package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import gamevaluate.controller.gestioneAccount.Login;

class LoginTest extends Mockito{


	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		
		System.out.println("Test Login");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("username")).thenReturn("admin");
		when(request.getParameter("password")).thenReturn("admin");
		when(request.getSession()).thenReturn(session);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		new Login().doPost(request, response);
		
		verify(response).sendRedirect(captor.capture());
		assertEquals("presentation/home.jsp", captor.getValue());
		
		 System.out.println("\n");
	    
		
	}

}
