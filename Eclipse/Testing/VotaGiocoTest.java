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
import gamevaluate.controller.gestioneGiochi.DelGame;
import gamevaluate.controller.gestioneGiochi.VoteGame;

class VotaGiocoTest extends Mockito{

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("Test VoteGame");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("id_gioco")).thenReturn("10");
		when(request.getParameter("gameplay")).thenReturn("6");
		when(request.getParameter("trama")).thenReturn("6");
		when(request.getParameter("grafica")).thenReturn("6");
		when(request.getParameter("creativita")).thenReturn("6");
		when(request.getParameter("innovazione")).thenReturn("6");
		when(request.getParameter("coinvolgimento")).thenReturn("6");
		when(request.getParameter("realismo")).thenReturn("6");
		when(request.getParameter("rigiocabilita")).thenReturn("6");
		when(request.getParameter("difficolta")).thenReturn("6");
		GeneralUser user = new GeneralUser();
		user.setUsername("admin");
		when(session.getAttribute("user")).thenReturn(user);
		when(request.getSession()).thenReturn(session);
	
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		new VoteGame().doPost(request, response);
		
		verify(session).setAttribute(captor.capture(), captor.capture());
		
		assertEquals("Valutazione inserita", captor.getAllValues().get(1));
	}

}
