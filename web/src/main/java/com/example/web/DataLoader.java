package com.example.web;

import com.example.web.entity.Role;
import com.example.web.entity.User;
import com.example.web.repository.RoleRepository;
import com.example.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(ApplicationArguments args) {

        if(!roleRepository.existsByName("USER")) {
            roleRepository.save(new Role("USER"));
        }

        if(!roleRepository.existsByName("ADMIN")) {
            roleRepository.save(new Role("ADMIN"));
        }

        String firstName = "Harry";
        String surname = "Potter";
        String email = "admin@gmail.com";
        String password = "Test123#";
        if(!userRepository.existsByEmail(email)) {
            User user = new User(firstName, surname, email, passwordEncoder.encode(password));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
        }

        firstName = "Amesh";
        surname = "Jayaweera";
        email = "ameshmbjyw97@gmail.com";
        if(!userRepository.existsByEmail(email)) {
            String adminPassword = "Test123#";
            User user = new User(firstName, surname, email, passwordEncoder.encode(adminPassword));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
