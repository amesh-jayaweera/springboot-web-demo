package com.example.web.dto;

import com.example.web.entity.Role;

import java.util.Set;

public class UserDTO {
    private String email;
    private String firstName;
    private String surname;

    public UserDTO() {
    }

    public UserDTO(String email, String firstName, String surname) {
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
