package test;
import model.*;
import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.registerController;
import enterprise.service.exceptions.CustomerEmailException;
import enterprise.service.exceptions.CustomerEntityExistsException;
import enterprise.service.exceptions.CustomerPasswordException;

public class RegisterTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws NamingException, CustomerEntityExistsException, CustomerPasswordException, CustomerEmailException {
			
				InitialContext context = new InitialContext();
			
			registerController registerTest = new registerController(context);
				Customer c = registerTest.register("aa@hotmail.com", "123456a", "nom","prenom");
			assertTrue(c.getEmail().equals("aa@hotmail.com"));
	}

}
