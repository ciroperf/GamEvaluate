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
import gamevaluate.controller.gestionePiattaforme.AddPlatform;

class AggiungiPiattaformaTest extends Mockito{

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("Test AddPlatform");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("nome")).thenReturn("NewPlatform");
		when(request.getSession()).thenReturn(session);
	
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		new AddPlatform().doPost(request, response);
		
		verify(session).setAttribute(captor.capture(), captor.capture());
		
		assertEquals("Piattaforma Aggiunta", captor.getAllValues().get(1));
	}
}
