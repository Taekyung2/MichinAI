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

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.michin.ai.chat.model.BotChat;

public class ChatPostService {

	@Value("${CHAT_URL}")
	private String CHAT_URL;

	public BotChat interactBot(String connectUrl, String userBotKey) {
		BotChat botChat = null;

		try {
			URL url = new URL(CHAT_URL + "/" + connectUrl);
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

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return botChat;
	}
}
