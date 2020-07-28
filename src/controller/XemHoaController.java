package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Hoa;
import model.dao.HoaDAO;

public class XemHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XemHoaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Hoa> listHoa = HoaDAO.getListHoa();
		request.setAttribute("listHoa", listHoa);
		request.getRequestDispatcher("/muahoa/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
