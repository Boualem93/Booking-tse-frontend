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

	public registerController(InitialContext context) {
		this.ctx=context;
		try {
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
