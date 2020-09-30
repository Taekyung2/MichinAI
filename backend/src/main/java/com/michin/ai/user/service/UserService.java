package com.michin.ai.user.service;

import com.michin.ai.user.model.User;

public interface UserService {
	
	User save(User user);
	User findById(Long id);
	User findByBotKey(String botKey);

}
