package com.michin.ai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WebSocketEventListener {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectEvent event) {
		System.out.println("attempt to connect");
	}
	
	@EventListener
	public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
		System.out.println("subscribed");
	}
	
	@EventListener
	public void handleWebSocketConnectedListener(SessionConnectedEvent event) {
		System.out.println("new web socket connected");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		System.out.println("ws disconnected");
//		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//		
//		String username = (String) headerAccessor.getSessionAttributes().get("username");
//		
//		if(username != null) {
//			headerAccessor.getSessionAttributes().remove("username");
//			
//			System.out.println("User disconnected : " + username);
//			
//			ChatMessage chatMessage = new ChatMessage();
//			chatMessage.setType(MessageType.LEAVE.ordinal());
//			chatMessage.setSender(username);
//			
//			messagingTemplate.convertAndSend("/chat/" + username, chatMessage);
//		}
	}
}
