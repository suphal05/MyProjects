/**
 * 
 */
package com.infosys.irs.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.InvalidCredentialException;
import com.infosys.irs.model.Login;
import com.infosys.irs.repository.UserRepository;


/**
 * The Class AadharService.
 */
@Service

public class LoginService {

	@Autowired
	private UserRepository userRepository;	
	
	
	public UserEntity authenticateLogin(Login userLogin) throws InvalidCredentialException{
		
		
		UserEntity user = userRepository.findOne(userLogin.getUserName());
		
		
		
		
		if (user == null){			
			throw new InvalidCredentialException(
					"LoginService.INVALID_CREDENTIALS");
		}
		else if(!(user.getPassword().equals(userLogin.getPassword()))){
			throw new InvalidCredentialException(
					"LoginService.INVALID_CREDENTIALS");
		}
	
			return user;				
		

	}
	

	
	
}
