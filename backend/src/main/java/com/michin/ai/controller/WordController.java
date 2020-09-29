package com.michin.ai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.common.ApiResult;
import com.michin.ai.common.Result;
import com.michin.ai.word.dto.payload.AddWordCommand;
import com.michin.ai.word.dto.payload.AddWordPayload;
import com.michin.ai.word.dto.payload.ChangeCheckCommand;
import com.michin.ai.word.dto.payload.ChangeCheckPayload;
import com.michin.ai.word.dto.payload.ChangeContentCommand;
import com.michin.ai.word.dto.payload.ChangeContentPayload;
import com.michin.ai.word.dto.payload.CreateWordbookCommand;
import com.michin.ai.word.dto.payload.CreateWordbookPayload;
import com.michin.ai.word.dto.payload.EditNameCommand;
import com.michin.ai.word.dto.payload.EditNamePayload;
import com.michin.ai.word.dto.response.WordbookResult;
import com.michin.ai.word.model.Wordbook;
import com.michin.ai.word.service.WordService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/wordbook")
public class WordController {
	
	@Autowired
	private WordService wordService;
	
	@PostMapping("")
	public ResponseEntity<ApiResult> addWordBook(@RequestBody CreateWordbookPayload payload) {
		CreateWordbookCommand command = payload.toCommand();
		Wordbook wb = wordService.createWordbook(command);
		if(wb == null) return Result.failure("중복된 이름입니다.");
		return WordbookResult.build(wb);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResult> getWordbookAll(@PathVariable("id") String user_id) {
		List<Wordbook> wb_list = wordService.getWordbook(user_id);
		return WordbookResult.build(wb_list);
	}

	@DeleteMapping("/{wordbook_id}")
	public ResponseEntity<ApiResult> deleteWordBook(@PathVariable("wordbook_id") String wordbook_id) {
		List<Wordbook> wb_list = wordService.deleteWordbook(wordbook_id);
		return WordbookResult.build(wb_list);
	}
	
	@PutMapping("/name")
	public ResponseEntity<ApiResult> editName(@RequestBody EditNamePayload payload) {
		EditNameCommand command = payload.toCommand();
		Wordbook wb = wordService.editName(command);
		return WordbookResult.build(wb);
	}
	
	@PutMapping("/word")
	public ResponseEntity<ApiResult> addWord(@RequestBody AddWordPayload payload) {
		AddWordCommand command = payload.toCommand();
		Wordbook wb = wordService.addWord(command);
		return WordbookResult.build(wb);
	}
	
	@DeleteMapping("/{wordbook_id}/{word_id}")
	public ResponseEntity<ApiResult> delWord(@PathVariable("wordbook_id") String wordbook_id,
												@PathVariable("word_id") String word_id) {
		wordService.delWord(wordbook_id, word_id);
		return Result.ok();
	}
	
	@PutMapping("/check")
	public ResponseEntity<ApiResult> changeCheck(@RequestBody ChangeCheckPayload payload) {
		ChangeCheckCommand command = payload.toCommand();
		Wordbook wb = wordService.changeCheck(command);
		return WordbookResult.build(wb);
	}
	
	@PutMapping("/word/content")
	public ResponseEntity<ApiResult> changeContent(@RequestBody ChangeContentPayload payload) {
		ChangeContentCommand command = payload.toCommand();
		Wordbook wb = wordService.changeContent(command);
		return WordbookResult.build(wb);
	}
}