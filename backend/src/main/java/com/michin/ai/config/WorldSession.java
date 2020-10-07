package com.michin.ai.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.michin.ai.world.model.WorldReplyMessage;

public class WorldSession {
	
	private static WorldSession instance;
	private Map<String, WorldReplyMessage> reply_map;
	
	public static WorldSession getInstance() {
		if(instance == null) {
			instance = new WorldSession();
			instance.initReplyMap();
		}
		return instance;
	}
	
	private WebSocketSession session;
	
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	
	public void initReplyMap() {
		this.reply_map = new HashMap<>();
	}
	
	public Map<String, WorldReplyMessage> getReplyMap(){
		return this.reply_map;
	}
	
	public void send(Object src) throws IOException {
		this.session.sendMessage(new TextMessage(new Gson().toJson(src)));
		System.out.println("Sended!");
	}
}
