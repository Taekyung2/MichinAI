package com.michin.ai.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michin.ai.user.dto.payload.SaveUserCommand;
import com.michin.ai.user.model.User;
import com.michin.ai.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(SaveUserCommand command) {
		User user = findById(command.getId());
		
		if (user != null) {
			if (!command.getName().equals(user.getName()))
				user.setName(command.getName());
		} else {
			user = new User(command.getId(), command.getName(), command.getBotKey());
		}
		
		return userRepository.save(user);
	}
	
	@Override
	public User link(SaveUserCommand command) {
		User user = findById(command.getId());
		
		if (user.getBotKey() == null || user.getBotKey().length() == 0) {
			user.setBotKey(command.getBotKey());
			return userRepository.save(user);
		}
		
		return null;
		
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
