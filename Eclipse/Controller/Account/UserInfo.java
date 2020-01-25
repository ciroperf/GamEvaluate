package gamevaluate.controller.gestioneAccount;

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


@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GeneralUserManager model = new GeneralUserManager();

    public UserInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		
		try {
			GeneralUser g = model.doRetrieveByKey(username);
			if (g == null) {
				System.out.println("utente null");

			} else {
				
				session.removeAttribute("utente");
				session.setAttribute("utente", g);
				response.sendRedirect("/GamEvaluate/presentation/user/user.jsp");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
