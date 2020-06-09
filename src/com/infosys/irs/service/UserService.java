package com.infosys.irs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.UserNotFoundException;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
public User getUserDetails(String userId) throws UserNotFoundException{
		
		
		UserEntity ue = userRepository.findOne(userId);
		
		
		
		
		if (ue == null){			
			throw new UserNotFoundException(
					"UserService.USER_NOT_FOUND");
		}
		User user = new User();
		user.setCity(ue.getCity());
		user.setEmail(ue.getEmail());
		user.setName(ue.getName());
		user.setPhone(ue.getPhone());
		user.setUserId(ue.getUserId());
		user.setDob(ue.getDob());
		
			return user;				
		

	}
	

}
