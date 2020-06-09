package com.infosys.irs.test;



import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import com.infosys.irs.exception.PassengerDetailNotFoundException;
import com.infosys.irs.model.Passenger;
import com.infosys.irs.service.PassengerService;

@ContextConfiguration
public class PassengerServiceTest 
{
	@Mock
	private PassengerService passengerService;
	@Rule
	public ExpectedException e = ExpectedException.none();
	Passenger passenger = new Passenger();
	List<Passenger> passengerPositive = new ArrayList<>();
	List<Passenger> passengerNegative = new ArrayList<>();
	public void initializePassengers() 
	{
		passenger.setPassengerName("punithamalar");
		passenger.setGender("female");
		passenger.setAge(13);
		passengerPositive.add(passenger);
		passenger.setPassengerName(null);
		passenger.setGender("female");
		passenger.setAge(13);
		passengerNegative.add(passenger);
	}
	@Before
	public void initialize()
	{
		MockitoAnnotations.initMocks(this);
		initializePassengers();
	}
	@Test
	public void testValidatePassengerDetailsPositive() 
	{
		try
		{
			passengerService.validatePassengerDetails(passengerPositive);
		}
		catch(Exception e){}
	}
	@Test
	public void testValidatePassengerDetailsNegative() throws PassengerDetailNotFoundException
	{
		e.expect(PassengerDetailNotFoundException.class);
		Mockito.doThrow(PassengerDetailNotFoundException.class).when(passengerService).validatePassengerDetails(passengerNegative);
		passengerService.validatePassengerDetails(passengerNegative);
	}
}
