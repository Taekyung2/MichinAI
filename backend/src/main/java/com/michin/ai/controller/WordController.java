package com.michin.ai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.controller.command.CreateWordbookCommand;
import com.michin.ai.controller.payload.CreateWordbookPayload;
import com.michin.ai.controller.result.ApiResult;
import com.michin.ai.controller.result.WordbookResult;
import com.michin.ai.word.model.Wordbook;
import com.michin.ai.word.service.WordService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/wordbook")
public class WordController {
	
	@Autowired
	private WordService wordService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResult> addWordBook(@RequestBody CreateWordbookPayload payload) {
		CreateWordbookCommand command = payload.toCommand();
		Wordbook wb = wordService.createWordbook(command);
		System.out.println(wb);
		return WordbookResult.build(wb);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResult> getWordbookAll(@PathVariable("id") String user_id) {
		List<Wordbook> wb_list = wordService.getWordbook(user_id);
		return WordbookResult.build(wb_list);
	}
}
