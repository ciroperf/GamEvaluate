package gamevaluate.controller.gestionericerca;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gamevaluate.bean.GeneralUser;
import gamevaluate.bean.Gioco;
import gamevaluate.bean.Recensione;
import gamevaluate.bean.Valutazione;
import gamevaluate.model.GeneralUserManager;
import gamevaluate.model.GiocoManager;
import gamevaluate.model.HaVotatoManager;
import gamevaluate.model.RecensioneManager;
import gamevaluate.model.ValutazioneManager;

/**
 * Servlet implementation class VisualizzaGioco
 */
@WebServlet("/infogame")
public class VisualizzaGioco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaGioco() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_gioco = Integer.parseInt(request.getParameter("gioco"));
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		GeneralUser user = (GeneralUser) session.getAttribute("user");
		//variabile booleana che indica alla jsp se deve o meno permettere la valutazione del gioco 
		//essa non deve essere permessa se l'utente è un visitatore o se l'utente ha già valutato il gioco.
		boolean consenti_valuatazione = true;
		GiocoManager gioco_manager = new GiocoManager();
		HaVotatoManager votato_manager = new HaVotatoManager();
		RecensioneManager recensioneManager = new RecensioneManager();
		ValutazioneManager valutazione_manager = new ValutazioneManager();
		try {
			//GIOCO
			Gioco gioco = gioco_manager.doRetrieveByKey(id_gioco);
			if(user == null || votato_manager.haVotato(user.getUsername(), gioco.getId()))
				consenti_valuatazione = false;
			//VALUTAZIONE
			Valutazione valutazione = valutazione_manager.doRetrieveByKey(gioco.getValutazione());
			int voto_generale = (valutazione.getGameplay() + valutazione.getCoinvolgimento() + valutazione.getCreativita() +
					valutazione.getDifficolta() + valutazione.getGrafica() + valutazione.getInnovazione() + 
					valutazione.getRealismo() + valutazione.getRigiocabilita() + valutazione.getTrama()) / 9;
			gioco.setValutazione(voto_generale);
			//RECENSIONI
			ArrayList<Recensione> recensioni = (ArrayList<Recensione>) recensioneManager.doRetrieveAll("");
			ArrayList<Recensione> recensioni_gioco = new ArrayList<Recensione>();
			for(Recensione recensione : recensioni) {
				if(recensione.getGioco() == id_gioco) {
					recensioni_gioco.add(recensione);
				}
			}
			Gson gson = new Gson();
			String jsongiochi = gson.toJson(gioco);
			String jsonconsenti = gson.toJson(consenti_valuatazione);
			String jsonvalutazione = gson.toJson(valutazione);
			String jsonrecensioni = gson.toJson(recensioni_gioco);
			out.println("{ \"gioco\":");
			out.println(jsongiochi+",");
			out.println("\"consenti_valutazione\":");
			out.println(jsonconsenti+",");
			out.println("\"valutazione\":");
			out.println(jsonvalutazione+",");
			out.println("\"recensioni\":");
			out.println(jsonrecensioni);
			out.println("}");
		} catch (SQLException e) {
			System.out.println("Errore SQL in VisualizzaGioco.java : "+e.getMessage());
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
