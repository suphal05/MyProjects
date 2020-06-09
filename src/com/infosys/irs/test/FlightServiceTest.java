package com.infosys.irs.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

import com.infosys.irs.entity.FlightEntity;
import com.infosys.irs.exception.ARSServiceException;
import com.infosys.irs.exception.FlightNotAvailableException;
import com.infosys.irs.exception.InvalidJourneyDateException;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.repository.FlightRepository;
import com.infosys.irs.service.FlightService;
import com.infosys.irs.utility.CalendarUtility;

@ContextConfiguration
public class FlightServiceTest {

	@Mock
	FlightRepository flightRepository;
	@InjectMocks
	FlightService flightService;
	@Rule
	public ExpectedException e = ExpectedException.none();
	List<String> sources = new ArrayList<>();
	List<String> destinations = new ArrayList<>();
	List<FlightEntity> flights = new ArrayList<>();
	Calendar calendar = Calendar.getInstance();
	FlightEntity flightEntity =null;
	public void initializeSourcesDestinations()
	{
		sources.add("Hyderabad");
		sources.add("Chennai");
		sources.add("Bangalore");
		destinations.add("Trivandrum");
		destinations.add("Coimbatore");
		destinations.add("Kochi");
	}
	public void initializeFlightEntity()
	{
		calendar.set(2017, 05, 05);
		flightEntity = new FlightEntity();
		flightEntity.setDepartureTime("1:00");
		flightEntity.setDestination("chennai");
		flightEntity.setFare(1000.0);
		flightEntity.setFlightAvailableDate(calendar);
		flightEntity.setSeatCount(3);
		flightEntity.setSource("hyderabad");
		flights.add(flightEntity);
	}
	@Before
	public void intialWork()
	{
		MockitoAnnotations.initMocks(this);
		initializeSourcesDestinations();
		initializeFlightEntity();
	}
	@Test
	public void testGetSourcesPositive() 
	{
		List<String> sourcesReturned=null;
		Mockito.when(flightRepository.findFlightSources()).thenReturn(sources);
       	try
     	{
       		sourcesReturned = flightService.getSources();
     	}
     	catch(Exception e){}
     	assertThat(sourcesReturned).isEqualTo(sources);
	}
	@Test
	public void testGetDestinationsPositive() 
	{
		List<String> destinationsReturned=null;
		Mockito.when(flightRepository.findFlightDestinations()).thenReturn(destinations);
       	try
     	{
       		destinationsReturned = flightService.getDestinations();
     	}
     	catch(Exception e){}
     	assertThat(destinationsReturned).isEqualTo(destinations);
	}
	@Test
	public void testGetFlightsPositive() 
	{
		List<SearchFlights> flightsReturned=null;
		List<SearchFlights>flightsActual = new ArrayList<>();
		Calendar calendarJourneyDateInside = Calendar.getInstance();
		calendarJourneyDateInside.set(2017, 12, 12);
		Mockito.when(flightRepository.findFlightDetails("hyderabad","chennai",calendarJourneyDateInside)).thenReturn(flights);
		try
     	{
       		flightsReturned = flightService.getFlights("hyderabad","chennai", calendarJourneyDateInside);
       		
       		for (FlightEntity f : flights) {
       			SearchFlights flight = new SearchFlights();
    			flight.setFlightId(f.getFlightId());
    			flight.setSource(f.getSource());
    			flight.setDestination(f.getDestination());
    			flight.setFlightAvailableDate(CalendarUtility.getStringFromCalendar(f.getFlightAvailableDate()));
    			flight.setDepartureTime(f.getDepartureTime());
    			flight.setSeatCount(f.getSeatCount().toString());
    			flight.setAirlines(f.getAirlines());
    			flight.setFare(Double.toString(f.getFare()));
    			flightsActual.add(flight);
			}
     	}
     	catch(Exception e){}
        assertThat(flightsReturned).isEqualTo(flightsActual);
	}
	@Test
	public void testGetFlightsNegativeInvalidDate() throws FlightNotAvailableException, InvalidJourneyDateException, Exception
	{
		e.expect(InvalidJourneyDateException.class);
		e.expectMessage("FlightService.INVALID_JOURNEYDATE");
		flightService.getFlights("hyderabad", "chennai", calendar);
	}
	@Test
	public void testGetFlightsNegativeNotAvailable() throws FlightNotAvailableException, InvalidJourneyDateException,Exception
	{
		Calendar calendarJourneyDate = Calendar.getInstance();
		calendarJourneyDate.set(2017, 12, 12);
		e.expect(FlightNotAvailableException.class);
		e.expectMessage("FlightService.FLIGHT_NOT_AVAILABLE");
		flightService.getFlights("hyderabad", "chennai", calendarJourneyDate);
	}
	@Test
	public void testUpdateFlightPositive()
	{
		Mockito.when(flightRepository.findOne(Mockito.anyString())).thenReturn(flightEntity);
		FlightEntity flightEntityReturned = flightRepository.findOne(Mockito.anyString());
		int count = flightEntityReturned.getSeatCount() - Integer.valueOf(Mockito.anyInt());
		flightEntityReturned.setSeatCount(count);
		Mockito.when(flightRepository.saveAndFlush(flightEntityReturned)).thenReturn(flightEntityReturned);
		try
		{
			flightService.updateFlight(Mockito.anyString(), Mockito.anyString());
		}
		catch(Exception e){}
	}
	@Test
	public void testUpdateFlightNegative() throws ARSServiceException
	{
		e.expect(ARSServiceException.class);
		e.expectMessage("No flight for the given details");
		Mockito.when(flightRepository.findOne(Mockito.anyString())).thenReturn(null);
		flightService.updateFlight("flightid", "3");
	}
	

}
