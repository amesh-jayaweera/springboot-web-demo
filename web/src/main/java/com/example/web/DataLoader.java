package com.example.web;

import com.example.web.entity.Role;
import com.example.web.entity.User;
import com.example.web.repository.RoleRepository;
import com.example.web.repository.UserRepository;
import com.example.web.util.ERole;
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

        if(!roleRepository.existsByName(ERole.ADMIN)) {
            roleRepository.save(new Role(ERole.ADMIN));
        }

        if(!roleRepository.existsByName(ERole.USER)) {
            roleRepository.save(new Role(ERole.USER));
        }

        String firstName = "Harry";
        String surname = "Potter";
        String adminEmail = "admin@gmail.com";
        if(!userRepository.existsByEmail(adminEmail)) {
            String adminPassword = "Test123#";
            User user = new User(firstName, surname, adminEmail, passwordEncoder.encode(adminPassword));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
