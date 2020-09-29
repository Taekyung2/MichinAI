package com.michin.ai.word.dto.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.michin.ai.common.ApiResult;
import com.michin.ai.common.Result;
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
				.add("name", wb.getName())
				.add("words", wordsData)
				.add("time", wb.getId());
		return Result.ok(apiResult);
	}
	
	
	public static ResponseEntity<ApiResult> build(List<Wordbook> wb_list) {
		ArrayList<WordbookData> wordbooksData = new ArrayList<>();
		for(Wordbook wb : wb_list) {
			List<WordData> wordsData = new ArrayList<>();
			for(Word word : wb.getWords()) {
				wordsData.add(new WordData(word.getId().toString(),
											word.getEng(),
											word.getKor(),
											word.isCheck()));
			}			
			wordbooksData.add(new WordbookData(wb.getId().toString(),
											wb.getName(),
											wb.getUser_id(),
											wordsData));
		}
		ApiResult apiResult = ApiResult.blank()
				.add("wordbooks", wordbooksData);
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
	
	@Data
	@AllArgsConstructor
	private static class WordbookData {
		private String id;
		private String name;
		private String user_id;
		private List<WordData> words;
	}
}
