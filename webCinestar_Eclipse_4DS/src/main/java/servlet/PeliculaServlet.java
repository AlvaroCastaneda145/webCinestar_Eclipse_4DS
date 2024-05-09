package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.CinestarDAO;

public class PeliculaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CinestarDAO cinestarDAO = new CinestarDAO();
       	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("id");
		    Object data = null;
		    if (id != null) {
		        if (id.equals("cartelera")) {
		            data = cinestarDAO.getPeliculas(1); 
		        } else if (id.equals("estrenos")) {
		            data = cinestarDAO.getPeliculas(2); 
		        }
		    } 
		    request.getSession().setAttribute("peliculas", data);
		    request.setAttribute("id", "peliculas");
		    request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
