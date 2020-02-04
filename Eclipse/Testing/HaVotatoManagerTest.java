package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import gamevaluate.model.HaVotatoManager;

class HaVotatoManagerTest {

	@Test
	void testSalvaVotazioneHaVotato() throws SQLException {
		System.out.println("Test salvaVotazione/HaVotato, HaVotatoManager");
		
		HaVotatoManager model = new HaVotatoManager();
		
		String username = "admin";
		int id_gioco = 10;
		
		model.salvaVotazione(username, id_gioco);
		
		boolean presente = model.haVotato(username, id_gioco);
		
		assertEquals(true, presente);
		
		System.out.println("\n");
	}

}
