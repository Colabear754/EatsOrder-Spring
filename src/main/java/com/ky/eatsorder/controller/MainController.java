package com.ky.eatsorder.controller;

import java.util.Locale;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {
	
//	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/main")
	public String main(Locale locale) {
		return "main";
	}
	
	@RequestMapping("/")
	public String main2() {
		return "redirect:main";
	}
	
	@RequestMapping("/utilization")
	public String utilization() {
		
		return "utilization";
	}
}
