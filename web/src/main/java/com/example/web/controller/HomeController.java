package com.example.web.controller;

import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = {"/","/home"})
	public String homePage(Model model) {
		Optional<User> userOptional = userRepository.findUserByEmail(securityService.findLoggedInUsername());
		userOptional.ifPresent(user -> model.addAttribute("username", user.getFirstName()));
		return "home";
	}
}
