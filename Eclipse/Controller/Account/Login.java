package gamevaluate.controller.gestioneAccount;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import gamevaluate.utilities.Md5Encoder;
import gamevaluate.bean.GeneralUser;
import gamevaluate.model.GeneralUserManager;


/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Ciao");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Md5Encoder encoder = new Md5Encoder();
		try {
			password = encoder.encode(password);
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("Errore md5 : "+ e1.getMessage());
		}
		 GeneralUserManager user_manager= new GeneralUserManager();
		try {
			boolean trovato = true;
			GeneralUser user = user_manager.doRetrieveByKey(username);
			if(user == null || !(user.getUsername().equals(username) && user.getPassword().equals(password)))
				trovato = false;
			if(!trovato) {
				String err="Errore : username/password errati";
				session.setAttribute("message", err);
				response.sendRedirect("presentation/login.jsp");
			} else if (!user.isBanned()){
				session.setAttribute("user", user);
				response.sendRedirect("presentation/home.jsp");
			} else {
				
				String err="Errore : utente bannato";
				session.setAttribute("message", err);
				response.sendRedirect("presentation/login.jsp");
			}
		} catch (SQLException e) {
			System.out.println("Errore retrieveByKey : "+ e.getMessage());
		}
	}

}
