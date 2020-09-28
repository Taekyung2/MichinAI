package com.michin.ai.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.conversation.model.Conversation;
import com.michin.ai.conversation.service.ConvService;
import com.michin.ai.kakao.dto.payload.SkillPayload;
import com.michin.ai.kakao.dto.response.component.BasicCard;
import com.michin.ai.kakao.dto.response.component.Carousel;
import com.michin.ai.kakao.dto.response.component.SimpleText;
import com.michin.ai.kakao.dto.response.sample.SampleResponse;
import com.michin.ai.kakao.model.response.SkillResponse;
import com.michin.ai.kakao.model.response.SkillTemplate;

@RestController
@RequestMapping("/kakao")
public class KakaoChatController {
	@Autowired
	private ConvService convService;

	private Map<String, String> emojiMap;

	public KakaoChatController() {
		emojiMap = new HashMap<>();
		emojiMap.put("man1", "🧑");
		emojiMap.put("man2", "👨");
		emojiMap.put("woman1", "👩");
		emojiMap.put("woman2", "👧");
	}

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
	public String conversation(@RequestBody SkillPayload payload) {
		List<Conversation> list = convService.loadConversation(LocalDate.of(2020, 9, 25));

		if (list != null) {
			StringBuilder engConv = new StringBuilder();
			StringBuilder korConv = new StringBuilder();
			for (Conversation conv : list) {
				engConv.append(emojiMap.get(conv.getSpeaker())).append(" : ").append(conv.getEng()).append('\n');
				korConv.append(emojiMap.get(conv.getSpeaker())).append(" : ").append(conv.getKor()).append('\n');
			}

			 SimpleText engText = new SimpleText("오늘의 회화\n\n"+ engConv.toString());
			 SimpleText korText = new SimpleText("오늘의 회화 - 해석\n\n"+ korConv.toString());

			return new SkillResponse(new SkillTemplate().addOutputs(engText).addOutputs(korText)).toJson();
		} else {
			return new SkillResponse(new SkillTemplate().addOutputs(new SimpleText("일요일은 오늘의 회화를 제공하지 않습니다.")))
					.toJson();
		}
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
