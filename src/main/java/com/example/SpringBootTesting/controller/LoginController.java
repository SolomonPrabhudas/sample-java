package com.example.SpringBootTesting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@GetMapping(value="/login")
	public String showLoginPage(ModelMap model)
	{
		return "login";
	}

}
