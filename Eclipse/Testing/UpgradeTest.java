package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import gamevaluate.bean.GeneralUser;
import gamevaluate.controller.gestioneUtenti.Ban;
import gamevaluate.controller.gestioneUtenti.Upgrade;

class UpgradeTest extends Mockito{

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("Test Upgrade");
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getParameter("username")).thenReturn("user");
		when(request.getSession()).thenReturn(session);
		
		ArgumentCaptor<GeneralUser> captor = ArgumentCaptor.forClass(GeneralUser.class);
		ArgumentCaptor<String> stringcaptor = ArgumentCaptor.forClass(String.class);
		
		new Upgrade().doGet(request, response);
		
		verify(session).setAttribute(stringcaptor.capture(),captor.capture());
		assertEquals(2, captor.getValue().getRole());
		
		System.out.println("\n");
	}


}
