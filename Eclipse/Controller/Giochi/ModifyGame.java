package gamevaluate.controller.gestioneGiochi;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamevaluate.bean.Gioco;
import gamevaluate.model.GiocoManager;

@WebServlet("/admin/ModifyGame")
public class ModifyGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GiocoManager model = new GiocoManager();
       
    
    public ModifyGame() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			
			int idGioco = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			String descrizione = request.getParameter("descrizione");
			String genere = request.getParameter("genere");
			String piattaforma = request.getParameter("piattaforma");
			String immagine = request.getParameter("immagine");
			genere = genere.substring(0,genere.length()).toUpperCase();
			nome = nome.substring(0,1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
			piattaforma = piattaforma.substring(0,1).toUpperCase() + piattaforma.substring(1, piattaforma.length()).toLowerCase();
			
			Gioco g = model.doRetrieveByKey(idGioco);
			g.setNome(nome);
			g.setDescrizione(descrizione);
			g.setGenere(genere);
			g.setPiattaforma(piattaforma);
			g.setImmagine(immagine);
			model.doUpdate(g);
			session.setAttribute("message", "modificato");
			response.sendRedirect("/GamEvaluate/presentation/admin/modify-game.jsp?gioco=" + g.getId());

		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			
		}
		
	}

}
