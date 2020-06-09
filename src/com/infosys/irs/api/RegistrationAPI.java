package com.infosys.irs.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.irs.exception.UserIdAlreadyPresentException;
import com.infosys.irs.model.User;
import com.infosys.irs.service.RegistrationService;
@RestController
@RequestMapping("RegisterAPI")
public class RegistrationAPI {
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private Environment environment;

	
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> addUser( @RequestBody User user) throws Exception {
		System.out.println("print this");
		String returnMessage = null;
		HttpStatus status=null;
		try{
			System.out.println("print this1");
			registrationService.registerUser(user);
			System.out.println("print this2");
			returnMessage = environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION");
			System.out.println("print this3");
			status = HttpStatus.OK;
			System.out.println("print this4");
		
		}catch(UserIdAlreadyPresentException e){
			if (e.getMessage().contains("RegistrationService")) {
				returnMessage = environment.getProperty( e.getMessage());
				status = HttpStatus.BAD_REQUEST;

			}
		}
		return new ResponseEntity<String>(returnMessage, status);

	}

}
