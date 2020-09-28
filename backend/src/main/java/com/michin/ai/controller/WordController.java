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
		Wordbook wb = wordService.delWord(wordbook_id, word_id);
		return WordbookResult.build(wb);
	}
	
	@PutMapping("/{wordbook_id}/{word_id}")
	public ResponseEntity<ApiResult> changeCheck(@PathVariable("wordbook_id") String wordbook_id,
													@PathVariable("word_id") String word_id) {
		Wordbook wb = wordService.changeCheck(wordbook_id, word_id);
		return WordbookResult.build(wb);
	}
	
	
}
