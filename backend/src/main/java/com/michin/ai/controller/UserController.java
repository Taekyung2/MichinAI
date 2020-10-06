package com.michin.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.common.ApiResult;
import com.michin.ai.common.Result;
import com.michin.ai.user.dto.SaveUserPayload;
import com.michin.ai.user.dto.SaveUserResponse;
import com.michin.ai.user.model.User;
import com.michin.ai.user.service.UserService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResult> login(@RequestBody SaveUserPayload payload)  {
		
		System.out.println(payload);
		
		if (payload.getUserId() == 0) {
			return Result.failure("userId가 존재하지 않습니다.");
		} else if (payload.getUserBotKey() != null) {
			return Result.failure("이미 연동된 계정입니다.");
		}
		User user = userService.save(payload.toCommand());
		if (user.getBotKey() != null) {
			return SaveUserResponse.build(user);
		} else {
			return Result.ok();
		}
	}
}
