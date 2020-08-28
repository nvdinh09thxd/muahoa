package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Hoa;
import model.dao.HoaDAO;

@MultipartConfig
public class SuaHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_UPLOAD = "muahoa/upload";

	public SuaHoaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Nếu chưa login thì chuyển đến trang login
		if (session.getAttribute("userLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		int idHoa = 0;
		try {
			idHoa = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("/muahoa/PageNotFound.jsp").forward(request, response);
			return;
		}
		Hoa itemHoa = HoaDAO.getItemHoa(idHoa);
		if (itemHoa == null) {
			request.getRequestDispatcher("/muahoa/PageNotFound.jsp").forward(request, response);
			return;
		}
		request.setAttribute("itemHoa", itemHoa);
		RequestDispatcher rd = request.getRequestDispatcher("muahoa/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Nếu chưa login thì chuyển đến trang login
		if (session.getAttribute("userLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// Lấy các giá trị đã sửa
		int id = Integer.parseInt(request.getParameter("id"));
		String tenHoa = request.getParameter("ten");
		int id_loaihoa = Integer.parseInt(request.getParameter("loaihoa"));
		int soLuong = Integer.parseInt(request.getParameter("soluong"));
		float giaBan = Float.parseFloat(request.getParameter("giaban"));
		String moTa = request.getParameter("mota");

		// Lấy ảnh cũ
		Hoa itemHoa = HoaDAO.getItemHoa(id);
		String oldPicture = itemHoa.getHinhAnh();
		String filePath = request.getServletContext().getRealPath("") + DIR_UPLOAD + File.separator
				+ oldPicture;
		File file = new File(filePath);

		// validate cho hình ảnh
		Part filePart = request.getPart("hinhanh");
		String fileName = filePart.getSubmittedFileName();
		boolean isSelected = fileName != "";// Có chọn ảnh hay không?
		if (isSelected) {
			String fileType = filePart.getContentType();
			if (!fileType.startsWith("image")) {
				request.setAttribute("hinhanh", oldPicture);
				request.getRequestDispatcher("/muahoa/edit.jsp?err=1").forward(request, response);
				return;
			}
			// đổi tên file
			String portal = fileName.split("\\.")[0];
			String extra = fileName.split("\\.")[1];
			long time = System.currentTimeMillis();
			fileName = portal + "_" + time + "." + extra;
			// Xử lý upload ảnh
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_UPLOAD;
			File saveDir = new File(dirPath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			filePath = dirPath + File.separator + fileName;
		} else {
			fileName = oldPicture;
		}

		itemHoa = new Hoa(id, tenHoa, soLuong, giaBan, fileName, moTa, id_loaihoa);
		// Sửa trong cơ sở dữ liệu
		if (HoaDAO.editItem(itemHoa) > 0) {// Nếu sửa thành công thì mới xóa ảnh cũ và upload ảnh mới
			if (isSelected) {
				// xóa ảnh cũ
				file.delete();
				// upload ảnh mới
				filePart.write(filePath);
			}
			response.sendRedirect(request.getContextPath() + "/xem-hoa?msg=2");
		} else {
			request.setAttribute("hinhanh", oldPicture);
			request.getRequestDispatcher("/muahoa/edit.jsp?err=0").forward(request, response);
		}
	}
}
