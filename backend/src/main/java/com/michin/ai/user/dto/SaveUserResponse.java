package com.michin.ai.user.dto;

import org.springframework.http.ResponseEntity;

import com.michin.ai.common.ApiResult;
import com.michin.ai.common.Result;
import com.michin.ai.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SaveUserResponse {
	
	public static ResponseEntity<ApiResult> build(User user) {
		ApiResult apiResult = ApiResult.blank()
				.add("userBotKey", user.getBotKey());
		return Result.ok(apiResult);
	}
	
	@Data
	@AllArgsConstructor
	private static class UserData {
		private String botKey;
	}

}
