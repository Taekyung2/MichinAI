package com.michin.ai.controller;

import java.io.IOException;
import java.util.Queue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.common.WorldSession;
import com.michin.ai.world.model.AskContent;
import com.michin.ai.world.model.WorldAskMessage;
import com.michin.ai.world.model.WorldReplyMessage;

@RestController
public class TestController {
	
	private WorldSession worldSession = WorldSession.getInstance();
	private Queue<WorldReplyMessage> reply_queue = worldSession.getReplyQueue(); 
	
	@GetMapping("/test/{id}/{text}")
	String test(@PathVariable String id, @PathVariable String text) {
		try {
			int size = reply_queue.size();
			
			WorldAskMessage<AskContent> ask = new WorldAskMessage<AskContent>("new_packet", new AskContent(id, "!" + id, new AskContent.Message(text, id + "!")));
			worldSession.send(ask);
			
			while(size == reply_queue.size()) {
				Thread.sleep(100);
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return "failed";
		}
		
		return "Success";
	}
	
}
