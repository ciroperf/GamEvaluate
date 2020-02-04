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

import gamevaluate.bean.GeneralUser;
import gamevaluate.controller.gestioneRecensioni.AddReview;

class AggiungiRecensioneTest extends Mockito {

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("Test AddReview");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("testo")).thenReturn("Testo Recensione");
		when(request.getParameter("idGioco")).thenReturn("10");
		GeneralUser user = new GeneralUser();
		user.setUsername("admin");
		when(session.getAttribute("user")).thenReturn(user);
		when(request.getSession()).thenReturn(session);
	
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		new AddReview().doPost(request, response);
		
		verify(session).setAttribute(captor.capture(), captor.capture());
		
		assertEquals("Recensione inserita", captor.getAllValues().get(1));
	}
}
