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
		request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//Lấy các giá trị nhập vào
		String idStr = request.getParameter("id");
		String tenHoa = request.getParameter("ten");
		String idLoaihoaStr = request.getParameter("loaihoa");
		String soLuongStr = request.getParameter("soluong");
		String giaBanStr = request.getParameter("giaban");
		String moTa = request.getParameter("mota");
		
		//setAttribute cho các giá trị
		request.setAttribute("idStr", idStr);
		request.setAttribute("tenHoa", tenHoa);
		request.setAttribute("soLuongStr", soLuongStr);
		request.setAttribute("giaBanStr", giaBanStr);
		request.setAttribute("moTa", moTa);
		
		//Validate cho id
		if ("".equals(idStr)) {
			request.setAttribute("err", "Vui lòng nhập ID hoa!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}		
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			if (id <= 0)
				throw new Exception();
		} catch (Exception e) {
			request.setAttribute("err", "ID hoa phải là một số nguyên dương!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}
		
		//Validate cho tên
		if ("".equals(tenHoa)) {
			request.setAttribute("err", "Vui lòng nhập tên hoa!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}
		
		int idLoaihoa = Integer.parseInt(idLoaihoaStr);
		
		//Validate cho số lượng
		if ("".equals(soLuongStr)) {
			request.setAttribute("err", "Vui lòng nhập số lượng!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}		
		int soLuong = 0;
		try {
			soLuong = Integer.parseInt(soLuongStr);
			if (soLuong < 0)
				throw new Exception();
		} catch (Exception e) {
			request.setAttribute("err", "Số lượng phải là số không âm!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}
		
		//Validate cho giá bán
		if ("".equals(giaBanStr)) {
			request.setAttribute("err", "Vui lòng nhập giá bán!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}		
		float giaBan = 0;
		try {
			giaBan = Float.parseFloat(giaBanStr);
			if (giaBan < 0)
				throw new Exception();
		} catch (Exception e) {
			request.setAttribute("err", "Giá bán phải là số không âm!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}
		
		Part filePart = request.getPart("hinhanh");
		String fileName = filePart.getSubmittedFileName();
		//Validate cho hình ảnh
		if ("".equals(fileName)) {
			request.setAttribute("err", "Vui lòng chọn hình ảnh!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}
		String fileType = filePart.getContentType();
		if (!fileType.startsWith("image")) {
			request.setAttribute("err", "File bạn chọn không phải là file ảnh!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
			return;
		}
		// Xử lý upload ảnh
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

		Hoa hoa = new Hoa(id, tenHoa, soLuong, giaBan, fileName, moTa, idLoaihoa);
		// Thêm vào cơ sở dữ liệu
		if (HoaDAO.addItem(hoa) > 0) {
			response.sendRedirect(request.getContextPath() + "/xem-hoa?msg=1");
		} else {
			request.setAttribute("err", "Xảy ra lỗi trong quá trình xử lý!");
			request.getRequestDispatcher("/muahoa/add.jsp").forward(request, response);
		}
	}
}
