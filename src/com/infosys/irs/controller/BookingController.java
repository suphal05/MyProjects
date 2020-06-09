/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;



import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.infosys.irs.exception.PassengerDetailNotFoundException;
import com.infosys.irs.exception.SeatsNotAvaialbleException;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.CreditCard;
import com.infosys.irs.model.PassengerListContainer;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.BookingService;

@Controller
@SessionAttributes({"booking","userId"})
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private Environment environment;



	@RequestMapping(value = "/bookFlight", method = RequestMethod.GET)
	public ModelAndView proceed(ModelMap model,	@RequestParam("flightid") String flightId, HttpSession httpSession ) throws Exception {
		SearchFlights flight = bookingService.getFlightDetails(flightId);

		Booking booking = new Booking();
		booking.setDepartureDate(flight.getFlightAvailableDate());
		booking.setDepartureTime(flight.getDepartureTime());
		booking.setDestination(flight.getDestination());
		booking.setFare(flight.getFare());
		booking.setFlightId(flightId);
		booking.setSource(flight.getSource());
		booking.setAirlines(flight.getAirlines());
		booking.setSeats(Integer.parseInt(flight.getSeatCount()));
		booking.setName((String)httpSession.getAttribute("userId"));


		model.addAttribute("booking",booking);

		return new ModelAndView("bookingReview", "command", booking);
		//return new ModelAndView("addPassengers", "command", booking);
	}





	@RequestMapping(value = "/bookingProcess", method = RequestMethod.GET)
	public ModelAndView bookingProcess1(
			ModelMap model,  HttpSession session)  throws Exception{
		
		PassengerListContainer passengerListContainer = (PassengerListContainer)session.getAttribute("passengerListContainer");
		//List<Passenger> passengerList =((PassengerListContainer)session.getAttribute("passengerListContainer")).getPassengerList();

		Booking newBooking = (Booking)session.getAttribute("booking");
		ModelAndView modelAndView = new ModelAndView("payment", "command", new CreditCard());

		try {
			newBooking = bookingService.bookTicket(newBooking, passengerListContainer);

			session.setAttribute("booking", newBooking);
			modelAndView.addObject("booking",newBooking);
		} catch (SeatsNotAvaialbleException | PassengerDetailNotFoundException  e) {
			// TODO Auto-generated catch block
			modelAndView = new ModelAndView("addPassengers"); 	
			modelAndView.addObject("message", environment.getProperty(e.getMessage()));
		}
		
	
		return modelAndView;


	}

}
