package com.infosys.irs.service;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.CreditCardEntity;
import com.infosys.irs.entity.FlightEntity;
import com.infosys.irs.entity.PassengerEntity;
import com.infosys.irs.entity.TicketEntity;
import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.CreditCardNotFoundException;
import com.infosys.irs.exception.InvalidCardDetailsException;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.CreditCard;
import com.infosys.irs.model.Passenger;
import com.infosys.irs.model.PassengerListContainer;
import com.infosys.irs.repository.CreditCardRepository;
import com.infosys.irs.repository.FlightRepository;
import com.infosys.irs.repository.PassengerRepository;
import com.infosys.irs.repository.TicketRepository;
import com.infosys.irs.repository.UserRepository;
import com.infosys.irs.utility.CalendarUtility;

@Service
public class PaymentService {
	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private PassengerRepository passengerRepository;
	
	public void findCreditCard(CreditCard creditCard) throws CreditCardNotFoundException,InvalidCardDetailsException{
		CreditCardEntity cce = (CreditCardEntity) creditCardRepository.findOne(creditCard.getCardNumber());
		if(cce==null){
			throw new CreditCardNotFoundException("PaymentService.CREDITCARD_NOT_FOUND");
		}
		if(!(cce.getCardHolderName().equalsIgnoreCase(creditCard.getCardHolderName()) && cce.getCvv().equals(creditCard.getCvv()) &&
				cce.getExpiryMonth().equalsIgnoreCase(creditCard.getExpiryMonth()) && cce.getExpiryYear().equals(creditCard.getExpiryYear()) &&
						cce.getSecurePin().equals(creditCard.getSecurePin()))){
			throw new InvalidCardDetailsException("PaymentService.INVALID_CARD_DETAILS");
			
		}
		
		
	}
	public void updateCreditCard(String cardNumber,String fare){
		
		 creditCardRepository.updateSeatCount(cardNumber, fare);
		
		
	}
	public void confirmBooking(HttpSession session) throws Exception{
		PassengerListContainer passengerListContainer = (PassengerListContainer)session.getAttribute("passengerListContainer");
		List<Passenger> passengerList = passengerListContainer.getPassengerList();
		Booking booking = (Booking)session.getAttribute("booking");
		UserEntity ue = userRepository.findOne(booking.getName());
		
		FlightEntity flightEntity = flightRepository.findOne(booking.getFlightId());
		flightRepository.updateSeatsDetails(booking.getFlightId(), booking.getSeats());
		TicketEntity te = new TicketEntity();
		
		te.setBookingDate(CalendarUtility.getStringFromCalendar(Calendar.getInstance()));
		te.setDepartureDate(booking.getDepartureDate());
		te.setDepartureTime(booking.getDepartureTime());
		te.setNoOfSeats(booking.getSeats());		
		te.setPnr(booking.getPnr().toString());
		te.setCustomer(ue);
		te.setFlight(flightEntity);
		te.setTotalFare(booking.getFare());
		ticketRepository.saveAndFlush(te);
		for (Passenger passenger : passengerList) {
			PassengerEntity pe = new PassengerEntity();
			pe.setPassengerAge(passenger.getAge());
			pe.setPassengerGender(passenger.getGender());
			pe.setPassengerName(passenger.getPassengerName());
			TicketEntity t = ticketRepository.findOne(booking.getPnr().toString());
			pe.setTicket(t);
			pe=passengerRepository.saveAndFlush(pe);
			
		}
		
	}
	


}
