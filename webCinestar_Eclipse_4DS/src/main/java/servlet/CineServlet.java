package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.CinestarDAO;

public class CineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CinestarDAO cinestarDAO = new CinestarDAO();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object data = cinestarDAO.getCines();
		request.getSession().setAttribute("cines", data);
		request.setAttribute("id", "cines");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
