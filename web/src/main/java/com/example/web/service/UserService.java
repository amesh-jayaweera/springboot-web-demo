package com.example.web.service;

import com.example.web.dto.UserDTO;
import com.example.web.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<UserDTO> getAllUsers();
}
