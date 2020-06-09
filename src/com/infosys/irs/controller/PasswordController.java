package com.infosys.irs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.irs.exception.UserNotFoundException;
import com.infosys.irs.model.Password;
import com.infosys.irs.service.PasswordService;

@Controller
public class PasswordController {
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private Environment environment;
	
    @RequestMapping("/forgotPassword")
    public ModelAndView initializeForm()
    {
          return new ModelAndView("forgotPassword","fp",new Password());
    }
    
    @RequestMapping(value="/resetPassword",method=RequestMethod.POST)
    public ModelAndView updatePassword(@Valid @ModelAttribute("fp")Password fp,BindingResult bindingResult,ModelMap model)
    {
        
         
          if (bindingResult.hasErrors()) {
                return new ModelAndView("forgotPassword", "fp", fp);

          } 
        
              
                String email=fp.getEmail();
                String phone=fp.getPhone();
                String password=fp.getNewPassword();
              
               
                int rowUpdate;
               
                try {
                	rowUpdate=passwordService.updatePassword(email,phone,password);
                	if(rowUpdate==1)
                		model.addAttribute("successMessage",environment.getProperty("PasswordController.SUCCESSFUL_RESET"));
		             return new ModelAndView("forgotPassword", "fp", fp);
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					ModelAndView modelAndView = new ModelAndView("forgotPassword");
					modelAndView.addObject("message", environment.getProperty(e.getMessage()));
					return modelAndView;				
					}
                            
        
       
                                        
   
    }



}
