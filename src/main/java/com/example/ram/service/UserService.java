package com.example.ram.service;

import com.example.ram.model.User;

import com.example.ram.web.dto.UserRegistrationDto;
import com.example.ram.web.dto.Users;

public interface UserService {
	User save(UserRegistrationDto registrationDto);

	UserRegistrationDto loadUser(String username);

	boolean authenticateUser(String username, String password);
	
	//Hi Yo

    Users findUsersToMessage(String username);
}
