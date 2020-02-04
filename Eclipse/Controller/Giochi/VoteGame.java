package gamevaluate.controller.gestioneGiochi;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamevaluate.bean.GeneralUser;
import gamevaluate.bean.Gioco;
import gamevaluate.bean.Valutazione;
import gamevaluate.model.GeneralUserManager;
import gamevaluate.model.GiocoManager;
import gamevaluate.model.HaVotatoManager;
import gamevaluate.model.ValutazioneManager;


@WebServlet("/user/votegame")
public class VoteGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ValutazioneManager modelValutazione = new  ValutazioneManager();
	private static HaVotatoManager model_haVotato = new  HaVotatoManager();
	private static GiocoManager model_gioco = new  GiocoManager();
	
       

    public VoteGame() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int idGioco = Integer.parseInt(request.getParameter("id_gioco"));
		
		
		
		try {
			
			Gioco gioco = model_gioco.doRetrieveByKey(idGioco);
			Valutazione v = modelValutazione.doRetrieveByKey(gioco.getValutazione());
			System.out.println("valutazione : "+v);
			
			if (v != null) {
				
				
				
				//VARIABILI INSERITE DALL'UTENTE
				int ga = Integer.parseInt(request.getParameter("gameplay"));
				int tr = Integer.parseInt(request.getParameter("trama"));
				int gr = Integer.parseInt(request.getParameter("grafica"));
				int cr = Integer.parseInt(request.getParameter("creativita"));
				int in = Integer.parseInt(request.getParameter("innovazione"));
				int co = Integer.parseInt(request.getParameter("coinvolgimento"));
				int re = Integer.parseInt(request.getParameter("realismo"));
				int ri = Integer.parseInt(request.getParameter("rigiocabilita"));
				int di = Integer.parseInt(request.getParameter("difficolta"));
				
				
				//VARIABILI PRESENTI NELLA VOTAZIONE
				int gameplay = v.getGameplay();
				int trama = v.getTrama();
				int grafica = v.getGrafica();
				int creativita = v.getCreativita();
				int innovazione = v.getInnovazione();
				int coinvolgimento = v.getCoinvolgimento();
				int realismo = v.getRealismo();
				int rigiocabilita = v.getRigiocabilita();
				int difficolta = v.getDifficolta();
				
				System.out.println(gameplay+trama+grafica+creativita+innovazione+coinvolgimento+realismo+rigiocabilita+difficolta);
				
				
				if (gameplay == 0) {
					
					v.setGameplay(ga);
					v.setTrama(tr);
					v.setGrafica(gr);
					v.setCreativita(cr);
					v.setInnovazione(in);
					v.setCoinvolgimento(co);
					v.setRealismo(re);
					v.setRigiocabilita(ri);
					v.setDifficolta(di);
					v.addCounter();
				} else {
					
					int counter = v.getCounter();
					v.setGameplay(((gameplay * counter) + ga) / (counter + 1));
					v.setTrama(((trama * counter) + tr) / (counter + 1));
					v.setGrafica(((grafica* counter) + gr) / (counter + 1));
					v.setCreativita(((creativita * counter) + cr) / (counter + 1));
					v.setInnovazione(((innovazione * counter) + in) / (counter + 1));
					v.setCoinvolgimento(((coinvolgimento * counter) + co) / (counter + 1));
					v.setRealismo(((realismo * counter) + re) / (counter + 1));
					v.setRigiocabilita(((rigiocabilita * counter) + ri) / (counter + 1));
					v.setDifficolta(((difficolta * counter) + di) / (counter + 1));
					v.addCounter();
				}
				GeneralUser user = (GeneralUser)session.getAttribute("user");
				modelValutazione.doUpdate(v);
				model_haVotato.salvaVotazione(user.getUsername(), idGioco);
				session.setAttribute("message", "Valutazione inserita");
				response.sendRedirect("/GamEvaluate/presentation/info-game.jsp?gioco="+idGioco);

			}
			
		} catch(SQLException e) {
			System.out.println("Error:" + e.getMessage());
			session.setAttribute("message", e.getMessage());
			response.sendRedirect("/GamEvaluate/presentation/info-game.jsp?gioco="+idGioco);
		}
		catch(NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			session.setAttribute("message", e.getMessage());
			response.sendRedirect("/GamEvaluate/presentation/info-game.jsp?gioco="+idGioco);
		}
		
	}

}
