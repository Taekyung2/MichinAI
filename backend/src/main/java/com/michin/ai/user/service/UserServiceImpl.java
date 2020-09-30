package com.michin.ai.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.michin.ai.user.model.User;
import com.michin.ai.user.repository.UserRepository;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User findByBotKey(String botKey) {
		return userRepository.findByBotKey(botKey);
	}

}
