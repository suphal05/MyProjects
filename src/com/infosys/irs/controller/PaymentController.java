/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.infosys.irs.exception.CreditCardNotFoundException;
import com.infosys.irs.exception.InvalidCardDetailsException;
import com.infosys.irs.model.CreditCard;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.User;
import com.infosys.irs.service.PaymentService;
import com.infosys.irs.service.UserService;


@Controller
@SessionAttributes("booking")
public class PaymentController {
	
	
	  
	 @Autowired
	private PaymentService paymentService;
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private Environment environment;
	  
	  
	  @RequestMapping(value = "/makePayment", method = RequestMethod.POST)
	    public ModelAndView  processPayment(@Valid @ModelAttribute("command") CreditCard creditCard,BindingResult bindingResult,
	            ModelMap model, @RequestParam("fare") String fare,HttpSession session) throws Exception {
	
	ModelAndView modelAndView = new ModelAndView("paymentSuccess","command",creditCard);
	    	if(bindingResult.hasErrors()){
	    		modelAndView= new ModelAndView("payment", "command",creditCard);
	    	}
	    	else{
	    	
	    	try {
	    		//Booking b = (Booking)session.getAttribute("booking");
				paymentService.findCreditCard(creditCard);
				
				//System.out.println("booking fare "+b.getFare());
				paymentService.updateCreditCard(creditCard.getCardNumber(),fare);
				
				paymentService.confirmBooking(session);
				modelAndView.addObject("paymentMessage", environment.getProperty("PaymentController.PAYMENT_SUCCESS"));
				modelAndView.addObject("pnrMessage", environment.getProperty("PaymentController.PNR_DETAIL"));
			} catch (CreditCardNotFoundException | InvalidCardDetailsException e) {
				// TODO Auto-generated catch block
				if (e.getMessage().contains("PaymentService")) {
					modelAndView = new ModelAndView("payment", "command",creditCard); 
					
				}
				
				modelAndView.addObject("message", environment.getProperty(e.getMessage()));
			}
	    	catch(Exception e){
				throw e;
			}

	    	}
	    	
	    	  return modelAndView;
	    	
	}
	  
	  
	  @RequestMapping(value="/downloadTicket.pdf", method = RequestMethod.GET)
	  public void downloadTicket(Model model,HttpSession session) throws Exception {
		  User user=new User();
		
		  String pnr = ((Booking)session.getAttribute("booking")).getPnr().toString();
		  String seats = ((Booking)session.getAttribute("booking")).getSeats().toString();
	     	 model.addAttribute("pnr", pnr);
	      	model.addAttribute("noOfSeats", seats);
	      	String userId = ((Booking)session.getAttribute("booking")).getName();
	       user = userService.getUserDetails(userId);
	      	
	          model.addAttribute("user", user.getName());
	 
	     
	  }
	  
	
/*	int pnr;
	int noOfSeats;
	
	String user;
	
	@Autowired
	private RestTemplate restTemplate;
	@RequestMapping(value = "/SearchFlightsDetails.htm", method = RequestMethod.GET)
    public ModelAndView SearchFlights(ModelMap model) {
		   
        return new ModelAndView("SearchFlights", "command", new SearchFlights());
    } 

    @RequestMapping(value = "/Payment.htm", method = RequestMethod.GET)
    public ModelAndView makePayment(ModelMap model, @RequestParam("user") String user, @RequestParam("pnr") int pnr,@RequestParam("noOfSeats") int noOfSeats) {
        this.user=user;
        this.pnr=pnr;
        this.noOfSeats=noOfSeats;
    	model.addAttribute("user", this.user);
        model.addAttribute("pnr", this.pnr);
        model.addAttribute("noOfSeats", this.noOfSeats);
        return new ModelAndView("Payment", "command", new CreditCard());
    }

    
    @RequestMapping(value = "/pay.htm", method = RequestMethod.POST)
    public ModelAndView  processPayment(@Valid @ModelAttribute("command") CreditCard creditCard,BindingResult bindingResult,
            ModelMap model, @RequestParam("fare") String fare, @RequestParam("user") String user, @RequestParam("pnr") int pnr,@RequestParam("noOfSeats") int  noOfSeats) {
System.out.println("CARD NUMBER"+creditCard.getCardNumber());
    	if(bindingResult.hasErrors()){
	return new ModelAndView("Payment", "command",creditCard);
}else{
	//model.addAttribute("creditCard", creditCard);
    	Boolean validUser=restTemplate.postForObject("http://localhost:8080/AirlineRS/payment/",creditCard,Boolean.class);
        if (validUser==true) {
           
             model.addAttribute("pnr", pnr);
        	model.addAttribute("noOfSeats", noOfSeats);
            model.addAttribute("user", user);
            System.out.println("payment success");
            
            
                        return new ModelAndView("PaymentSuccess", "command", creditCard);
        }
        return new ModelAndView("Failure", "command", "Invalid credit card details!!!");

    }
    	
}
    
   


    @RequestMapping(value="/index2.pdf", method = RequestMethod.GET)
 public String getDocuments(Model model) {
    	 model.addAttribute("pnr", pnr);
     	model.addAttribute("noOfSeats", noOfSeats);
         model.addAttribute("user", user);
     return "index";

 }

    
    
    
    
    
    */
	
    
}
