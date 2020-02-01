package gamevaluate.controller.gestionePiattaforme;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gamevaluate.bean.Gioco;
import gamevaluate.bean.Piattaforma;
import gamevaluate.model.GiocoManager;
import gamevaluate.model.PiattaformaManager;


@WebServlet("/admin/DelPlatform")
public class DelPlatform extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static GiocoManager modelGioco = new GiocoManager();
	static PiattaformaManager modelPiattaforma = new PiattaformaManager();

    public DelPlatform() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("message");
		
		try {
			
			String nome = request.getParameter("nome");
			nome = nome.substring(0,1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
			Piattaforma piattaforma = modelPiattaforma.doRetrieveByKey(nome);
			boolean find = false;
			if (piattaforma != null) {
				ArrayList<Gioco> giochi = (ArrayList<Gioco>) modelGioco.doRetrieveAll("");
				
				for (Gioco g: giochi) {
					if (modelPiattaforma.doRetrieveByKey(g.getPiattaforma()).equals(piattaforma)) {
						find = true;
					}
				}
				
				if (find) {
					request.getSession().setAttribute("message", "Impossibile eliminare piattaforma: sono presenti piattaforme con tale genere");
					response.sendRedirect("/GamEvaluate/presentation/admin/platforms.jsp");
				} else {
					modelPiattaforma.doDelete(nome);
					request.getSession().setAttribute("message", "piattaforma eliminata");
					response.sendRedirect("/GamEvaluate/presentation/admin/platforms.jsp");
				}
			} else {
				
				request.getSession().setAttribute("error", "Piattaforma null");
				response.sendRedirect("/GamEvaluate/presentation/admin/platforms.jsp");
			}
			
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
