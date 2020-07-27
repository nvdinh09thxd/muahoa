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
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/muahoa/PageNotFound.jsp");
			return;
		}
		Hoa itemHoa = HoaDAO.getItemHoa(idHoa);
		request.setAttribute("itemHoa", itemHoa);
		RequestDispatcher rd = request.getRequestDispatcher("muahoa/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String tenHoa = request.getParameter("ten");
		int id_loaihoa = Integer.parseInt(request.getParameter("loaihoa"));
		int soLuong = Integer.parseInt(request.getParameter("soluong"));
		String moTa = request.getParameter("mota");
		
		//delete picture
		Hoa itemHoa1 = HoaDAO.getItemHoa(id);
		String filePath1 = request.getServletContext().getRealPath("")+"files\\"+itemHoa1.getHinhAnh();
		File file = new File(filePath1);
		file.delete();
		// handle upload
		Part filePart = request.getPart("hinhanh");
		String fileName = getName(filePart);
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
		String filePath = dirPath + File.separator + fileName;
		filePart.write(filePath);
		
		Hoa itemHoa = new Hoa(id, tenHoa, soLuong, fileName, moTa, id_loaihoa);
		if(HoaDAO.editItem(itemHoa)>0) {
			response.getWriter().print("Thanh cong");
		}else {
			response.getWriter().print("That bai");
		}
	}
	private String getName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
