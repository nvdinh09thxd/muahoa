package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.LoaiHoa;
import model.dao.LoaiHoaDAO;

@MultipartConfig
public class ThemLoaiHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemLoaiHoaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String loaiHoa = request.getParameter("loaihoa");
		LoaiHoa item = new LoaiHoa(0, loaiHoa);
		if (LoaiHoaDAO.addItem(item) > 0) {
			response.getWriter().println("Đã lưu!");
		} else {
			response.getWriter().println("That bai!");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
