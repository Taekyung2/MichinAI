package com.michin.ai.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.kakao.model.response.SkillResponse;
import com.michin.ai.kakao.model.response.sample.SampleResponse;
import com.michin.ai.kakao.payload.SkillPayload;

@RestController
@RequestMapping("/kakao")
public class KakaoChatController {

	@PostMapping("/chat/start")
	public String chatStart(@RequestBody SkillPayload payload) {
		System.out.println("CHAT/START");

		return new SampleResponse().fallBackCarousel().toJson();
	}

	@PostMapping("/chat")
	public String chat(@RequestBody SkillPayload payload) {
		if (payload.getContexts().size() == 0 || !payload.getContexts().get(0).getName().equals("chat_state"))
			return new SampleResponse().fallBackCarousel().toJson();
		
		String utterance = payload.getUserRequest().getUtterance();
		

		return null;
	}

	@PostMapping("/conversation")
	public SkillResponse conversation(@RequestBody SkillPayload payload) {
		
		return null;
	}

	@PostMapping("/wordbook_list")
	public SkillResponse wordbookList(@RequestBody SkillPayload payload) {

		return null;
	}

	@PostMapping("/wordbook")
	public SkillResponse wordbook(@RequestBody SkillPayload payload) {

		return null;
	}

	@PostMapping("/connect")
	public String connectWeb(@RequestBody SkillPayload payload) {
		String userBotKey = payload.getUserRequest().getUser().getId();

		if (true) { // check User connection
			return new SampleResponse().alreadyConnectBlock().toJson();
		} else {
			return new SampleResponse().connectBlock(userBotKey).toJson();
		}

	}

}
