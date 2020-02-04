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
import gamevaluate.controller.gestionericerca.Search;

class RicercaGiocoTest extends Mockito{

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws IOException, ServletException {
		System.out.println("Test Search Game");
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		
		when(request.getParameter("valutazione")).thenReturn("1");
		when(request.getParameter("titolo")).thenReturn("1");
		when(request.getParameter("target")).thenReturn("giochi");
		when(request.getParameter("genere")).thenReturn("Tutti");
		when(request.getParameter("piattaforma")).thenReturn("Tutte");
		when(request.getParameter("value")).thenReturn("Halo");
		
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		
		when(response.getWriter()).thenReturn(out);
		
		new Search().doGet(request, response);
		
		assertTrue(writer.toString().contains("\"id\":9"));
	}

}
