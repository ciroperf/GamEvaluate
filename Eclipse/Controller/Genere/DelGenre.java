package gamevaluate.controller.gestioneGeneri;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gamevaluate.bean.Genere;
import gamevaluate.bean.Gioco;
import gamevaluate.model.GenereManager;
import gamevaluate.model.GiocoManager;


@WebServlet("/DelGenre")
public class DelGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GenereManager modelGenere = new GenereManager();
	static GiocoManager modelGioco = new GiocoManager();
	
    public DelGenre() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("error");
		request.getSession().removeAttribute("message");
		
		try {
			
			String nome = request.getParameter("nome");
			nome = nome.substring(0,1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
			Genere genere = modelGenere.doRetrieveByKey(nome);
			boolean find = false;
			if (genere != null) {
				
				ArrayList<Gioco> giochi = (ArrayList<Gioco>) modelGioco.doRetrieveAll("");
				
				for (Gioco g: giochi) {
					if (modelGenere.doRetrieveByKey(g.getGenere()).equals(genere)) {
						find = true;
					}
				}
				
				if (find) {
					request.getSession().setAttribute("error", "Impossibile eliminare genere: sono presenti giochi con tale genere");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/generi.jsp");
					rd.forward(request, response);
				} else {
					modelGenere.doDelete(nome);
					request.getSession().setAttribute("message", "Genere eliminato");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/generi.jsp");
					rd.forward(request, response);
				}
			} else {
				
				request.getSession().setAttribute("error", "Genere null");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/generi.jsp");
				rd.forward(request, response);
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
