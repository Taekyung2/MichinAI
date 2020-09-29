package com.michin.ai.chat.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.michin.ai.chat.model.BotChat;
import com.michin.ai.util.BaseProperties;

@Service
public class ChatServiceImpl implements ChatService {

	@Override
	public BotChat interactBot(String userBotKey, String msg) {
		BotChat botChat = null;
		try {
			URL url = new URL(BaseProperties.CHAT_URL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

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
	public void saveChat(String sender, String msg) {

	}
}
