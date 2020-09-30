package com.michin.ai.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.chat.model.BotChat;
import com.michin.ai.chat.service.ChatService;
import com.michin.ai.conversation.model.Conversation;
import com.michin.ai.conversation.service.ConvService;
import com.michin.ai.kakao.dto.payload.Context;
import com.michin.ai.kakao.dto.payload.SkillPayload;
import com.michin.ai.kakao.dto.response.common.ButtonAction;
import com.michin.ai.kakao.dto.response.common.QuickReply;
import com.michin.ai.kakao.dto.response.common.QuickReplyAction;
import com.michin.ai.kakao.dto.response.component.BasicCard;
import com.michin.ai.kakao.dto.response.component.SimpleText;
import com.michin.ai.kakao.dto.response.sample.SampleResponse;
import com.michin.ai.kakao.model.response.SkillResponse;
import com.michin.ai.kakao.model.response.SkillTemplate;
import com.michin.ai.word.model.Word;
import com.michin.ai.word.model.Wordbook;
import com.michin.ai.word.service.WordService;

@RestController
@RequestMapping("/kakao")
public class KakaoChatController {

	@Value("${BASE_URL}")
	private String BASE_URL;
	@Autowired
	private ConvService convService;
	@Autowired
	private ChatService chatService;
	@Autowired
	private WordService wordService;

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
		// 1. 사용자가 회원인지 확인
		if (true) {
			// 2. 대화 시작
			System.out.println("CHAT/START");
			String userBotKey = payload.getUserRequest().getUser().getId();
			BotChat botChat = chatService.interactBot(userBotKey, "[BEGIN]");

			return new SkillResponse(new SkillTemplate().addOutputs(
					new SimpleText(botChat == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : botChat.getText())))
							.toJson();
		}

		// 3. 아니면 return
		return new SampleResponse().fallBackCarousel().toJson();
	}

	@PostMapping("/chat")
	public String chat(@RequestBody SkillPayload payload) {
		List<Context> contextList = payload.getContexts();
		if (contextList.size() == 0 || contextList.get(0).getParams().size() == 0)
			return new SampleResponse().fallBackCarousel().toJson();

		String userBotKey = payload.getUserRequest().getUser().getId();
		String utterance = payload.getUserRequest().getUtterance();
		chatService.saveChat(userBotKey, "user", utterance);
		BotChat botChat = chatService.interactBot(userBotKey, utterance);

		return new SkillResponse(new SkillTemplate().addOutputs(
				new SimpleText(botChat == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : botChat.getText())))
						.toJson();
	}

	@PostMapping("/conv/eng")
	public String conversationEng(@RequestBody SkillPayload payload) {
		List<Conversation> list = convService.loadConversation(null);

		if (list != null) {
			StringBuilder engConv = new StringBuilder();
			for (Conversation conv : list) {
				engConv.append(emojiMap.get(conv.getSpeaker())).append(" : ").append(conv.getEng()).append("\n\n");
			}

			SimpleText engText = new SimpleText("오늘의 회화\n\n" + engConv.toString());

			return new SkillResponse(new SkillTemplate().addOutputs(engText)
					.addQuickReply(QuickReplyAction.BLOCK.create("해석", "5f7157517db21342eac64ddf"))).toJson();

		} else {
			return new SkillResponse(new SkillTemplate().addOutputs(new SimpleText("일요일은 오늘의 회화💬를 제공하지 않습니다.")))
					.toJson();
		}
	}

	@PostMapping("/conv/kor")
	public String conversationKor(@RequestBody SkillPayload payload) {
		List<Conversation> list = convService.loadConversation(null);

		if (list != null) {
			StringBuilder korConv = new StringBuilder();
			for (Conversation conv : list) {
				korConv.append(emojiMap.get(conv.getSpeaker())).append(" : ").append(conv.getKor()).append("\n\n");
			}

			SimpleText korText = new SimpleText("오늘의 회화 - 해석\n\n" + korConv.toString());

			return new SkillResponse(new SkillTemplate().addOutputs(korText)).toJson();
		} else {
			return new SkillResponse(new SkillTemplate().addOutputs(new SimpleText("일요일은 오늘의 회화💬를 제공하지 않습니다")))
					.toJson();
		}
	}

	@PostMapping("/wb_list")
	public String wordbookList(@RequestBody SkillPayload payload) {
		// 1. 유저 키로 유저 받아오기
		String userId = "";

		// 2. 유저 아이디로 단어장 리스트 찾기
		List<Wordbook> wbList = wordService.getWordbook(userId);

		if (wbList.isEmpty() || wbList.size() == 0) {
			return new SkillResponse(new SkillTemplate().addOutputs(new BasicCard("생성한 단어장이 없습니다.", "단어장을 추가해보세요!",
					null, null, ButtonAction.WEBLINK.create("단어장 만들러 가기", BASE_URL + "/wordbook/list")))).toJson();
		}

		SimpleText text = new SimpleText("단어장을 선택해 주세요");
		List<QuickReply> wbQuickReplies = new ArrayList<>();
		for (Wordbook wb : wbList) {
			wbQuickReplies.add(QuickReplyAction.BLOCK.create(wb.getName(), "5f719021e842c7724277efba"));
		}

		return new SkillResponse(new SkillTemplate().addOutputs(text).addQuickReplies(wbQuickReplies)).toJson();
	}

	@PostMapping("/wb")
	public SkillResponse wordbook(@RequestBody SkillPayload payload) {
		String userId = "";
		String wbName = payload.getUserRequest().getUtterance();
//		Wordbook wb = new Wordbook();
//		IntStream.range(0, 20).forEach(i -> wb.getWords().add(new Word()));
		Wordbook wb = wordService.getWordbook(userId, wbName);

		StringBuilder wordStr = new StringBuilder();
		for (Word word : wb.getWords()) {
			wordStr.append(word.getEng()).append("\n\n");
		}

		new SkillResponse(new SkillTemplate().addOutputs(new SimpleText(wordStr.toString()))).toJson();

		return null;
	}

	@PostMapping("/connect")
	public String connectWeb(@RequestBody SkillPayload payload) {
		String userBotKey = payload.getUserRequest().getUser().getId();

		if (false) { // check User connection
			return new SampleResponse().alreadyConnectBlock().toJson();
		} else {
			return new SampleResponse().connectBlock(userBotKey).toJson();
		}

	}

}
