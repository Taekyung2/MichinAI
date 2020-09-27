package com.michin.ai.controller.result;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.michin.ai.word.model.Wordbook;

public class WordbookResult {
	
	public static ResponseEntity<ApiResult> build(Wordbook wb) {
		ApiResult apiResult = ApiResult.blank()
				.add("id", wb.getId().toString())
				.add("user_id", wb.getUser_id())
				.add("words", wb.getWords())
				.add("time", wb.getId());
		return Result.ok(apiResult);
	}
	
	
	public static ResponseEntity<ApiResult> build(List<Wordbook> wb_list) {
		ApiResult apiResult = ApiResult.blank()
				.add("wordbooks", wb_list);
		return Result.ok(apiResult);
	}
}
