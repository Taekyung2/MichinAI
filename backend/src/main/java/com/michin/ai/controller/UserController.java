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
		
		if (payload.getUserId() == 0) {
			return Result.failure("userId가 존재하지 않습니다.");
		}
		User origin = userService.findById(payload.getUserId());
		String originBotKey = "";
		if (origin != null) 
			originBotKey = origin.getBotKey();
		
		User user = userService.save(payload.toCommand());
		
		if (payload.getUserBotKey() == null || payload.getUserBotKey() == "") {	// 로그인 요청: 봇키 있으면 봇키 리턴, 없으면 걍 
			if (user.getBotKey() == null)
				return SaveUserResponse.build(false);
			return SaveUserResponse.build(user, false);
		} else {	// 연동 요청
			if (originBotKey == null || originBotKey == "")	// DB에 봇 키 없으면
				return SaveUserResponse.build(user, true);
			return SaveUserResponse.build("이미 연동된 계정입니다.", true);
		}
	}
}
