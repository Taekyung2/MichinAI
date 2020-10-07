package com.michin.ai.chat.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.michin.ai.chat.dto.ChatLoadByDatePayload;
import com.michin.ai.chat.dto.ChatsDeletePayload;
import com.michin.ai.chat.model.BotChat;
import com.michin.ai.chat.model.Chat;
import com.michin.ai.chat.model.ChatList;
import com.michin.ai.chat.model.Check;
import com.michin.ai.chat.model.Score;
import com.michin.ai.chat.repository.ChatRepository;
import com.michin.ai.common.util.LanguageToolUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepo;
	private final ChatSocketService chatSocket;
	private LanguageToolUtil lt = new LanguageToolUtil();

	private final int MAX_SCORE = 98;
	private final int MIN_SCORE = 0;

	@Override
	public BotChat startBot(String userBotKey) {
		JSONObject obj = new JSONObject();
		obj.put("command", "interact");
		obj.put("u_id", userBotKey);
		obj.put("msg", "Hello!");
		BotChat botChat = chatSocket.interactBot(userBotKey, obj);

		if (botChat != null)
			saveChat(userBotKey, "bot", botChat.getText());
		System.out.println(LocalTime.now());
		return botChat;
	}

	@Override
	public BotChat interactBot(String userBotKey, String msg) {
		System.out.println(LocalTime.now());
		System.out.println(msg.length());
		if (msg.length() >= 200)
			msg = msg.substring(0, 200);
		saveChat(userBotKey, "user", msg);

		JSONObject obj = new JSONObject();
		obj.put("command", "interact");
		obj.put("u_id", userBotKey);
		obj.put("msg", msg);
		BotChat botChat = chatSocket.interactBot(userBotKey, obj);

		if (botChat != null)
			saveChat(userBotKey, "bot", botChat.getText());
		System.out.println(LocalTime.now());
		return botChat;
	}

	@Async
	@Override
	public void saveChat(String userBotKey, String sender, String msg) {
		Chat chat = Chat.builder().msg(msg.trim()).sender(sender).build();

		if (sender.equals("user"))
			chat.setCheck(lt.spellCheck(msg));

		System.out.println(chat);
		chatRepo.addChat(userBotKey, chat);
	}

	@Override
	public List<ChatList> loadChatList(String userBotKey) {
		return chatRepo.findByUserBotKeyOrderByDateDesc(userBotKey);
	}

	@Override
	public List<ChatList> loadChatListByDate(ChatLoadByDatePayload payload) {
		return chatRepo.findByUserBotKeyAndDate(payload.getUserBotKey(), payload.getDate());
	}

	@Override
	public void deleteChatList(List<String> ids) {
		chatRepo.deleteByIdIn(ids);
	}

	@Override
	public void deleteChats(ChatsDeletePayload payload) {
		chatRepo.deleteChatsByIds(payload);
	}

	@Override
	public List<ChatList> loadChatScore(String userBotKey) {
		List<ChatList> chatList = chatRepo.findByUserBotKeyOrderByDateAsc(userBotKey);
		List<ChatList> updateChatList = new ArrayList<ChatList>();

		for (ChatList chats : chatList) {
			Map<String, Integer> categoryCnt = new HashMap<String, Integer>();
			if (chats.getScore() == null || chats.getScore().getScore() == 0
					|| chats.getDate().isEqual(LocalDate.now())) {
				Score score = new Score();
				int chatCnt = 0, checkCnt = 0, wordCnt = 0, engCnt = 0, totalLen = 0;
				for (Chat chat : chats.getChats()) {
					if (chat.getSender().equals("user")) {
						chatCnt++;
						checkCnt += chat.getCheck().size();
						wordCnt += chat.getMsg().split(" ").length;
						totalLen += chat.getMsg().length();

						for (char ch : chat.getMsg().toCharArray()) {
							if (Character.isUpperCase(ch) || Character.isLowerCase(ch))
								engCnt++;
						}
					}
					if (chat.getCheck() != null)
						for (Check ck : chat.getCheck()) {
							categoryCnt.put(ck.getCategory(), categoryCnt.getOrDefault(ck.getCategory(), 0) + 1);
						}
				}

				double s = -1;
				if (chatCnt >= 10) {
					s = Math.max(Math.min((wordCnt / chatCnt) * 16.7, MAX_SCORE) - checkCnt * 1.1, MIN_SCORE);
					double engRatio = (double) engCnt / totalLen;
					if (engRatio < 0.75) {
						categoryCnt.put("notEnglish", (int) Math.round(checkCnt * engRatio));
						s *= engRatio;
					}
					if ((wordCnt / chatCnt) < 6)
						categoryCnt.put("Too Short", 6 - (wordCnt / chatCnt));
					s = Math.round(s * 100) / (double) 100;
				}
				score.setScore(s);
				score.setCategoryCnt(categoryCnt);
				chats.setScore(score);
			}
			updateChatList.add(chats);
		}

		updateChatScore(updateChatList);
		return chatList;
	}

	@Async
	@Override
	public void updateChatScore(List<ChatList> chatList) {
		chatRepo.updateChatScore(chatList);
	}

}
