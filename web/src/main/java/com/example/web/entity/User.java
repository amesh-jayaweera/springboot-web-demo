package com.example.web.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Slf4j
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long id;
	
	@Column(name="first_name", nullable = false)
	private String firstName;

	@Column(name="surname")
	private String surname;

	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;

	@Transient
	private String passwordConfirm;

	@ManyToMany(
			targetEntity = Role.class
	)
	private Set<Role> roles;

	public User() {
	}

	public User(String firstName, String surname, String email, String password) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
