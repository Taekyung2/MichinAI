package com.michin.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.controller.result.ApiResult;
import com.michin.ai.user.model.User;
import com.michin.ai.user.service.UserService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResult> login(@RequestBody User user)  {
		
		User origin = userService.findById(user.getId());
		
		if (origin != null) {
			if (!origin.getName().equals(user.getName()))
				userService.save(user);
		} else {
			userService.save(user);
		}
		
		return ;
	}
	
	@PostMapping("/link")
	public ResponseEntity<ApiResult> LinkToBot() {
		
		User user = userService.findById(id);
		user.setBotKey(botKey);
		
		userService.save(user);
	}
}
