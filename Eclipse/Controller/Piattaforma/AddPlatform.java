package gamevaluate.controller.gestionePiattaforme;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gamevaluate.bean.Piattaforma;
import gamevaluate.model.PiattaformaManager;

@WebServlet("/AddPlatform")
public class AddPlatform extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static PiattaformaManager model = new PiattaformaManager();

    public AddPlatform() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("message");
		
		try {
			
			String nome = request.getParameter("nome");
			nome = nome.substring(0,1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();
			if (model.doRetrieveByKey(nome).getNome() != null) {
				request.getSession().setAttribute("message", "Piattaforma già presente");
				response.sendRedirect("presentation/admin/platforms.jsp");
			} else {
				
				Piattaforma p = new Piattaforma(nome);
				model.doSave(p);
				request.getSession().setAttribute("message", "Piattaforma Aggiunta");
				response.sendRedirect("presentation/admin/platforms.jsp");
				
			}
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			
		}
	}
}
