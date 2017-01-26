package controller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import enterprise.service.EventManager;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import enterprise.service.Register;
import enterprise.service.exceptions.CustomerEmailException;
import enterprise.service.exceptions.CustomerEntityExistsException;
import enterprise.service.exceptions.CustomerPasswordException;
import enterprise.service.exceptions.EventCategoryException;
import enterprise.service.exceptions.EventNameException;
import model.Event;


public class CreateEventController {

	private InitialContext ctx;
	private EventManager eventManagerEJB;

	public CreateEventController(InitialContext context) {
		this.ctx=context;
		try {
			
			eventManagerEJB = (EventManager) ctx.lookup("enterprise.service.EventManager");

		} catch (NamingException nex) {
			nex.printStackTrace();
		}
	}

	public Event create (String name, Date date, String categoryname) throws EventNameException, EventCategoryException{
		Event event = null;
		event = eventManagerEJB.createEvent(name, date, categoryname);
		return event;

	}
	
	public List<Event> allEvents(){
		List<Event> liste = eventManagerEJB.showAllEvent();
		return liste;
		
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
