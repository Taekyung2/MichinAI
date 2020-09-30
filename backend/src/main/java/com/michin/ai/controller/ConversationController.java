package com.michin.ai.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.common.Result;
import com.michin.ai.conversation.dto.ConversationResponse;
import com.michin.ai.conversation.service.ConvService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/conv")
public class ConversationController {
	@Autowired
	private ConvService convService;

	@GetMapping(value = { "/{date}", "/" })
	@ApiOperation(value = "회화 불러오기 date=yyyy-MM-dd/ 공백이라면 오늘의 회화")
	public ResponseEntity conversationList(@ApiParam(defaultValue = "2020-09-28")
			@PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		LocalDate today = LocalDate.now();

		
		if (today.isBefore(date)) {
			return Result.failure("현재 날짜 이후의 회화 데이터를 반활할 수 없습니다.");
		}

		if (ChronoUnit.DAYS.between(date, today) >= 365) {
			return Result.failure("1년 이상의 회화 데이터를 반환할 수 없습니다.");
		}

		return ConversationResponse.build(convService.loadConversation(date));
	}

}
