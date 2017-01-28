package controller;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import enterprise.service.CustomerManager;
import enterprise.service.exceptions.CustomerEmailException;
import enterprise.service.exceptions.CustomerEntityExistsException;
import enterprise.service.exceptions.CustomerPasswordException;
import model.Customer;

public class CustomerController {
	private InitialContext ctx;
	private CustomerManager customerEJB;

	public CustomerController(InitialContext context) {
		this.ctx=context;
		try {
			customerEJB = (CustomerManager) ctx.lookup("enterprise.service.CustomerManager");

		} catch (NamingException nex) {
			nex.printStackTrace();
		}
	}

	public List<Customer> getAllCustomer(){
		List<Customer> liste = customerEJB.getAllCustomer();
		return liste;

	}
}
