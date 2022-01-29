package com.example.web.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String email, String password);
}
