package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import gamevaluate.controller.gestioneAccount.Signin;

class SigninTest extends Mockito{

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("Test Signin");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("user")).thenReturn("carmine");
		when(request.getParameter("email")).thenReturn("carmine@carmine.it");
		when(request.getParameter("password1")).thenReturn("testpassword");
		when(request.getParameter("password2")).thenReturn("testpassword");
		when(request.getSession()).thenReturn(session);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		new Signin().doPost(request, response);
		
		verify(response).sendRedirect(captor.capture());
		assertEquals("presentation/login.jsp", captor.getValue());
		
		 System.out.println("\n");
	}

}
