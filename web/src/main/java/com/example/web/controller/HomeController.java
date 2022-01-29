package com.example.web.controller;

import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
		userOptional.ifPresent(user -> {
			model.addAttribute("username", user.getFirstName());
			model.addAttribute("surname", user.getSurname());
			model.addAttribute("email", user.getEmail());
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
					SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			List<String> roles = new ArrayList<>();
			for(SimpleGrantedAuthority simpleGrantedAuthority : authorities) {
				roles.add(simpleGrantedAuthority.getAuthority());
			}
			model.addAttribute("roles", roles);
		});
		return "home";
	}

	@GetMapping(value = {"/users"})
	@PreAuthorize("hasRole('ADMIN')")
	public String usersPage(Model model) {
		return "users";
	}
}
