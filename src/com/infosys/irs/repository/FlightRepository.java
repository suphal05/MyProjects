package com.infosys.irs.repository;

import java.util.Calendar;
import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.irs.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String> {	 
	@Query("select f from FlightEntity f where f.source=:source and f.destination=:dest and f.flightAvailableDate=:jdate and f.seatCount>0")
	List<FlightEntity> findFlightDetails(@Param("source") String source, @Param("dest") String destination,
			@Param("jdate") Calendar date);

	@Query("select f from FlightEntity f where (f.source=:source and f.destination=:dest and f.flightAvailableDate=:jdate) or (f.source=:dest and f.destination=:source and f.flightAvailableDate=:rdate) and f.seatCount>0")
	List<FlightEntity> findFlightDetailsRound(@Param("source") String source, @Param("dest") String destination,
			@Param("jdate") Calendar jdate, @Param("rdate") Calendar rdate);
	
	
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value="update FlightEntity set seatCount=seatCount-:noOfSeats where flightId=:flightId")
	void updateSeatsDetails(@Param("flightId") String flightId, @Param("noOfSeats") int noOfSeats);
	 
	 
	 
	 

		@Query("select f from FlightEntity f where f.flightId=:flightId")
		FlightEntity findFlight(@Param("flightId") String flightId);

		@Query("select distinct(f.source) from FlightEntity f")
		List<String> findFlightSources();

	@Query("select distinct(f.destination) from FlightEntity f")
	List<String> findFlightDestinations();
	 
}
