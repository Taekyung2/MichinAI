package com.michin.ai.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.CustomizableThreadCreator;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.michin.ai.world.model.WorldAskMessage;
import com.michin.ai.world.model.WorldReplyMessage;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		System.out.println("Received Something");
		JsonParser parser = new JsonParser();
		JsonObject obj = (JsonObject) parser.parse(message.getPayload());
		
		if(obj.has("type")) {
			
			System.out.println("Alive Message");
//			Receive Alive Message from World
			session.sendMessage(new TextMessage(new Gson().toJson(new WorldAskMessage<String>("conn_success", null))));
			
		}else if(obj.has("message_type")){
			
//			Receive Reply Message from World
			WorldReplyMessage reply = new Gson().fromJson(obj.toString(), WorldReplyMessage.class);
			System.out.println(reply.toString());
			WorldSession.getInstance().getReplyMap().put(reply.getRecipient(), reply);
			
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		WorldSession worldSession = WorldSession.getInstance();
		worldSession.setSession(session);
		
	}
	
//	Avoid Schedule Exception
	@Bean
	public TaskScheduler taskScheduler() {
	    TaskScheduler scheduler = new ThreadPoolTaskScheduler();

	    ((ThreadPoolTaskScheduler) scheduler).setPoolSize(6);
	    ((ExecutorConfigurationSupport) scheduler).setThreadNamePrefix("scheduled-task-");
	    ((CustomizableThreadCreator) scheduler).setDaemon(true);

	    return scheduler;
	}
}
