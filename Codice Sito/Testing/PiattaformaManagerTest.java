package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gamevaluate.bean.Piattaforma;
import gamevaluate.model.PiattaformaManager;;

class PiattaformaManagerTest {

	@Test
	void testDoRetrieveAll() throws SQLException {
		System.out.println("Test doRetrieveAll, PiattaformaManager");
		PiattaformaManager model = new PiattaformaManager();
		ArrayList<Piattaforma> piattaforme = (ArrayList<Piattaforma>) model.doRetrieveAll("");
		
		int oldLength = piattaforme.size();
		
		Piattaforma piattaforma = new Piattaforma();
		piattaforma.setNome("Nome");
		
		model.doSave(piattaforma);
		
		piattaforme = (ArrayList<Piattaforma>) model.doRetrieveAll("");
		
		int newLength = piattaforme.size();
		
		assertEquals((oldLength + 1), newLength);
		
		model.doDelete("Nome");
		System.out.println("\n");
	}

	@Test
	void testDoSaveDoRetrieveByKey() throws SQLException {
		System.out.println("Test doSave/DoRetrieveByKey, PiattaformaManager");
		
		PiattaformaManager model = new PiattaformaManager();
		
		Piattaforma piattaforma = new Piattaforma();
		piattaforma.setNome("Nome");
		
		model.doSave(piattaforma);
		
		Piattaforma result = model.doRetrieveByKey("Nome");
		
		assertEquals("Nome", result.getNome());
		
		model.doDelete("Nome");
		System.out.println("\n");
	}

	@Test
	void testDoDelete() throws SQLException {
		System.out.println("Test doDelete, PiattaformaManager");
		
		PiattaformaManager model = new PiattaformaManager();
		
		Piattaforma piattaforma = new Piattaforma();
		piattaforma.setNome("Nome");
		
		model.doSave(piattaforma);
		
		Piattaforma result = model.doRetrieveByKey("Nome");
		
		model.doDelete(result.getNome());
		
		result = model.doRetrieveByKey("Nome");
		
		assertEquals(null, result.getNome());
		
		System.out.println("\n");
	}

}
