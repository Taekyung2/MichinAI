package com.michin.ai.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.chat.service.ChatService;
import com.michin.ai.config.WorldSession;
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
import com.michin.ai.world.model.AskContent;
import com.michin.ai.world.model.WorldAskMessage;
import com.michin.ai.world.model.WorldReplyMessage;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
//@ApiIgnore
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

	private WorldSession worldSession = WorldSession.getInstance();
	private Map<String, WorldReplyMessage> reply_map = worldSession.getReplyMap();

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

//		BotChat botChat = chatService.interactBot(user.getBotKey(), "begin");
//		System.out.println(LocalTime.now());
//		BotChat botChat = chatService.startBot(payload.getUserRequest().getUser().getId());
//		System.out.println(LocalTime.now());
//		System.out.println(botChat);
//		return new SkillResponse(new SkillTemplate().addOutputs(new SimpleText("대화는 200자 이내의 문자만 인식합니다.\n연속으로 3초 이내에 발송시 제대로 인식하지 못할 수 있습니다😥")).addOutputs(
//				new SimpleText(botChat == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : botChat.getText())))
//						.toJson();
		
		String userBotKey = payload.getUserRequest().getUser().getId();
		
		reply_map.put(userBotKey + "##@", null);

		/////////////////////

		String tempUserId = userBotKey + "##@";
		String tempmId = new BigInteger(130, new SecureRandom()).toString(32);

		WorldAskMessage<AskContent> ask = new WorldAskMessage<AskContent>("new_packet",
				new AskContent(tempUserId, userBotKey, new AskContent.Message("hey", tempmId)));

		try {
			worldSession.send(ask);
		} catch (IOException e) {
			e.printStackTrace();
		}

		WorldReplyMessage reply = null;
		while (reply == null) {
			try {
				reply = reply_map.get(tempUserId);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ask = new WorldAskMessage<AskContent>("new_packet",
				new AskContent(tempUserId, userBotKey, new AskContent.Message("begin", tempmId)));

		try {
			worldSession.send(ask);
		} catch (IOException e) {
			e.printStackTrace();
		}

		reply = null;
		while (reply == null) {
			try {
				reply = reply_map.get(tempUserId);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new SkillResponse(new SkillTemplate().addOutputs(
				new SimpleText(reply == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : reply.getText()))).toJson();
		
	}

	@PostMapping("/chat")
	public String chat(@RequestBody SkillPayload payload) {
		System.out.println("도착");
		double start = System.currentTimeMillis();

		List<Context> contextList = payload.getContexts();
		if (contextList.size() == 0 || contextList.get(0).getParams().size() == 0)
			return sampleRes.fallBackCarousel().toJson();

		String userBotKey = payload.getUserRequest().getUser().getId();
		String utterance = payload.getUserRequest().getUtterance();

		chatService.saveChat(userBotKey, "user", utterance);

		reply_map.put(userBotKey + "##@", null);

		/////////////////////

		String tempUserId = userBotKey + "##@";
		String tempmId = new BigInteger(130, new SecureRandom()).toString(32);

		WorldAskMessage<AskContent> ask = new WorldAskMessage<AskContent>("new_packet",
				new AskContent(tempUserId, userBotKey, new AskContent.Message(utterance, tempmId)));

		try {
			worldSession.send(ask);
		} catch (IOException e) {
			e.printStackTrace();
		}

		WorldReplyMessage reply = null;
		while (reply == null) {
//			Iterator it = reply_.iterator();
//			while(it.hasNext()) {
//				WorldReplyMessage temp = (WorldReplyMessage) it.next();
//				if(temp.getRecipient().equals(tempUserId)){
//					reply = temp;
//					it.remove();
//					break;
//				}
//			}

			try {
				reply = reply_map.get(tempUserId);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("왔다갔다 시간 : " + ((System.currentTimeMillis() - start) / 1000.0));
		if (reply != null)
			chatService.saveChat(userBotKey, "bot", reply.getText());

//		reply == null 인 경우 : 위에서 시간 초과 만든다음에 시간초과 메세지 주면 좋지않을까?
		return new SkillResponse(new SkillTemplate().addOutputs(
				new SimpleText(reply == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : reply.getText()))).toJson();

		/////////////////////

//		BotChat botChat = chatService.interactBot(userBotKey, utterance);

//		return new SkillResponse(new SkillTemplate().addOutputs(
//				new SimpleText(botChat == null ? "대화를 시작하던 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요!" : botChat.getText())))
//						.toJson();

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
		List<Wordbook> wbList = wordService.getWordbook(user.getId());

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
		Long userId = user.getId();
		String wbName = payload.getUserRequest().getUtterance().substring(3);

		Wordbook wb = wordService.getWordbook(userId, wbName);

		StringBuilder wordStr = new StringBuilder("📖 " + wbName + "\n\n");
		for (Word word : wb.getWords()) {
			wordStr.append(word.getEng()).append('\n').append(' ').append(word.getKor()).append("\n\n");
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
