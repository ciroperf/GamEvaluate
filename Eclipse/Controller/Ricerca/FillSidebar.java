package gamevaluate.controller.gestionericerca;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamevaluate.bean.GeneralUser;

/**
 * Servlet implementation class FillSidebar
 */
@WebServlet("/fillsidebar")
public class FillSidebar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillSidebar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		GeneralUser utente = (GeneralUser) session.getAttribute("user");
		
		if(utente == null) {
			out.println("<a href=\"/GamEvaluate/presentation/login.jsp\">Login</a>");
		} else {
				out.println("<a href=\"/GamEvaluate/presentation/user/modify-password.jsp\">Modifica Password</a>");
				out.println("<a href=\"/GamEvaluate/VisualizzaUtente?username="+utente.getUsername()+"\">Profilo</a>");
			if(utente.getRole() == 3) {
				out.println("<a href=\"/GamEvaluate/presentation/admin/add-game.jsp\">Aggiungi Giochi</a>");
				out.println("<a href=\"/GamEvaluate/presentation/admin/platforms.jsp\">Gestione Piattaforme</a>");
				out.println("<a href=\"/GamEvaluate/presentation/admin/genres.jsp\">Gestione Generi</a>");
			}
			out.println("<a href=\"/GamEvaluate/logout\">Logout</a>");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
