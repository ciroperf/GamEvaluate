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

import gamevaluate.bean.Genere;
import gamevaluate.bean.Piattaforma;
import gamevaluate.model.GenereManager;
import gamevaluate.model.PiattaformaManager;

/**
 * Servlet implementation class SelectFiller
 */
@WebServlet("/selectfiller")
public class SelectFiller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectFiller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String target = request.getParameter("target");
		int for_filters = Integer.parseInt(request.getParameter("for_filters"));
		
		switch(target) {
			case "genre" :
				GenereManager genere_manager = new GenereManager();
				try {
					ArrayList<Genere> generi = (ArrayList<Genere>)genere_manager.doRetrieveAll("");
					for(Genere genere : generi) {
						out.println("<a onclick=\"genereChanged('"+genere.getNome()+"')\" class=\"dropdown-item\" href=\"#\" role=\"button\">"+genere.getNome()+"</a>");
					}
					if(for_filters == 1)
						out.println("<a  onclick=\"genereChanged('Tutti')\"class=\"dropdown-item\" href=\"#\" role=\"button\">Tutti</a>");
				} catch (SQLException e) {
					System.out.println("Errore retrieveAll di generi nel riempimento delle select nei filtri : "+e.getMessage());
				}
				break;
			case "platform" :
				PiattaformaManager piattaforma_manager = new PiattaformaManager();
				try {
					ArrayList<Piattaforma> piattaforme = (ArrayList<Piattaforma>)piattaforma_manager.doRetrieveAll("");
					for(Piattaforma piattaforma : piattaforme) {
						out.println("<a onclick=\"piattaformaChanged('"+piattaforma.getNome()+"')\"class=\"dropdown-item\" href=\"#\" role=\"button\">"+piattaforma.getNome()+"</button>");
					}
					if(for_filters == 1)
						out.println("<a onclick=\"piattaformaChanged('Tutte')\" class=\"dropdown-item\" href=\"#\" role=\"button\">Tutte</a>");
				} catch (SQLException e) {
					System.out.println("Errore retrieveAll di piattaforme nel riempimento delle select nei filtri : "+e.getMessage());
				}
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
