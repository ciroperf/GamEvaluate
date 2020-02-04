package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gamevaluate.bean.Genere;
import gamevaluate.model.GenereManager;

class GenereManagerTest {

	@Test
	void testDoRetrieveAll() throws SQLException {
		System.out.println("Test doRetrieveAll, GenereManager");
		GenereManager model = new GenereManager();
		ArrayList<Genere> generi = (ArrayList<Genere>) model.doRetrieveAll("");
		
		int oldLength = generi.size();
		
		Genere genere = new Genere();
		genere.setDescrizione("Descrizione");
		genere.setNome("Nome");
		
		model.doSave(genere);
		
		generi = (ArrayList<Genere>) model.doRetrieveAll("");
		
		int newLength = generi.size();
		
		assertEquals((oldLength + 1), newLength);
		
		model.doDelete("Nome");
		System.out.println("\n");
	}

	@Test
	void testDoSaveDoRetrieveByKey() throws SQLException {
		System.out.println("Test doSave/DoRetrieveByKey, GenereManager");
		
		GenereManager model = new GenereManager();
		
		Genere genere = new Genere();
		genere.setDescrizione("Descrizione");
		genere.setNome("Nome");
		
		model.doSave(genere);
		
		Genere result = model.doRetrieveByKey("Nome");
		
		assertEquals("Nome", result.getNome());
		
		model.doDelete("Nome");
		System.out.println("\n");
	}

	@Test
	void testDoDelete() throws SQLException {
		System.out.println("Test doDelete, GenereManager");
		
		GenereManager model = new GenereManager();
		
		Genere genere = new Genere();
		genere.setDescrizione("Descrizione");
		genere.setNome("Nome");
		
		model.doSave(genere);
		
		Genere result = model.doRetrieveByKey("Nome");
		
		model.doDelete(result.getNome());
		
		result = model.doRetrieveByKey("Nome");
		
		assertEquals(null, result.getNome());
		
		System.out.println("\n");
	}

}
