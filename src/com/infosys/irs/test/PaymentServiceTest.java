package com.infosys.irs.test;



import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import com.infosys.irs.entity.CreditCardEntity;
import com.infosys.irs.entity.FlightEntity;
import com.infosys.irs.entity.PassengerEntity;
import com.infosys.irs.entity.TicketEntity;
import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.CreditCardNotFoundException;
import com.infosys.irs.exception.InvalidCardDetailsException;
import com.infosys.irs.model.CreditCard;
import com.infosys.irs.repository.CreditCardRepository;
import com.infosys.irs.repository.FlightRepository;
import com.infosys.irs.repository.PassengerRepository;
import com.infosys.irs.repository.TicketRepository;
import com.infosys.irs.repository.UserRepository;
import com.infosys.irs.service.PaymentService;
@ContextConfiguration
public class PaymentServiceTest 
{
	@Mock
	private CreditCardRepository creditCardRepository;
	@Mock
	private FlightRepository flightRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private PassengerRepository passengerRepository;
	@InjectMocks
	private PaymentService paymentService;
	@Rule
	public ExpectedException e = ExpectedException.none();
	CreditCardEntity creditCardEntity = new CreditCardEntity();
	CreditCardEntity creditCardEntity1 = new CreditCardEntity();
	CreditCard creditCard = new CreditCard();
	UserEntity userEntity = new UserEntity();
	FlightEntity flightEntity = new FlightEntity();
	@Mock 
	HttpSession session;
	public void initialize()
	{
		creditCardEntity.setCardHolderName("Sangamithra");
		creditCardEntity.setCardNumber("12345678");
		creditCardEntity.setCvv("kkkkkkk");
		creditCardEntity.setExpiryMonth("MAY");
		creditCardEntity.setExpiryYear("2017");
		creditCardEntity.setSecurePin("8888");
		creditCardEntity.setTotalBill("300");
		creditCard.setCardHolderName("Sangamithra");
		creditCard.setCardNumber("12345678");
		creditCard.setCvv("kkkkkkk");
		creditCard.setExpiryMonth("MAY");
		creditCard.setExpiryYear("2017");
		creditCard.setSecurePin("8888");
		creditCard.setTotalBill("300");
		creditCardEntity1.setCardHolderName("Sangamithraa");
		creditCardEntity1.setCardNumber("12345679");
		creditCardEntity1.setCvv("kkkkkkl");
		creditCardEntity1.setExpiryMonth("MAY");
		creditCardEntity1.setExpiryYear("2018");
		creditCardEntity1.setSecurePin("8889");
		creditCardEntity1.setTotalBill("3000");
		userEntity.setUserId("dummy1");
		userEntity.setPassword("dummy1");
		userEntity.setCity("dummy1");
		userEntity.setCity("dummy1");
		userEntity.setEmail("dummy1");
		userEntity.setPhone("dummy1");
		flightEntity = new FlightEntity();
		flightEntity.setDepartureTime("1:00");
		flightEntity.setDestination("chennai");
		flightEntity.setFare(1000.0);
		flightEntity.setFlightAvailableDate(Calendar.getInstance());
		flightEntity.setSeatCount(3);
		flightEntity.setSource("hyderabad");
	}
	@Before
	public void initialWork()
	{
		MockitoAnnotations.initMocks(this);
		initialize();
	}
	@Test
	public void testFindCreditCardNegative1() throws CreditCardNotFoundException,InvalidCardDetailsException
	{
		e.expect(CreditCardNotFoundException.class);
		e.expectMessage("PaymentService.CREDITCARD_NOT_FOUND");
		Mockito.when(creditCardRepository.findOne(Mockito.anyString())).thenReturn(null);
		paymentService.findCreditCard(creditCard);
	}
	@Test
	public void testFindCreditCardNegative2() throws CreditCardNotFoundException,InvalidCardDetailsException
	{
		
		
		e.expect(InvalidCardDetailsException.class);
		e.expectMessage("PaymentService.INVALID_CARD_DETAILS");
		Mockito.when(creditCardRepository.findOne(Mockito.anyString())).thenReturn(creditCardEntity1);
		paymentService.findCreditCard(creditCard);
	}
	@Test
	public void testFindCreditCardPositive() 
	{
		Mockito.when(creditCardRepository.findOne(Mockito.anyString())).thenReturn(creditCardEntity1);
		try
		{
			paymentService.findCreditCard(creditCard);
		}
		catch(Exception e){}
	}
	@Test
	public void testUpdateCreditCard() 
	{
		
		Mockito.doNothing().when(creditCardRepository).updateSeatCount(Mockito.anyString(), Mockito.anyString());
		paymentService.updateCreditCard(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testConfirmBooking() 
	{
		
		TicketEntity ticketEntity = new TicketEntity();
		PassengerEntity passengerEntity = new PassengerEntity();
		Mockito.when(userRepository.findOne(Mockito.anyString())).thenReturn(userEntity);
		Mockito.when(flightRepository.findOne(Mockito.anyString())).thenReturn(flightEntity);
		Mockito.doNothing().when(flightRepository).updateSeatsDetails(Mockito.anyString(), Mockito.anyInt());
		Mockito.when(ticketRepository.saveAndFlush(ticketEntity)).thenReturn(ticketEntity);
		Mockito.when(passengerRepository.saveAndFlush(passengerEntity)).thenReturn(passengerEntity);
		try
		{
			paymentService.confirmBooking(session);
		}
		catch(Exception e){}
	}

}
