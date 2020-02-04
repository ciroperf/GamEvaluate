package gamevaluate.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gamevaluate.bean.GeneralUser;
import gamevaluate.model.GeneralUserManager;
import gamevaluate.utilities.Md5Encoder;

class GeneralUserManagerTest {

	@Test
	void testDoRetrieveAll() throws SQLException, NoSuchAlgorithmException {
		System.out.println("Test doRetrieveAll, GeneralUserManager");
		GeneralUserManager model = new GeneralUserManager();
		ArrayList<GeneralUser> users = (ArrayList<GeneralUser>) model.doRetrieveAll("");
		int oldLength = users.size();
		
		GeneralUser utente = new GeneralUser();

		utente.setUsername("ciccio");
		utente.setEmail("ciccio@ciccio.it");
		Md5Encoder encoder = new Md5Encoder();
		String pwd = "ciao";
		pwd = encoder.encode("ciao");
		utente.setPassword(pwd);
		utente.setRole(1);
		utente.setBanned(false);
		
		model.doSave(utente);
		
		users = (ArrayList<GeneralUser>) model.doRetrieveAll("");
		
		int newLength = users.size();
		
		assertEquals((oldLength+1), newLength);
		
		model.doDelete(utente.getUsername());
		System.out.println("\n");
		
	}

	@Test
	void testDoSaveDoRetrieveByKey() throws NoSuchAlgorithmException, SQLException {
		System.out.println("Test doSave/DoRetrieveByKey, GeneralUserManager");
		GeneralUser utente = new GeneralUser();
		GeneralUserManager model = new GeneralUserManager();
		utente.setUsername("ciccio");
		utente.setEmail("ciccio@ciccio.it");
		Md5Encoder encoder = new Md5Encoder();
		String pwd = "ciao";
		pwd = encoder.encode("ciao");
		utente.setPassword(pwd);
		utente.setRole(1);
		utente.setBanned(false);
		
		model.doSave(utente);
		
		GeneralUser result = model.doRetrieveByKey("ciccio");
		
		assertEquals(utente, result);
		
		model.doDelete(utente.getUsername());
		System.out.println("\n");
	}

	@Test
	void testDoUpdate() throws NoSuchAlgorithmException, SQLException {
		System.out.println("Test doSave/DoUpdate, GeneralUserManager");
		
		GeneralUser utente = new GeneralUser();
		GeneralUserManager model = new GeneralUserManager();
		utente.setUsername("ciccio");
		utente.setEmail("ciccio@ciccio.it");
		Md5Encoder encoder = new Md5Encoder();
		String pwd = "ciao";
		pwd = encoder.encode("ciao");
		utente.setPassword(pwd);
		utente.setRole(1);
		utente.setBanned(false);
		
		model.doSave(utente);
		
		GeneralUser result = model.doRetrieveByKey("ciccio");
		String newpwd = "ciaociao";
		newpwd = encoder.encode(newpwd);
		result.setPassword(newpwd);
		
		model.doUpdate(result);
		
		result = model.doRetrieveByKey("ciccio");
		
		assertEquals(newpwd, result.getPassword());
		
		model.doDelete(result.getUsername());
		System.out.println("\n");
	}

}
