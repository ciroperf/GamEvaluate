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

import gamevaluate.controller.gestioneRecensioni.DelReview;

class EliminaRecensioneTest extends Mockito{

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("Test DelReview");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("username")).thenReturn("admin");
		when(request.getParameter("idGioco")).thenReturn("10");
		when(request.getParameter("data")).thenReturn("2020-02-04 21:01:33");
		when(request.getParameter("returnTo")).thenReturn("/GamEvaluate/presentation/user-info.jsp");
		when(request.getSession()).thenReturn(session);
	
		ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
		
		new DelReview().doPost(request, response);
		
		verify(session, times(2)).setAttribute(captor1.capture(), captor2.capture());
		
		assertEquals("Recensione eliminata", captor2.getAllValues().get(1));
	}

}
