package com.michin.ai.chat.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.michin.ai.chat.model.BotChat;

@Component
public class ChatSocketService {

	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	public ChatSocketService() {
		try {
			socket = new Socket();
			SocketAddress address = new InetSocketAddress("localhost", 8999);

			socket.connect(address);
		} catch (IOException e) {
		}

	}

	public BotChat chatBot(String userBotKey, String msg) {
		sendMsg(userBotKey, msg);
		return recieveMsg(userBotKey);
	}

	private void sendMsg(String userBotKey, String msg) {
		try {
			pw = new PrintWriter(socket.getOutputStream());
			JSONObject obj = new JSONObject();
			obj.put("u_id", userBotKey);
			obj.put("msg", msg);

			pw.println(obj.toJSONString());
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

	private BotChat recieveMsg(String userBotKey) {
		BotChat botChat = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while ((line = br.readLine()) != null)
				sb.append(line);

			botChat = new Gson().fromJson(sb.toString(), BotChat.class);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		return botChat;
	}

}
