package com.michin.ai.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.conversation.service.ConvService;

@RestController
@RequestMapping("/conv")
public class ConversationController {
	@Autowired
	private ConvService convService;

	@GetMapping(value = { "/{date}", "/" })
	public ResponseEntity conversationList(
			@PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		System.out.println(convService.loadConversation(date));
		return null;
	}
}
