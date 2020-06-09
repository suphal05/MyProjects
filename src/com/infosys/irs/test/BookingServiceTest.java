package com.infosys.irs.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.infosys.irs.entity.FlightEntity;
import com.infosys.irs.exception.PassengerDetailNotFoundException;
import com.infosys.irs.exception.SeatsNotAvaialbleException;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.Passenger;
import com.infosys.irs.model.PassengerListContainer;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.repository.FlightRepository;
import com.infosys.irs.service.BookingService;

import java.util.List;

public class BookingServiceTest {
	
	@Mock 
	private FlightRepository flightsRepository;
	
	@Mock
	private PassengerListContainer passengerListContainer1;
	
	@InjectMocks
	private BookingService bookingService; 
	
	private FlightEntity flight;
	private Booking booking;
	private PassengerListContainer passengerListContainer;
	private Passenger passenger;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Before
	public void initialise(){
		passenger = new Passenger();
		passengerListContainer = new PassengerListContainer();
		booking = new Booking();
		MockitoAnnotations.initMocks(this);
		flight = new FlightEntity();
		flight.setDepartureTime("5:15");
		flight.setDestination("Chennai");
		flight.setFare(2000.00);
		flight.setFlightAvailableDate(Calendar.getInstance());
		flight.setFlightId("B101");
		flight.setSeatCount(50);
		flight.setSource("Bangalore");
		passenger.setAge(3);
		passenger.setGender("Male");
		passenger.setPassengerName("Swamyyyy");
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		passengerListContainer.setPassengerList(passengerList);
		booking.setAirlines("KingFlights");
		booking.setDepartureDate("5:15");
		booking.setDepartureDate("02/12/2017");
		booking.setDestination("Hyderabad");
		booking.setFare("2000");
		booking.setFlightId("B101");
		booking.setName("MyFlight");
		booking.setPersonListContainer(passengerListContainer);
	}

	@Test
	public void testGetFlightDetails() throws Exception 
	{
		Mockito.when(flightsRepository.findOne(Mockito.anyString())).thenReturn(flight);
		SearchFlights result =  bookingService.getFlightDetails("B101");
		assertEquals("2000.0", result.getFare());
		assertEquals("Chennai", result.getDestination());
		assertEquals("Bangalore", result.getSource());
	}

	public void testBootTicketNegative1()throws PassengerDetailNotFoundException, SeatsNotAvaialbleException,Exception
	{ 
		exception.expect(PassengerDetailNotFoundException.class);
		exception.expectMessage("BookingService.PASSENGER_NOT_ADDED");
		bookingService.bookTicket(booking, passengerListContainer1);
	}
	public void testBootTicketNegative2()throws PassengerDetailNotFoundException, SeatsNotAvaialbleException,Exception
	{ 
		exception.expect(SeatsNotAvaialbleException.class);
		exception.expectMessage("BookingService.SEATS_NOT_AVAILABLE");
		bookingService.bookTicket(booking, passengerListContainer);
	}
	public void testBootTicketPositive()
	{ 
		Mockito.when(flightsRepository.findOne(Mockito.anyString())).thenReturn(flight);
		try
		{
			bookingService.bookTicket(booking, passengerListContainer);
		}
		catch(Exception e){}
		List<Passenger> passengerList = passengerListContainer.getPassengerList();
		assertThat(passengerList.size()).isGreaterThan(0);
	}
}
