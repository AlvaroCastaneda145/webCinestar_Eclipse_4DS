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
	        if (id.equals("cartelera") || id.equals("estrenos")) {
	            data = cinestarDAO.getPeliculas(id);
	            request.setAttribute("id", "peliculas");
	            request.getSession().setAttribute("peliculas", data); 
	        } else {
	            data = cinestarDAO.getPelicula(id);
	            request.setAttribute("id", "pelicula");
	            request.getSession().setAttribute("pelicula", data); 
	        }
	    }

	    request.getRequestDispatcher("index.jsp").forward(request, response);
	}													

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
