package com.michin.ai.user.dto;

import org.springframework.http.ResponseEntity;

import com.michin.ai.common.ApiResult;
import com.michin.ai.common.Result;
import com.michin.ai.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SaveUserResponse {
	
	public static ResponseEntity<ApiResult> build (boolean conn) {
		ApiResult apiResult = ApiResult.blank()
				.add("connect", conn);
		return Result.ok(apiResult);
	}
	
	public static ResponseEntity<ApiResult> build(User user, boolean conn) {
		ApiResult apiResult = ApiResult.blank()
				.add("connect", conn)
				.add("userBotKey", user.getBotKey());
		return Result.ok(apiResult);
	}
	
	public static ResponseEntity<ApiResult> build(String msg, boolean conn) {
		ApiResult apiResult = ApiResult.blank()
				.add("connect", conn)
				.add("msg", msg);
		return Result.ok(apiResult);
	}
	
	@Data
	@AllArgsConstructor
	private static class UserData {
		private String botKey;
	}

}
