/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

public class SearchFlights {
	
	 @NotEmpty(message="Please enter the origin")
		@NotNull(message="Soource is mandatory")
	private String source;
	 
	 @NotEmpty(message="Please enter the destination")
	 @NotNull(message="Destination is mandatory")
	 private String destination;
	 @NotEmpty(message="Please enter the journey date")
	 @NotNull(message="Journey date is mandatory")
	 private String journeyDate;
	 @Value("${returnDate:#{null}}")
	 private String returnDate;
	 
	 private String radioCheck;
	 private String fare;
	 private String flightId;
	 private String flightAvailableDate;
	 private String seatCount;
	 private String departureTime;
	 private String arrivalTime;
	 private String airlines;
	 
	 public String getReturnDate() {
			return returnDate;
		}

		public void setReturnDate(String returnDate) {
			this.returnDate = returnDate;
		}
		
	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFlightAvailableDate() {
		return flightAvailableDate;
	}

	public void setFlightAvailableDate(String flightAvailableDate) {
		this.flightAvailableDate = flightAvailableDate;
	}

	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getRadioCheck() {
		return radioCheck;
	}

	public void setRadioCheck(String radioCheck) {
		this.radioCheck = radioCheck;
	}



	
	

	}
