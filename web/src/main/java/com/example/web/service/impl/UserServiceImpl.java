package com.example.web.service.impl;

import com.example.web.entity.Role;
import com.example.web.entity.User;
import com.example.web.repository.RoleRepository;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import com.example.web.util.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// adding new normal user
	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		HashSet<Role> roles = new HashSet<>();
		Optional<Role> roleOptional = roleRepository.findByName(ERole.ROLE_USER);
		roleOptional.ifPresent(roles::add);
		user.setRoles(roles);
		userRepository.save(user);
	}
}
