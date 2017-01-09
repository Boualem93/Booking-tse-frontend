package controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import enterprise.service.Register;
import enterprise.service.exceptions.CustomerEmailException;
import enterprise.service.exceptions.CustomerEntityExistsException;
import enterprise.service.exceptions.CustomerPasswordException;
import model.Customer;


public class registerController {

	private InitialContext ctx;
	private Register registerEJB;

	public registerController() {
		try {
			System.setProperty("java.security.auth.login.config", "C:\\appclientlogin.conf");
			/*
				Properties props = new Properties();
				props.load(new FileInputStream("jndi.properties"));
			 */
			Properties props = new Properties(); 
			props.setProperty("java.naming.factory.initial",
					"com.sun.enterprise.naming.SerialInitContextFactory");
			props.setProperty("java.naming.factory.url.pkgs",
					"com.sun.enterprise.naming");
			props.setProperty("java.naming.factory.state",
					"com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
			props.setProperty("org.omg.CORBA.ORBInitialHost", "ec2-35-157-0-118.eu-central-1.compute.amazonaws.com");
			props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
			InitialContext ctx = new InitialContext(props);
			registerEJB = (Register) ctx.lookup("enterprise.service.Register");

		} catch (NamingException nex) {
			nex.printStackTrace();
		}
	}

	public Customer register (String email, String mdp, String nom, String prenom) throws CustomerEntityExistsException, CustomerPasswordException, CustomerEmailException, NamingException{
		Customer client = null;
		client = registerEJB.createCustomer(email, mdp, nom, prenom);
		return client;

	}

	

	/*

        public static String createEvent() {
                try {
                        InitialContext ic = new InitialContext();
                        EventManager sless = (EventManager) ic.lookup("enterprise.service.EventManager");
                        Date date = new Date();
                        return (sless.createEvent("MHD", date, "C3").toString());
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        public static String createBooking() {
                try {
                        InitialContext ic = new InitialContext();
                        BookingManager sless = (BookingManager) ic.lookup("enterprise.service.BookingManager");
                        return (sless.addBooking(1, 1, "A1").toString());
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        public static String isSeatAvailableByID(int eventid, String seat) {
                try {
                        InitialContext ic = new InitialContext();
                        BookingManager sless = (BookingManager) ic.lookup("enterprise.service.BookingManager");
                        if(sless.isSeatAvailableByID(eventid, seat)){
                                return ("Seat "+ seat + " for Event" + eventid + " Available");
                        } else {
                                return ("Seat "+ seat + " for Event" + eventid + " not Available");
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        return "Error";
                }
        }



        public static String getAvailableSeat(int eventid, String seat){
                try {
                        InitialContext ic = new InitialContext();
                        BookingManager sless = (BookingManager) ic.lookup("enterprise.service.BookingManager");
                        return (sless.getAvailableSeat(eventid, seat));
                } catch (Exception e) {
                        e.printStackTrace();
                        return "Error";
                }
        }

	 */
}
