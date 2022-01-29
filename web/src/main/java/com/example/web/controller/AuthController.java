package com.example.web.controller;

import com.example.web.entity.User;
import com.example.web.service.SecurityService;
import com.example.web.service.UserService;
import com.example.web.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private SecurityService securityService;

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("userForm", new User());
		return "register";
	}
	
	// registration of user
	@PostMapping("/register")
	public String processingRegistrationForm(@ModelAttribute("userForm") User userForm, BindingResult
			bindingResult) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "register";
		}
		userService.saveUser(userForm);
		securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm());
		return "redirect:/home";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null){
			model.addAttribute("error", "Your username and password is invalid.");
		}

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}
}
