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
import org.mockito.Mockito;

import gamevaluate.bean.GeneralUser;
import gamevaluate.controller.gestionericerca.VisualizzaGioco;

class VisuallizzaGiocoTest extends Mockito{

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws IOException, ServletException {
		System.out.println("Test VisualizzaGioco");
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		
		when(request.getParameter("gioco")).thenReturn("10");
		GeneralUser user = new GeneralUser();
		user.setUsername("admin");
		when(session.getAttribute("user")).thenReturn(user);
		when(request.getSession()).thenReturn(session);
		
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		
		when(response.getWriter()).thenReturn(out);
		
		new VisualizzaGioco().doGet(request, response);
		
		assertTrue(writer.toString().contains("\"id\":10,\"nome\":\"Monster Hunter\""));
	}

}
