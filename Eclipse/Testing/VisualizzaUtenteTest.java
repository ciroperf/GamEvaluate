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
import gamevaluate.bean.GeneralUser;
import gamevaluate.controller.gestionericerca.VisualizzaUtente;

class VisualizzaUtenteTest extends Mockito{

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws IOException, ServletException {
		System.out.println("Test VisualizzaUtente");
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		
		when(request.getParameter("username")).thenReturn("admin");
		when(request.getSession()).thenReturn(session);
		
		ArgumentCaptor<String> captorstring = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<GeneralUser> captor = ArgumentCaptor.forClass(GeneralUser.class);
		
		
		new VisualizzaUtente().doGet(request, response);
		
		verify(session, times(3)).setAttribute(captorstring.capture(), captor.capture());
		
		assertEquals("admin", captor.getAllValues().get(0).getUsername());
	}
}
