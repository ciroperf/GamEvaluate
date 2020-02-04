package gamevaluate.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamevaluate.bean.GeneralUser;

/**
 * Servlet Filter implementation class FiltroGuest
 */
@WebFilter(urlPatterns = { "/user/*"})
public class FiltroGuest implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroGuest() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		HttpSession session = hrequest.getSession();
		if(session != null) {
			GeneralUser utente = (GeneralUser)session.getAttribute("user");
			if((utente != null) && (utente.getRole() > 0)) {
				chain.doFilter(request, response);
			} else {
				session.setAttribute("message", "Operazione vietata!");
				hresponse.sendRedirect("/GamEvaluate/presentation/login.jsp");
			}
		} else {
				hresponse.sendRedirect("/GamEvaluate/presentation/login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
