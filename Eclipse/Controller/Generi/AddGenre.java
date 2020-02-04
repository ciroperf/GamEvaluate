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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		request.getSession().removeAttribute("message");
		
		try {
			
			String nome = request.getParameter("nome");
			nome = nome.substring(0, nome.length()).toUpperCase();
			if (model.doRetrieveByKey(nome).getNome() != null) {
				request.getSession().setAttribute("message", "Genere già presente");
				response.sendRedirect("/GamEvaluate/presentation/admin/genres.jsp");
			} else {
				
				String descrizione = request.getParameter("descrizione");
				Genere g = new Genere(nome, descrizione);
				model.doSave(g);
				request.getSession().setAttribute("message", "Genere Aggiunto");
				response.sendRedirect("/GamEvaluate/presentation/admin/genres.jsp");
			}	
			
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			
		}
			
	}

}
