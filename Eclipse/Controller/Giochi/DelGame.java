package gamevaluate.controller.gestioneGiochi;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import gamevaluate.model.GiocoManager;


@WebServlet("/admin/DelGame")
public class DelGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GiocoManager model = new GiocoManager();
       
    public DelGame() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		session.removeAttribute("message");
		int id = Integer.parseInt(request.getParameter("idgioco"));
		
		try {
			
			model.doDelete(id);
			session.setAttribute("message", "gioco eliminato");
			response.sendRedirect("/GamEvaluate/presentation/home.jsp");
			
		} catch(SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			
		}
	}

}
