package com.michin.ai.chat.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.michin.ai.chat.model.BotChat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
public class ChatSocketService {
	@Value("${BASE_URL}")
	private String BASE_URL;
	@Value("${CHAT_PORT}")
	private int CHAT_PORT;

	Map<String, UserSocket> map = new HashMap<>();

//	private Socket socket;
//	private InputStream is;
//	private PrintWriter pw;
//
//	public ChatSocketService() {
//		connectSocket();
//	}

	public BotChat interactBot(String userBotKey, String msg) {
		UserSocket userSock = map.get(userBotKey);
		if (userSock == null) {
			userSock = new UserSocket(BASE_URL, CHAT_PORT);
			map.put(userBotKey, new UserSocket(BASE_URL, CHAT_PORT));
		}

		System.out.println(LocalTime.now());
		BotChat botChat = null;

		System.out.println(userSock);
		try {
			JSONObject obj = new JSONObject();
			obj.put("command", "interact");
			obj.put("u_id", userBotKey);
			obj.put("msg", msg);

			userSock.pw.println(obj.toJSONString());
			userSock.pw.flush();
			System.out.println(LocalTime.now());
//			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			StringBuilder sb = new StringBuilder();
//			String line = "";
////			while ((line = br.readLine()) != null)
////				sb.append(line);
//			System.out.println(br.readLine());
////			sb.append(br.readLine());
//			System.out.println(sb.toString());

			byte[] bytes = new byte[1024];
			int readByteCount = userSock.is.read(bytes);
			System.out.println(LocalTime.now());
			botChat = new Gson().fromJson(new String(bytes, 0, readByteCount, "UTF-8"), BotChat.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(LocalTime.now());
		return botChat;
	}

}

@ToString
class UserSocket {
	public Socket socket;
	public InputStream is;
	public PrintWriter pw;

	public UserSocket(String baseUrl, int chatPort) {
		try {
			System.out.println("소켓 생성 :" + LocalTime.now());
			socket = new Socket(baseUrl, chatPort);
//			SocketAddress address = new InetSocketAddress("localhost", 8999);
//			socket.connect(address);
			is = socket.getInputStream();
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("소켓 오픈! :" + LocalTime.now());
	}
}
