package com.michin.ai.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.config.WorldSession;
import com.michin.ai.world.model.WorldReplyMessage;

@RestController
public class TestController {
	
	private Map<String, WorldReplyMessage> reply_map = WorldSession.getInstance().getReplyMap();
	
	@PostMapping("/test")
	public String test(@RequestBody WorldReplyMessage reply) {
		System.out.println("received");
		
		reply_map.put(reply.getRecipient(), reply);
		
		return "Success";
	}
	
}
