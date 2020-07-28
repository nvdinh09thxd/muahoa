package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Hoa;
import model.dao.HoaDAO;

@MultipartConfig
public class SuaHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SuaHoaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idHoa = 0;
		try {
			idHoa = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("/muahoa/PageNotFound.jsp").forward(request, response);
			return;
		}
		Hoa itemHoa = HoaDAO.getItemHoa(idHoa);
		request.setAttribute("itemHoa", itemHoa);
		RequestDispatcher rd = request.getRequestDispatcher("muahoa/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String tenHoa = request.getParameter("ten");
		int id_loaihoa = Integer.parseInt(request.getParameter("loaihoa"));
		int soLuong = Integer.parseInt(request.getParameter("soluong"));
		float giaBan = Float.parseFloat(request.getParameter("giaban"));
		String moTa = request.getParameter("mota");

		// xóa ảnh cũ
		Hoa itemHoa = HoaDAO.getItemHoa(id);
		String filePath = request.getServletContext().getRealPath("") + "files\\" + itemHoa.getHinhAnh();
		File file = new File(filePath);
		file.delete();

		// upload ảnh mới
		Part filePart = request.getPart("hinhanh");
		String fileName = filePart.getSubmittedFileName();
		String appPath = request.getServletContext().getRealPath("");
		String dirPath = appPath + "files";
		File saveDir = new File(dirPath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		String portal = fileName.split("\\.")[0];
		String extra = fileName.split("\\.")[1];
		long time = System.currentTimeMillis();
		fileName = portal + "_" + time + "." + extra;
		filePath = dirPath + File.separator + fileName;
		filePart.write(filePath);

		itemHoa = new Hoa(id, tenHoa, soLuong, giaBan, fileName, moTa, id_loaihoa);
		if (HoaDAO.editItem(itemHoa) > 0) {
			response.sendRedirect(request.getContextPath() + "/xem-hoa?msg=2");
		} else {
			request.getRequestDispatcher("/muahoa/edit.jsp?err=0").forward(request, response);
		}
	}
}
