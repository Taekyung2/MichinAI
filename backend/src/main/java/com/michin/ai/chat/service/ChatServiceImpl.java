package com.michin.ai.chat.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.michin.ai.chat.model.BotChat;
import com.michin.ai.chat.model.Chat;
import com.michin.ai.chat.model.ChatList;
import com.michin.ai.chat.repository.ChatRepository;
import com.michin.ai.common.util.LanguageToolUtil;

@Service
public class ChatServiceImpl implements ChatService {

	@Value("${CHAT_URL}")
	private String CHAT_URL;

	@Autowired
	private ChatRepository chatRepo;
	private LanguageToolUtil lt = new LanguageToolUtil();

	@Override
	public BotChat interactBot(String userBotKey, String msg) {
		BotChat botChat = null;
		
		try {
			URL url = new URL(CHAT_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			JSONObject obj = new JSONObject();
			obj.put("u_id", userBotKey);
			obj.put("msg", msg);
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

	@Async
	@Override
	public void saveChat(String userBotKey, String sender, String msg) {
		Chat chat = Chat.builder().time(LocalTime.now()).msg(msg.trim()).sender(sender).build();

		if (sender.equals("user"))
			chat.setCheck(lt.spellCheck(msg));

		chatRepo.addChat(userBotKey, chat);
	}

	@Override
	public ChatList loadChatList(String userId, LocalDate date) {
		return null;
	}
}
