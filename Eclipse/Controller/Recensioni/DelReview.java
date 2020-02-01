package gamevaluate.controller.gestioneRecensioni;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamevaluate.bean.Recensione;
import gamevaluate.model.RecensioneManager;

/**
 * Servlet implementation class DelReview
 */
@WebServlet("/user/DelReview")
public class DelReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RecensioneManager model = new RecensioneManager();
	static RecensioneManager modelRecensione = new RecensioneManager();


	public DelReview() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {

			int gioco = Integer.parseInt(request.getParameter("idGioco"));
			String username = request.getParameter("username");
			String data = request.getParameter("data");
			String returnTo = request.getParameter("returnTo");

			model.doDelete(data, gioco, username);
			ArrayList<Recensione> recensioni = (ArrayList<Recensione>) modelRecensione.doRetrieveAll("");
			
			for(Iterator<Recensione> it = recensioni.iterator(); it.hasNext();) {
				Recensione r = it.next();
				if (!r.getUsername().equals(username))
					it.remove();
			}
			
			session.removeAttribute("recensioni");
			session.setAttribute("recensioni", recensioni);
			session.setAttribute("message", "Recensione eliminata");
			response.sendRedirect(returnTo);
			

		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());

		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
