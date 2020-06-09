/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;

import java.util.Calendar;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.infosys.irs.exception.FlightNotAvailableException;
import com.infosys.irs.exception.InvalidJourneyDateException;
import com.infosys.irs.exception.InvalidSourceDestinationException;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.FlightService;
import com.infosys.irs.utility.CalendarUtility;

@Controller

public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private Environment environment;

	@RequestMapping(value = "/searchFlights", method = RequestMethod.POST)
	public ModelAndView searchFlights(@Valid @ModelAttribute("command") SearchFlights searchFlights,
			BindingResult bindingResult, ModelMap model) throws Exception {
		ModelAndView modelAndView = null;
		try {
			if (bindingResult.hasErrors()) {
				modelAndView = new ModelAndView("searchFlights", "command", searchFlights);

			} else {

				String source = searchFlights.getSource();
				String destination = searchFlights.getDestination();
				List<SearchFlights> availableFlights;
				Calendar journeyDate = CalendarUtility.getCalendarFromString(searchFlights.getJourneyDate());
				
				if (searchFlights.getRadioCheck().equals("oneWay")) {
					availableFlights = flightService.getFlights(source, destination, journeyDate, null);
				} else {
					Calendar returnDate = CalendarUtility.getCalendarFromString(searchFlights.getReturnDate());

					availableFlights = flightService.getFlights(source, destination, journeyDate, returnDate);
				}

				model.addAttribute("availableFlights", availableFlights);
				model.addAttribute("size", availableFlights.size());

				modelAndView = new ModelAndView("searchFlights", "command", searchFlights);
			}
		} catch (FlightNotAvailableException | InvalidJourneyDateException | InvalidSourceDestinationException e) {
			if (e.getMessage().contains("FlightService")) {
				modelAndView = new ModelAndView("searchFlights");

			}
			modelAndView.addObject("message", environment.getProperty(e.getMessage()));
		}
		List<String> s1 = flightService.getSources();
		model.addAttribute("sourceList", s1);

		// Select destination values from db
		List<String> s2 = flightService.getDestinations();
		model.addAttribute("destinationList", s2);
		return modelAndView;
	}

	@RequestMapping(value = "/modifyBooking", method = RequestMethod.GET)
	public ModelAndView authenticateLogin(ModelMap model) {

		// Select source values from db
		List<String> s1 = flightService.getSources();

		model.addAttribute("sourceList", s1);

		// Select destination values from db
		List<String> s2 = flightService.getDestinations();

		model.addAttribute("destinationList", s2);

		ModelAndView modelAndView = new ModelAndView("searchFlights", "command", new SearchFlights());

		return modelAndView;

	}

}
