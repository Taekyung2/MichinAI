package com.michin.ai.chat.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.michin.ai.chat.dto.ChatLoadByDatePayload;
import com.michin.ai.chat.dto.ChatsDeletePayload;
import com.michin.ai.chat.model.BotChat;
import com.michin.ai.chat.model.Chat;
import com.michin.ai.chat.model.ChatList;
import com.michin.ai.chat.repository.ChatRepository;
import com.michin.ai.common.util.LanguageToolUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

	@Value("${CHAT_URL}")
	private String CHAT_URL;

	@Autowired
	private ChatRepository chatRepo;
	private final ChatSocketService chatSocket;
	private LanguageToolUtil lt = new LanguageToolUtil();

	@Override
	public BotChat startBot(String userBotKey) {
		BotChat botChat = null;

		try {
			URL url = new URL(CHAT_URL + "/start");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			JSONObject obj = new JSONObject();
			obj.put("u_id", userBotKey);
			bw.write(obj.toJSONString());
			bw.flush();
			bw.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while ((line = br.readLine()) != null)
				sb.append(line);

			botChat = new Gson().fromJson(sb.toString(), BotChat.class);
			saveChat(userBotKey, "bot", botChat.getText());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return botChat;
	}

//	@Override
//	public BotChat interactBot(String userBotKey, String msg) {
//		BotChat botChat = null;
//
//		try {
//			System.out.println("URL");
//			URL url = new URL(CHAT_URL + "/interact");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type", "application/json");
//			conn.setDoOutput(true);
//			System.out.println("BUFFERED WRITER");
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//			JSONObject obj = new JSONObject();
//			obj.put("u_id", userBotKey);
//			obj.put("msg", msg);
//			bw.write(obj.toJSONString());
//			bw.flush();
//			bw.close();
//			System.out.println("BUFFERED READER");
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			StringBuilder sb = new StringBuilder();
//			String line = "";
//			while ((line = br.readLine()) != null)
//				sb.append(line);
//			System.out.println("OUTPUT");
//			System.out.println(sb.toString());
//			botChat = new Gson().fromJson(sb.toString(), BotChat.class);
//
//			saveChat(userBotKey, "bot", botChat.getText());
//			System.out.println("SAVE CHAT");
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (ProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return botChat;
//	}

	@Override
	public BotChat interactBot(String userBotKey, String msg) {
		System.out.println(LocalTime.now());
		saveChat(userBotKey, "user", msg);
		BotChat botChat = chatSocket.interactBot(userBotKey, msg);

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

}
