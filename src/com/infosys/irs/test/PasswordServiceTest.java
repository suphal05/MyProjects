package com.infosys.irs.test;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import com.infosys.irs.exception.UserNotFoundException;
import com.infosys.irs.repository.UserRepository;
import com.infosys.irs.service.PasswordService;

@ContextConfiguration
public class PasswordServiceTest 
{
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private PasswordService passwordService;
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	@Before
	public void initialize()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAuthenticateLoginNegative()throws UserNotFoundException
	{
		e.expect(UserNotFoundException.class);
    	e.expectMessage("PasswordService.USER_NOT_FOUND");
    	Mockito.when(userRepository.updatePassword("punithamalar","1234567890","punithamalar_b")).thenReturn(0);
    	passwordService.updatePassword("punithamalar","1234567890","punithamalar_b");
    }
	
	@Test
	public void testAuthenticateLoginPositive()
	{
		 Mockito.when(userRepository.updatePassword("punithamalar","1234567890","punithamalar_b")).thenReturn(1);
		try
     	{
			passwordService.updatePassword("punithamalar","1234567890","punithamalar_b");
     	}
     	catch(Exception e)
     	{
     	
     	}
		
		
   	}

}
