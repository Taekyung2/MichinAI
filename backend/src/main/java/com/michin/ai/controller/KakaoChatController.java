package com.michin.ai.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.michin.ai.kakao.exception.NotConnectException;
import com.michin.ai.kakao.model.response.ContextValue;
import com.michin.ai.kakao.model.response.SkillResponse;
import com.michin.ai.kakao.model.response.SkillTemplate;
import com.michin.ai.user.model.User;
import com.michin.ai.user.service.UserService;
import com.michin.ai.word.model.Word;
import com.michin.ai.word.model.Wordbook;
import com.michin.ai.word.service.WordService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
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
	@Autowired
	private UserService userService;
	@Autowired
	private SampleResponse sampleRes;

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
		User user = getUserByUserBotKey(payload);

		BotChat botChat = chatService.interactBot(user.getBotKey(), "[BEGIN]");
//		BotChat botChat = chatService.interactBot("DD", "[BEGIN]");

		return new SkillResponse(new SkillTemplate().addOutputs(
				new SimpleText(botChat == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : botChat.getText())))
						.toJson();

//		return new SampleResponse().fallBackCarousel().toJson();
	}

	@PostMapping("/chat")
	public String chat(@RequestBody SkillPayload payload) {
		List<Context> contextList = payload.getContexts();
		if (contextList.size() == 0 || contextList.get(0).getParams().size() == 0)
			return sampleRes.fallBackCarousel().toJson();

		String userBotKey = payload.getUserRequest().getUser().getId();
		String utterance = payload.getUserRequest().getUtterance();

		BotChat botChat = chatService.interactBot(userBotKey, utterance);

		return new SkillResponse(new SkillTemplate().addOutputs(
				new SimpleText(botChat == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : botChat.getText())))
						.toJson();
	}

	@PostMapping("/conv/eng")
	public String conversationEng(@RequestBody SkillPayload payload) {
		if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.SUNDAY))
			return new SkillResponse(new SkillTemplate().addOutputs(new SimpleText("일요일은 오늘의 회화💬를 제공하지 않습니다.")))
					.toJson();

		if (LocalTime.now().isBefore(LocalTime.of(0, 5)))
			return new SkillResponse(
					new SkillTemplate().addOutputs(new SimpleText("오늘의 회화는 5분부터 제공됩니다.\n잠시만 기다려주세요😥"))).toJson();

		List<Conversation> list = convService.loadConversation(null);

		StringBuilder engConv = new StringBuilder();
		for (Conversation conv : list) {
			engConv.append(emojiMap.get(conv.getSpeaker())).append(" : ").append(conv.getEng()).append("\n\n");
		}

		SimpleText engText = new SimpleText("오늘의 회화\n\n" + engConv.toString());

		return new SkillResponse(new SkillTemplate().addOutputs(engText)
				.addQuickReply(QuickReplyAction.BLOCK.create("해석", "5f7157517db21342eac64ddf"))).toJson();

	}

	@PostMapping("/conv/kor")
	public String conversationKor(@RequestBody SkillPayload payload) {
		List<Conversation> list = convService.loadConversation(null);

		StringBuilder korConv = new StringBuilder();
		for (Conversation conv : list) {
			korConv.append(emojiMap.get(conv.getSpeaker())).append(" : ").append(conv.getKor()).append("\n\n");
		}

		SimpleText korText = new SimpleText("오늘의 회화 - 해석\n\n" + korConv.toString());

		return new SkillResponse(new SkillTemplate().addOutputs(korText)).toJson();
	}

	@PostMapping("/wb_list")
	public String wordbookList(@RequestBody SkillPayload payload) {
		// 1. 유저 키로 유저 받아오기
		User user = getUserByUserBotKey(payload);

		// 2. 유저 아이디로 단어장 리스트 찾기
		List<Wordbook> wbList = wordService.getWordbook(String.valueOf(user.getId()));

		if (wbList.isEmpty() || wbList.size() == 0) {
			return new SkillResponse(new SkillTemplate().addOutputs(new BasicCard("생성한 단어장이 없습니다.", "단어장을 추가해보세요!",
					null, null, ButtonAction.WEBLINK.create("단어장 만들러 가기", BASE_URL + "/wordbook/list")))).toJson();
		}

		SimpleText text = new SimpleText("단어장을 선택해 주세요");
		List<QuickReply> wbQuickReplies = new ArrayList<>();
		for (Wordbook wb : wbList) {
			wbQuickReplies.add(QuickReplyAction.BLOCK.create("📔 " + wb.getName(), "5f719021e842c7724277efba"));
		}

		return new SkillResponse(new SkillTemplate().addOutputs(text).addQuickReplies(wbQuickReplies)).toJson();
	}

	@PostMapping("/wb")
	public String wordbook(@RequestBody SkillPayload payload) {
		User user = getUserByUserBotKey(payload);
		String userId = String.valueOf(user.getId());
		String wbName = payload.getUserRequest().getUtterance().substring(3);

		Wordbook wb = wordService.getWordbook(userId, wbName);

		StringBuilder wordStr = new StringBuilder("📖 " + wbName + "\n\n");
		for (Word word : wb.getWords()) {
			wordStr.append(word.getEng()).append('\n').append(' ').append('-').append(word.getKor()).append("\n\n");
		}

		return new SkillResponse(new SkillTemplate().addOutputs(new SimpleText(wordStr.toString()))).toJson();
	}

	@PostMapping("/connect")
	public String connectWeb(@RequestBody SkillPayload payload) {
		String userBotKey = payload.getUserRequest().getUser().getId();
		User user = userService.findByBotKey(userBotKey);
		if (user != null) { // check User connection
			return sampleRes.alreadyConnectBlock().toJson();
		} else {
			return sampleRes.connectBlock(userBotKey).toJson();
		}

	}

	private User getUserByUserBotKey(SkillPayload payload) {
		String userBotKey = payload.getUserRequest().getUser().getId();
		User user = userService.findByBotKey(userBotKey);

		if (user == null)
			throw new NotConnectException(userBotKey);
		return user;
	}

	@ExceptionHandler(NotConnectException.class)
	public String notConnectWithWeb(NotConnectException e) {
		return sampleRes.connectErrorBlock(e.getUserBotKey()).addContext(new ContextValue("chat_state", 0)).toJson();
	}

}
