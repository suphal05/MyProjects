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
import static org.assertj.core.api.Assertions.assertThat;
import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.InvalidCredentialException;
import com.infosys.irs.model.Login;
import com.infosys.irs.repository.UserRepository;
import com.infosys.irs.service.LoginService;

@ContextConfiguration
public class LoginServiceTest 
{
	
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private LoginService loginService;
	private UserEntity user1; 
	private UserEntity user;
	private Login login; 
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	public void customer1()
	{
		login = new Login();
		login.setUserName("dummy1");
		login.setPassword("dummy1");
		user1 = new UserEntity();
		user1.setUserId("dummy1");
		user1.setPassword("dummy1");
		user1.setCity("dummy1");
		user1.setCity("dummy1");
		user1.setEmail("dummy1");
		user1.setPhone("dummy1");
	}
	
	@Before
	public void initializeCustomer()
	{
		MockitoAnnotations.initMocks(this);
		customer1();
	}
	  
	@Test
	public void testAuthenticateLoginNegative()throws InvalidCredentialException
	{
     	e.expect(InvalidCredentialException.class);
    	e.expectMessage("LoginService.INVALID_CREDENTIALS");
    	Mockito.when(userRepository.findOne(Mockito.anyString())).thenReturn(null);
    	loginService.authenticateLogin(login);
   	}
	
	@Test
	public void testAuthenticateLoginPositive()
	{
     	Mockito.when(userRepository.findOne(login.getUserName())).thenReturn(user1);
       	try
     	{
     		user = loginService.authenticateLogin(login);
     	}
     	catch(Exception e)
     	{
     		
     	}
     	assertThat(user).isEqualTo(user1);
   	}
}
