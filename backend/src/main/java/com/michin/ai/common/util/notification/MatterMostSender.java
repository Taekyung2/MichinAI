package com.michin.ai.common.util.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import lombok.ToString;

@Component
@ToString
public class MatterMostSender {
	private Logger log = LoggerFactory.getLogger(MatterMostSender.class);

	private boolean mmEnabled = true;
	private String webhookUrl = "https://meeting.ssafy.com/hooks/4pcarwbxj7d1pjmt5t5spmdeyh";

	public void sendMessage(Exception excpetion, String uri, String params) {
		if (mmEnabled) {
			try {
				Attachments attachments = new Attachments(excpetion, uri, params);

				String payload = new Gson().toJson(attachments);

				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);

				HttpEntity<String> entity = new HttpEntity<>(payload, headers);
				restTemplate.postForEntity(webhookUrl, entity, String.class);
			} catch (Exception e) {
				log.error("ERROR!! While send MatterMost Message : " + e.getMessage());
			}
		}
	}

}
