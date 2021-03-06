package gamevaluate.controller.gestioneRecensioni;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamevaluate.bean.GeneralUser;
import gamevaluate.bean.Recensione;
import gamevaluate.model.RecensioneManager;


@WebServlet("/user/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RecensioneManager model = new RecensioneManager();
       
    public AddReview() {
        super();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			
			String testo = request.getParameter("testo");
			int idGioco = Integer.parseInt(request.getParameter("idGioco"));
			GeneralUser user = (GeneralUser) session.getAttribute("user");
			String username = user.getUsername();
			
			if (testo.length() != 0 && testo.length() < 2000) {
			
				Recensione r = new Recensione(testo, idGioco, username);
				model.doSave(r);
				session.setAttribute("message", "Recensione inserita");
				response.sendRedirect("/GamEvaluate/presentation/info-game.jsp?gioco="+idGioco);
				
			}

		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			
		}
		
	}

}
