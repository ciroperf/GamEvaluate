package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gamevaluate.bean.Gioco;
import gamevaluate.model.GiocoManager;


class GiocoManagerTest {
	static GiocoManager model = new GiocoManager();

	@Test
	void testDoRetrieveAll() throws SQLException {
		System.out.println("Test doRetreieveAll, GiocoManager");
		
		ArrayList<Gioco> giochi = (ArrayList<Gioco>) model.doRetrieveAll("");
		int oldlenght = giochi.size();
		
		Gioco gioco = new Gioco();
		gioco.setNome("Gioco");
		gioco.setDescrizione("Descrizione");
		gioco.setImmagine("url");
		gioco.setGenere("FPS");
		gioco.setPiattaforma("PS4");
		
		model.doSave(gioco);
		
		giochi = (ArrayList<Gioco>) model.doRetrieveAll("");
		
		int newlength = giochi.size();
		
		assertEquals((oldlenght + 1), newlength);
		
		model.doDelete(model.getLastId());
		System.out.println("\n");
		
	}

	@Test
	void testDoSaveDoRetrieveByKey() throws SQLException {
		System.out.println("Test doSave/DoRetrieveByKey, GiocoManager");
		
		Gioco gioco = new Gioco();
		gioco.setNome("Gioco");
		gioco.setDescrizione("Descrizione");
		gioco.setImmagine("url");
		gioco.setGenere("FPS");
		gioco.setPiattaforma("PS4");
		
		model.doSave(gioco);
		
		Gioco result = model.doRetrieveByKey(model.getLastId());
		
		assertEquals("Gioco", result.getNome());
		
		model.doDelete(result.getId());
		System.out.println("\n");
		
	}


	@Test
	void testDoDelete() throws SQLException {
		System.out.println("Test doDelete, GiocoManager");
		
		Gioco gioco = new Gioco();
		gioco.setNome("Gioco");
		gioco.setDescrizione("Descrizione");
		gioco.setImmagine("url");
		gioco.setGenere("FPS");
		gioco.setPiattaforma("PS4");
		
		model.doSave(gioco);
		
		Gioco result = model.doRetrieveByKey(model.getLastId());
		model.doDelete(result.getId());
		
		result = model.doRetrieveByKey(result.getId());
		
		assertEquals(null, result.getNome());
		
		System.out.println("\n");
	}

}
