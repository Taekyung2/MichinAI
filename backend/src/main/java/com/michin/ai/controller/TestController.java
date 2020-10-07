package com.michin.ai.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@MessageMapping("/websocket")
	public String test(@Payload String str) {
		System.out.println(str);
		
		return str;
	}
}
