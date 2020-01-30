package gamevaluate.controller.gestioneUtenti;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import gamevaluate.bean.GeneralUser;
import gamevaluate.model.GeneralUserManager;


@WebServlet("/Downgrade")
public class Downgrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GeneralUserManager model = new GeneralUserManager();

    public Downgrade() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			
			String username = request.getParameter("username");
			
			GeneralUser u = model.doRetrieveByKey(username);
			u.setRole(1);
			model.doUpdate(u);
			session.setAttribute("other-user", model.doRetrieveByKey(username));
			response.sendRedirect("/GamEvaluate/presentation/user-info.jsp");
			
			
		} catch(SQLException | NumberFormatException e) {
			System.out.println("Error:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
