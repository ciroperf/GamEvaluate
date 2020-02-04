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

import gamevaluate.controller.gestioneGiochi.AddGame;

class AggiungiGiocoTest  extends Mockito{

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("Test AddGame");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("nome")).thenReturn("NewGame");
		when(request.getParameter("descrizione")).thenReturn("NewGameDescription");
		when(request.getParameter("genere")).thenReturn("FPS");
		when(request.getParameter("piattaforma")).thenReturn("PS4");
		when(request.getParameter("immagine")).thenReturn("amazon.com/images/I/51VZPPXPtkL._SX399_BO1,204,203,200_.jpg");
		when(request.getSession()).thenReturn(session);
	
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		new AddGame().doPost(request, response);
		
		verify(session).setAttribute(captor.capture(), captor.capture());
		
		assertEquals("gioco aggiunto", captor.getAllValues().get(1));
	}

}
