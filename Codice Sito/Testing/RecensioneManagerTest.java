package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gamevaluate.bean.Recensione;
import gamevaluate.model.RecensioneManager;

class RecensioneManagerTest {
	static RecensioneManager model = new RecensioneManager();

	@Test
	void testDoRetrieveAll() throws SQLException {
		System.out.println("Test doRetrieveAll, RecensioneManager");
		
		ArrayList<Recensione> recensioni = (ArrayList<Recensione>) model.doRetrieveAll("");
		int oldlength = recensioni.size();
		
		Recensione recensione = new Recensione();
		recensione.setTesto("Testo");
		recensione.setGioco(10);
		recensione.setUsername("admin");
		
		model.doSave(recensione);
		
		recensioni = (ArrayList<Recensione>) model.doRetrieveAll("");
		
		int newlength = recensioni.size();
		
		assertEquals((oldlength + 1), newlength);
		
		Recensione last = recensioni.get(recensioni.size()-1);
		System.out.println(last.getTesto());
		
		model.doDelete(last.getData(), last.getGioco(), last.getUsername());
		System.out.println("\n");
	}

	@Test
	void testDoSave() throws SQLException {
		System.out.println("Test doSave/doRetrieveByKey, RecensioneManager");
		
		Recensione recensione = new Recensione();
		recensione.setTesto("Testo");
		recensione.setGioco(10);
		recensione.setUsername("admin");
		
		model.doSave(recensione);
		
		ArrayList<Recensione> recensioni = (ArrayList<Recensione>) model.doRetrieveAll("");
		Recensione result = recensioni.get(recensioni.size()-1);
		
		assertEquals(recensione.getTesto(), result.getTesto());
		
		System.out.println(result.getTesto());
		model.doDelete(result.getData(), result.getGioco(), result.getUsername());
		System.out.println("\n");
	}

	@Test
	void testDoDelete() throws SQLException {
System.out.println("Test doDelete, RecensioneManager");
		
		Recensione recensione = new Recensione();
		recensione.setTesto("Testo");
		recensione.setGioco(10);
		recensione.setUsername("admin");
		
		model.doSave(recensione);
		
		ArrayList<Recensione> recensioni = (ArrayList<Recensione>) model.doRetrieveAll("");
		Recensione result = recensioni.get(recensioni.size()-1);
		
		model.doDelete(result.getData(), result.getGioco(), result.getUsername());
		
		result = model.doRetrieveByKey(result.getGioco(), result.getUsername(), result.getData());
		
		assertEquals(null, result.getTesto());
		
		System.out.println("\n");
	}

}
