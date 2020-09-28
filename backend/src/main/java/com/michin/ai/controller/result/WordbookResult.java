package com.michin.ai.controller.result;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.michin.ai.word.model.Word;
import com.michin.ai.word.model.Wordbook;

import lombok.AllArgsConstructor;
import lombok.Data;

public class WordbookResult {
	
	public static ResponseEntity<ApiResult> build(Wordbook wb) {
		ArrayList<WordData> wordsData = new ArrayList<>();
		for(Word word : wb.getWords()) {
			wordsData.add(new WordData(word.getId().toString(),
										word.getEng(),
										word.getKor(),
										word.isCheck()));
		}
		ApiResult apiResult = ApiResult.blank()
				.add("id", wb.getId().toString())
				.add("user_id", wb.getUser_id())
				.add("words", wordsData)
				.add("time", wb.getId());
		return Result.ok(apiResult);
	}
	
	
	public static ResponseEntity<ApiResult> build(List<Wordbook> wb_list) {
		ApiResult apiResult = ApiResult.blank()
				.add("wordbooks", wb_list);
		return Result.ok(apiResult);
	}
	
	@Data
	@AllArgsConstructor
	private static class WordData {
		private String id;
		private String eng;
		private String kor;
		private boolean check;
	}
}
