package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import gamevaluate.bean.Valutazione;
import gamevaluate.model.ValutazioneManager;

class ValutazioneManagerTest {

	static ValutazioneManager model = new ValutazioneManager();


	@Test
	void testDoSave() throws SQLException {
		System.out.println("Test doSave/doRetrieveByKey, ValutazioneManager");
		
		Valutazione valutazione = new Valutazione();
		valutazione.setId(20);
		valutazione.setCounter(0);
		valutazione.setDifficolta(7);
		model.doSave(valutazione);
		
		Valutazione result = model.doRetrieveByKey(20);
		
		assertEquals(7, result.getDifficolta());
		
		model.doDelete(20);
		System.out.println("\n");
		
	}

	@Test
	void testDoUpdate() throws SQLException {
		System.out.println("Test doUpdate, ValutazioneManager");
		
		Valutazione valutazione = new Valutazione();
		valutazione.setId(20);
		valutazione.setCounter(0);
		valutazione.setDifficolta(7);
		model.doSave(valutazione);
		
		Valutazione result = model.doRetrieveByKey(20);
		result.setDifficolta(10);
		model.doUpdate(result);
		
		result = model.doRetrieveByKey(20);
		
		assertEquals(10, result.getDifficolta());
		
		model.doDelete(20);
		System.out.println("\n");
	}

}
