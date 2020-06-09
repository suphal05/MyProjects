package com.infosys.irs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.UserIdAlreadyPresentException;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void registerUser(User user) throws UserIdAlreadyPresentException{
		System.out.println("serv 1");
		UserEntity ue = userRepository.findOne(user.getUserId());
		System.out.println("serv 2");
		if(ue!=null)
			throw new UserIdAlreadyPresentException("RegistrationService.USERID_PRESENT");
		UserEntity userEntity = new UserEntity();
		System.out.println("serv 3");
		userEntity.setCity(user.getCity());
		System.out.println("serv 4");
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhone(user.getPhone());
		userEntity.setUserId(user.getUserId());
		userEntity.setDob(user.getDob());
		userRepository.saveAndFlush(userEntity);	
	
		
	}
	
	

	

}
