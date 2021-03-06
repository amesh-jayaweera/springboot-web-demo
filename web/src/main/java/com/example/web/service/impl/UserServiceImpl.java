package com.example.web.service.impl;

import com.example.web.dto.UserDTO;
import com.example.web.entity.Role;
import com.example.web.entity.User;
import com.example.web.repository.RoleRepository;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	// adding new normal user
	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		HashSet<Role> roles = new HashSet<>();
		Optional<Role> roleOptional = roleRepository.findByName("USER");
		roleOptional.ifPresent(roles::add);
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOS = new ArrayList<>();
		for(User user : users) {
			UserDTO newUser = new UserDTO(user.getEmail(), user.getFirstName(), user.getSurname());
			userDTOS.add(newUser);
		}
		return userDTOS;
	}
}
