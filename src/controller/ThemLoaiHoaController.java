package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.LoaiHoa;
import model.dao.LoaiHoaDAO;

public class ThemLoaiHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemLoaiHoaController() {
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
		request.getRequestDispatcher("/muahoa/themloaihoa.jsp").forward(request, response);
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

		String loaiHoa = request.getParameter("loaihoa");
		request.setAttribute("loaiHoa", loaiHoa);
		// Validate cho tên loại hoa
		if ("".equals(loaiHoa)) {
			request.setAttribute("err", "Vui lòng nhập tên loại hoa!");
			request.getRequestDispatcher("/muahoa/themloaihoa.jsp").forward(request, response);
			return;
		}
		LoaiHoa item = new LoaiHoa(0, loaiHoa);
		if (LoaiHoaDAO.addItem(item) > 0) {
			response.sendRedirect(request.getContextPath() + "/index?msg=1");
		} else {
			request.setAttribute("err", "Xảy ra lỗi trong quá trình xử lý!");
			request.getRequestDispatcher("/muahoa/themloaihoa.jsp").forward(request, response);
		}
	}

}
