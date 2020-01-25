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


@WebServlet("/ModifyPass")
public class ModifyPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GeneralUserManager model = new GeneralUserManager();
       

    public ModifyPass() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		try {
			if(!password1.equals(password2)) {
				String err = "Le password non coincidono!";
				session.setAttribute("error", err);
				response.sendRedirect("/GamEvaluate/presentation/user/user.jsp");
			} else {
				
				GeneralUser g = model.doRetrieveByKey(username);
				g.setPassword(password1);
				model.doUpdate(g);
				request.getSession().removeAttribute("utente");
				request.getSession().setAttribute("utente", model.doRetrieveByKey(username));
				request.getSession().setAttribute("message", "Password modificata");
				response.sendRedirect(request.getContextPath() + "/GamEvaluate/presentation/user/user.jsp");
			
			}
		} catch (SQLException e) {
			System.out.println("Errore retrieveByKey : "+ e.getMessage());
		}
		
	}

}
