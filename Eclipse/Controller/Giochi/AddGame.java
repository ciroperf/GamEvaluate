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
import gamevaluate.bean.Valutazione;
import gamevaluate.model.GenereManager;
import gamevaluate.model.GiocoManager;
import gamevaluate.model.PiattaformaManager;
import gamevaluate.model.ValutazioneManager;

@WebServlet("/AddGame")
public class AddGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GiocoManager giocoModel = new GiocoManager();
	private static GenereManager genereModel = new GenereManager();
	private static PiattaformaManager piattaformaModel = new PiattaformaManager();
	private static ValutazioneManager valutazioneModel = new ValutazioneManager();
       
    public AddGame() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		HttpSession session = request.getSession();
		
		try {
			
			boolean valid = true;
			Valutazione v = new Valutazione();
			String nome = request.getParameter("nome");
			String descrizione = request.getParameter("descrizione");
			String genere = request.getParameter("genere");
			String piattaforma = request.getParameter("piattaforma");
			String immagine = request.getParameter("immagine");
			genere = genere.substring(0,genere.length()).toUpperCase();
			nome = nome.substring(0,1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
			piattaforma = piattaforma.substring(0,1).toUpperCase() + piattaforma.substring(1, piattaforma.length()).toLowerCase();
			
			if (genereModel.doRetrieveByKey(genere) == null) {
				System.out.println("Errore genere non esistente nel db");
				valid = false;
			}
			
			if (piattaformaModel.doRetrieveByKey(piattaforma) == null) {
				System.out.println("Errore piattaforma non esistente nel db");
				valid = false;
			}
			
			if (valid) {
			
				Gioco gioco = new Gioco(nome, descrizione, immagine, genere, piattaforma);
		        giocoModel.doSave(gioco);
				int idGioco = giocoModel.getLastId();
				if (idGioco == -1)
					System.out.println("Errore id gioco");
				else {
					v.setId(idGioco);
					valutazioneModel.doSave(v);
					gioco = giocoModel.doRetrieveByKey(idGioco);
					gioco.setValutazione(idGioco);
					giocoModel.doUpdate(gioco);
					session.setAttribute("message", "gioco aggiunto");
					response.sendRedirect("presentation/admin/add-game.jsp");
				}
			}
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("message", e.getMessage());
			
		}

	}
}
