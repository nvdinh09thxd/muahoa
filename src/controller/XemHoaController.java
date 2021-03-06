package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Hoa;
import model.dao.HoaDAO;

public class XemHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XemHoaController() {
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
		ArrayList<Hoa> listHoa = HoaDAO.getListHoa();
		request.setAttribute("listHoa", listHoa);
		request.getRequestDispatcher("/muahoa/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
