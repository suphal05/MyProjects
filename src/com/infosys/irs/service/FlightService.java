/**
 * 
 */
package com.infosys.irs.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.FlightEntity;
import com.infosys.irs.exception.ARSServiceException;
import com.infosys.irs.exception.FlightNotAvailableException;
import com.infosys.irs.exception.InvalidJourneyDateException;
import com.infosys.irs.exception.InvalidSourceDestinationException;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.repository.FlightRepository;
import com.infosys.irs.utility.CalendarUtility;;

/**
 * The Class AadharService.
 */
@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightsRepository;
	 protected String baseUrl;

	public List<String> getSources() {
		List<String> sources = flightsRepository.findFlightSources();
		
			return sources;
		
	}
	
	public List<String> getDestinations() {
		List<String> destinations = flightsRepository.findFlightDestinations();
		
			return destinations;
		
	}

	public  List<SearchFlights> getFlights(String source, String destination, Calendar journeyDate, Calendar returnDate) throws InvalidSourceDestinationException,FlightNotAvailableException, InvalidJourneyDateException, Exception	 {
		
		Calendar today = Calendar.getInstance();
		if(today.after(journeyDate))
			throw new InvalidJourneyDateException("FlightService.INVALID_JOURNEYDATE");
		if(source.equalsIgnoreCase(destination)){
		
			
			throw new InvalidSourceDestinationException("FlightService.INVALID_SOURCE_DESTINATION");
		}
		List<FlightEntity> flights = null;
		if(returnDate== null)
		{
		 flights = flightsRepository.findFlightDetails(source, destination, journeyDate);
		}else {
		 flights = flightsRepository.findFlightDetailsRound(source, destination, journeyDate, returnDate);
		}
		 
		if (flights == null || flights.isEmpty()) {
			throw new FlightNotAvailableException("FlightService.FLIGHT_NOT_AVAILABLE");
		} 
		
		List<SearchFlights> availableFlights = new ArrayList<SearchFlights>();
		for (FlightEntity f : flights) {
			SearchFlights flight = new SearchFlights();
			flight.setFlightId(f.getFlightId());
			flight.setSource(f.getSource());
			flight.setDestination(f.getDestination());
			flight.setFlightAvailableDate(CalendarUtility.getStringFromCalendar(f.getFlightAvailableDate()));
			flight.setDepartureTime(f.getDepartureTime());
			flight.setArrivalTime(f.getArrivalTime());
			flight.setSeatCount(f.getSeatCount().toString());
			flight.setAirlines(f.getAirlines());
			flight.setFare(Double.toString(f.getFare()));
			availableFlights.add(flight);
			
		}
			return availableFlights;
		
		

	}

	
	public void updateFlight(String flightId, String noOfSeats) throws ARSServiceException {
		FlightEntity flight = flightsRepository.findOne(flightId);
		if(flight == null){
			throw new ARSServiceException("No flight for the given details");
		}
		else{
			
			int count = flight.getSeatCount() - Integer.valueOf(noOfSeats);
			flight.setSeatCount(count);
			flightsRepository.saveAndFlush(flight);
			
			
			
		}
		
		
	}
	
	
	
	public void updateSeatsDetails(HttpServletRequest request,String flightId, int noOfSeats) {
		if (baseUrl == null) {
			getBaseUrl(request);
		}
		//String url = baseUrl + "/flights/" + flightId + "/" + noOfSeats + "/";

		//restTemplate.put(url, flightId, noOfSeats);

	}

	public void reliable(String flightId, int noOfSeats) {

	}

	private void getBaseUrl(HttpServletRequest request) {
		
		this.baseUrl= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		/*List<ServiceInstance> instances = discoveryClient.getInstances("flights-microservice");
		System.out.println(instances.size());
		ServiceInstance serviceInstance = instances.get(0);
		System.out.println(serviceInstance);
		baseUrl = serviceInstance.getUri().toString();*/

		this.baseUrl = baseUrl.startsWith("http") ? baseUrl : "http://" + baseUrl;
	}

}
