package com.example.ram.service;

import com.example.ram.model.User;
import com.example.ram.repository.UserRepository;
import com.example.ram.web.dto.UserRegistrationDto;
import com.example.ram.web.dto.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(),
				registrationDto.getLastName(), registrationDto.getEmail(),
				registrationDto.getPassword(), registrationDto.getRoles());

		return userRepository.save(user);
	}

	@Override
	public UserRegistrationDto loadUser(String username) {
		User user = userRepository.findByEmail(username);
		return new UserRegistrationDto(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRoles());
	}

	@Override
	public boolean authenticateUser(String username, String password) {
		User user = userRepository.findByEmail(username);
		return password.equals(user.getPassword());
	}

	@Override
	public Users findUsersToMessage(String username) {
		Users users = new Users();
		List<User> userList = userRepository.findAll();
		for(User user: userList) {
			if(!user.getEmail().equals(username)) {
				users.addUser(new UserRegistrationDto("", "", user.getEmail(), "", ""));
			}
		}
		return users;
	}
}
