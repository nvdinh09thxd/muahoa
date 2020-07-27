package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Hoa;
import model.dao.HoaDAO;

@MultipartConfig
public class ThemHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemHoaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("muahoa/add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameterNames());
		int id = Integer.parseInt(request.getParameter("id"));
		String tenHoa = request.getParameter("ten");
		int id_loaihoa = Integer.parseInt(request.getParameter("loaihoa"));
		int soLuong = Integer.parseInt(request.getParameter("soluong"));
		String moTa = request.getParameter("mota");
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

		Hoa hoa = new Hoa(id, tenHoa, soLuong, fileName, moTa, id_loaihoa);
		if (HoaDAO.addItem(hoa) > 0) {
			response.getWriter().println("Thanh cong!");
		} else {
			response.getWriter().println("That bai!");
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
