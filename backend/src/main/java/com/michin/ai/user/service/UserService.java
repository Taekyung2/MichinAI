package com.michin.ai.user.service;

import com.michin.ai.user.dto.payload.SaveUserCommand;
import com.michin.ai.user.model.User;

public interface UserService {
	
	User save(SaveUserCommand command);
	User findById(Long id);
	User findByBotKey(String botKey);

}
