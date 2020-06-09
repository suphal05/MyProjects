/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("contextPath")
/*@PropertySources({@PropertySource("classpath:/com/infosys/irs/resources/configuration.properties"),
	@PropertySource("classpath:/com/infosys/irs/resources/message_en.properties"),
	@PropertySource("classpath:/com/infosys/irs/resources/message_hi.properties")})*/
@PropertySource("classpath:/com/infosys/irs/resources/configuration.properties")
public class HomeController {
	
                   
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getHomeDetails(HttpSession session, HttpServletRequest request) {
		session.setAttribute("contextPath", request.getContextPath());
		
		return new ModelAndView("infyGoHome", "", "");
	}
	
}
