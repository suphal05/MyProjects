/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.FlightService;
import com.infosys.irs.utility.CalendarUtility;

@RestController
@RequestMapping("FlightAPI")
public class FlightAPI {

	@Autowired
	private FlightService flightService;

	@RequestMapping(value = "/{source}/{destination}/{journeyDate}", method = RequestMethod.GET, headers = "Accept=application/json")

	public ResponseEntity<List<SearchFlights>> searchFlights(@PathVariable String source,
			@PathVariable String destination, @PathVariable String journeyDate) throws Exception {

		Calendar jDate = CalendarUtility.getCalendarFromString(journeyDate);
		

		List<SearchFlights> availableFlights = flightService.getFlights(source, destination, jDate, null);

		return new ResponseEntity<List<SearchFlights>>(availableFlights, HttpStatus.OK);
	}
	
	//API for Return Journey
	@RequestMapping(value = "/{source}/{destination}/{journeyDate}/{returnDate}", method = RequestMethod.GET, headers = "Accept=application/json")

	public ResponseEntity<List<SearchFlights>> searchFlights(@PathVariable String source,
			@PathVariable String destination, @PathVariable String journeyDate, @PathVariable String returnDate) throws Exception {

		Calendar jDate = CalendarUtility.getCalendarFromString(journeyDate);
		Calendar rDate = CalendarUtility.getCalendarFromString(returnDate);

		List<SearchFlights> availableFlights = flightService.getFlights(source, destination, jDate, rDate);

		return new ResponseEntity<List<SearchFlights>>(availableFlights, HttpStatus.OK);
	}

	@RequestMapping(value = "/source", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<String>> getSources() throws Exception {

		try {
			List<String> s1 = flightService.getSources();
			return new ResponseEntity<List<String>>(s1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@RequestMapping(value = "/destination", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<String>> getDestinations() throws Exception {

		List<String> s2 = flightService.getDestinations();
		return new ResponseEntity<List<String>>(s2, HttpStatus.OK);
	}
}
