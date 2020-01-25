package gamevaluate.controller.gestioneAccount;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamevaluate.bean.GeneralUser;
import gamevaluate.model.GeneralUserManager;
import gamevaluate.utilities.Md5Encoder;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("ciao");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");
		String regex = "^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		HttpSession session = request.getSession();
		GeneralUserManager manager_account = new GeneralUserManager();
		//Controllo delle presenza di uno username uguale già creato in passato (Presente nel DB).
		boolean trovato = false;
		try {
			GeneralUser user = manager_account.doRetrieveByKey(username);
			if(user != null)	{
				//Salvataggio dei valori dei campi del form di signin per ritornare a quello stato del form dopo la ridirezione
				String err = "Username già esistente!";
				session.setAttribute("message", err);
				response.sendRedirect("presentation/signin.jsp");
			} else {
				String email = request.getParameter("email");
				if(!email.matches(regex)) {
					String err = "Email non valida!";
					session.setAttribute("message", err);
					response.sendRedirect("presentation/signin.jsp");
				} else {
					String password1 = request.getParameter("password1");
					if(password1.length() < 5) {
						String err = "La password deve essere di almeno 5 caratteri!";
						session.setAttribute("message", err);
						response.sendRedirect("presentation/signin.jsp");
					} else {
						String password2 = request.getParameter("password2");
						if(!password1.equals(password2)) {
							String err = "Le password non coincidono!";
							session.setAttribute("message", err);
							response.sendRedirect("presentation/signin.jsp");
						} else {
							GeneralUserManager account_manager = new GeneralUserManager();
							Md5Encoder encoder = new Md5Encoder();
							password1 = encoder.encode(password1);
							user = new GeneralUser();
							user.setPassword(password1);
							user.setUsername(username);
							user.setEmail(email);
							user.setBanned(false);
							user.setRole(0);
							account_manager.doSave(user);
							String note = "Registrazione effettuata!";
							session.setAttribute("message", note);
							response.sendRedirect("presentation/login.jsp");
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore SQL : "+ e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Errore Md5 encoding : "+ e.getMessage());
		}


	}

}
