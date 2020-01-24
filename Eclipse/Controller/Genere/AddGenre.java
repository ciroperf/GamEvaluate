package gamevaluate.controller.gestioneGeneri;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gamevaluate.bean.Genere;
import gamevaluate.model.GenereManager;


@WebServlet("/admin/AddGenre")
public class AddGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GenereManager model = new GenereManager();

    public AddGenre() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		request.getSession().removeAttribute("error");
		request.getSession().removeAttribute("message");
		
		try {
			
			String nome = request.getParameter("nome");
			nome = nome.substring(0,1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
			if (model.doRetrieveByKey(nome) != null) {
				request.getSession().setAttribute("error", "Genere già presente");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/generi.jsp");
				rd.forward(request, response);
			} else {
				
				String descrizione = request.getParameter("descrizione");
				Genere g = new Genere(nome, descrizione);
				model.doSave(g);
				request.getSession().setAttribute("message", "Genere Aggiunto");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/generi.jsp");
				rd.forward(request, response);
			}	
			
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			
		}
			
	}

}
