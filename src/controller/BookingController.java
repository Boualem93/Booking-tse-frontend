package controller;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.event.Event;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import enterprise.service.BookingManager;
import model.Booking;
import model.Customer;

public class BookingController {

	

		private InitialContext ctx;
		private BookingManager bookingEJB;

		public BookingController(InitialContext context) {
			
			this.ctx=context;
			try {
				
				bookingEJB = (BookingManager) ctx.lookup("enterprise.service.BookingManager");

			} catch (NamingException nex) {
				nex.printStackTrace();
			}
		}
	public HashMap <String,Integer> getBookedSeats (int id ){
		HashMap<String,Integer> map = bookingEJB.bookedSeats(id);
		return map;
	}
	
	public List<Booking> getBookingList (Customer cus){
		List<Booking> liste = bookingEJB.getAllMyBookings(cus);
		return liste;
	}
}
	
	