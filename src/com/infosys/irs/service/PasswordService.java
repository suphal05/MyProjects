package com.infosys.irs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.exception.UserNotFoundException;
import com.infosys.irs.repository.UserRepository;

@Service
public class PasswordService {
	
	@Autowired
	private UserRepository userRepository;
	public int updatePassword(String email,String phone, String password) throws UserNotFoundException{
		int rowCount=0;
        
        //System.out.println("Inside updatePassword Service...... fp.getEmail():"+email);
		
		
         rowCount= userRepository.updatePassword(email,phone,password);
		if(rowCount!=1)
			throw new UserNotFoundException("PasswordService.USER_NOT_FOUND");
		return rowCount;
        
  }



}
