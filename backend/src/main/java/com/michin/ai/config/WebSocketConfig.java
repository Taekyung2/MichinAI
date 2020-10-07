package com.michin.ai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
//	connection을 맺을 떄 cors 허용
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/api").setAllowedOrigins("*").withSockJS();
	}

//	클라이언트가 메시지를 구독할 endpoint를 정의합니다
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		클라이언트가 보내는 곳
		registry.setApplicationDestinationPrefixes("/ws");
		
//		받는거 클라이언트들이 subscribe, 서버가 보내는 곳
		registry.enableSimpleBroker("/topic");
	}
	
}


