package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Hoa;
import model.dao.HoaDAO;

public class XoaHoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_UPLOAD = "muahoa/upload";

	public XoaHoaController() {
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
			response.sendRedirect(request.getContextPath() + "/muahoa/PageNotFound.jsp");
			return;
		}
		Hoa itemHoa = HoaDAO.getItemHoa(idHoa);
		if (itemHoa == null) {
			request.getRequestDispatcher("/muahoa/PageNotFound.jsp").forward(request, response);
			return;
		}
		String filePath = request.getServletContext().getRealPath("") + DIR_UPLOAD + File.separator + itemHoa.getHinhAnh();
		File file = new File(filePath);
		if (HoaDAO.delItem(idHoa) > 0) {
			file.delete();
			response.sendRedirect(request.getContextPath() + "/xem-hoa?msg=3");
		} else {
			response.sendRedirect(request.getContextPath() + "/xem-hoa?msg=0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
