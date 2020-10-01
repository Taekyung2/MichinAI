package com.michin.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.controller.result.ApiResult;
import com.michin.ai.controller.result.Result;
import com.michin.ai.user.dto.payload.SaveUserPayload;
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
		User user = userService.save(payload.toCommand());
		
		return Result.ok();
	}
	
	@PostMapping("/link")
	public ResponseEntity<ApiResult> LinkToBot(@RequestBody SaveUserPayload payload) {
		
		long id = payload.getUserId();
		String botKey = payload.getUserBotKey();
		
		if (botKey == null || botKey.length() == 0) {
			return Result.failure("botKey가 존재하지 않습니다.");
		} else if (id == 0) {
			return Result.failure("userId가 존재하지 않습니다.");
		} else {
			User user = userService.link(payload.toCommand());
			return Result.ok();
		}
	}
}
