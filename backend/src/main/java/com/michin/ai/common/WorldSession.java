package com.michin.ai.common;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.michin.ai.world.model.WorldReplyMessage;

public class WorldSession {
	
	private static WorldSession instance;
	private Queue<WorldReplyMessage> reply_queue;
	
	public static WorldSession getInstance() {
		if(instance == null) {
			instance = new WorldSession();
			instance.initReplyQueue();
		}
		return instance;
	}
	
	private WebSocketSession session;
	
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	
	public void initReplyQueue() {
		this.reply_queue = new ArrayDeque<>();
	}
	
	public Queue<WorldReplyMessage> getReplyQueue(){
		return this.reply_queue;
	}
	
	public void send(Object src) throws IOException {
		this.session.sendMessage(new TextMessage(new Gson().toJson(src)));
		System.out.println("Sended!");
	}
}
