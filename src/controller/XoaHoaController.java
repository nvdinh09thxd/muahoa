package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Hoa;
import model.dao.HoaDAO;

public class XoaHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XoaHoaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idHoa = 0;
		try {
			idHoa = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/muahoa/PageNotFound.jsp");
			return;
		}
		Hoa itemHoa = HoaDAO.getItemHoa(idHoa);
		String filePath = request.getServletContext().getRealPath("") + "files\\" + itemHoa.getHinhAnh();
		File file = new File(filePath);
		file.delete();
		if (HoaDAO.delItem(idHoa) > 0) {
			response.sendRedirect(request.getContextPath() + "/xem-hoa?msg=3");
		} else {
			response.sendRedirect(request.getContextPath() + "/xem-hoa?msg=0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
