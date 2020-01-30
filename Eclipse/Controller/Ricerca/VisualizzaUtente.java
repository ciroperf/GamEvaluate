package gamevaluate.controller.gestioneRicerca;

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
import gamevaluate.bean.GeneralUser;
import gamevaluate.bean.Gioco;
import gamevaluate.bean.Recensione;
import gamevaluate.model.GeneralUserManager;
import gamevaluate.model.GiocoManager;
import gamevaluate.model.RecensioneManager;


@WebServlet("/VisualizzaUtente")
public class VisualizzaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GeneralUserManager modelUser = new GeneralUserManager();
	static RecensioneManager modelRecensione = new RecensioneManager();
	static GiocoManager modelGioco = new GiocoManager();

    public VisualizzaUtente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		
		try {
			GeneralUser g = modelUser.doRetrieveByKey(username);
			if (g == null) {
				System.out.println("utente null");

			} else {
				
				
				ArrayList<Recensione> recensioni = (ArrayList<Recensione>) modelRecensione.doRetrieveAll("");
				
				for(Iterator<Recensione> it = recensioni.iterator(); it.hasNext();) {
					Recensione r = it.next();
					if (!r.getUsername().equals(username))
						it.remove();
				}
				
				ArrayList<String> titoli = new ArrayList<String>();
				for (int i = 0; i < recensioni.size(); i++) {
					Gioco gioco = modelGioco.doRetrieveByKey(recensioni.get(i).getGioco());
					titoli.add(gioco.getNome());
				}
				
				session.removeAttribute("other-user");
				session.removeAttribute("recensioni");
				session.removeAttribute("titoli-giochi");
				session.setAttribute("other-user", g);
				session.setAttribute("recensioni", recensioni);
				session.setAttribute("titoli-giochi", titoli);
				response.sendRedirect("/GamEvaluate/presentation/user-info.jsp");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
