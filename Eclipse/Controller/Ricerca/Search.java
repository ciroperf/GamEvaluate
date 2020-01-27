package gamevaluate.controller.gestionericerca;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gamevaluate.bean.GeneralUser;
import gamevaluate.bean.Gioco;
import gamevaluate.bean.Valutazione;
import gamevaluate.model.GeneralUserManager;
import gamevaluate.model.GiocoManager;
import gamevaluate.model.ValutazioneManager;
import gamevaluate.utilities.ValutationSorter;

/**
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filtro_valutazione = Integer.parseInt(request.getParameter("valutazione"));
		int filtro_titolo = Integer.parseInt(request.getParameter("titolo"));
		String order="";
		String filtro_genere = request.getParameter("genere");
		String filtro_piattaforma = request.getParameter("piattaforma");
		String testo_searchbar = request.getParameter("value");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String target = request.getParameter("target");
		GeneralUser user = (GeneralUser) session.getAttribute("user");
		
		//settaggio dei filtri
		if(filtro_titolo == 1)
			order = "ASC";
		if(filtro_titolo == 2)
			order = "DESC";
		
		switch(target) {
			case "utenti" :
				GeneralUserManager user_manager = new GeneralUserManager();
				try {
					ArrayList<GeneralUser> utenti = (ArrayList<GeneralUser>) user_manager.doRetrieveAll("");
					ArrayList<GeneralUser> result = new ArrayList<GeneralUser>();
					for(GeneralUser utente : utenti) {
						if(utente.getUsername().contains(testo_searchbar)) {
							result.add(utente);
						}
					}
					Gson gson = new Gson();
					String jsonutenti = gson.toJson(result);
					out.println("{ \"utenti\":");
					out.println(jsonutenti);
					out.println("}");
				} catch (SQLException e) {
					System.out.println("Errore retrieveAll di utenti nel riempimento della search : "+e.getMessage());
				}
				break;
			case "giochi" :
				GiocoManager gioco_manager = new GiocoManager();
				ValutazioneManager valutazione_manager = new ValutazioneManager();
				try {
					ArrayList<Gioco> giochi = (ArrayList<Gioco>) gioco_manager.doRetrieveAll(order);
					ArrayList<Gioco> result = new ArrayList<Gioco>();
					Valutazione valutazione;
					for(Gioco gioco : giochi) {
						valutazione = valutazione_manager.doRetrieveByKey(gioco.getValutazione());
						int voto_generale = (valutazione.getGameplay() + valutazione.getCoinvolgimento() + valutazione.getCreativita() +
								valutazione.getDifficolta() + valutazione.getGrafica() + valutazione.getInnovazione() + 
								valutazione.getRealismo() + valutazione.getRigiocabilita() + valutazione.getTrama()) / 9;
						gioco.setValutazione(voto_generale);
					}
						//valutazione crescente
						if(filtro_valutazione == 1)
							giochi.sort(new ValutationSorter(true));
						//valutazione descrescente
						if(filtro_valutazione == 2)
							giochi.sort(new ValutationSorter(false));
					boolean matched;
					for(Gioco gioco : giochi) {
						matched = true;
						if((!filtro_genere.equals("Tutti")) && (!gioco.getGenere().equals(filtro_genere))) {
							matched = false;
						}
						if((!filtro_piattaforma.equals("Tutte")) && (!gioco.getPiattaforma().equals(filtro_piattaforma))) {
							matched = false;
						}
						if(!gioco.getNome().toLowerCase().contains(testo_searchbar.toLowerCase()))
							matched = false;
						if(matched)
							result.add(gioco);
					}
					Gson gson = new Gson();
					String jsongiochi = gson.toJson(result);
					out.println("{ \"giochi\":");
					out.println(jsongiochi);
					out.println("}");
				} catch (SQLException e) {
					System.out.println("Errore retrieveAll di giochi/valutazioni nel riempimento della search : "+e.getMessage());
				}
				break;
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
