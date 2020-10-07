//package com.michin.ai.config;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.PongMessage;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//import org.springframework.stereotype.Component;
//
//@Component
//@ServerEndpoint("/ws")
//public class WebS {
//	
//	@OnOpen
//	public void echoOpen(Session session) {
//	}
//
//
//
//     //텍스트 데이터를 수신할 때 호출됨(session을 이용하여 파라미터를 추출할 수 있고 일반 데이터는 msg에 전달된다)
//
//    @OnMessage
//
//    public void echoTextMessage(Session session, String msg, boolean last) {
//
//
//
//        // 클라이언트가 요청할 때 전달한 파라미터(usr=홍길동)는 아래처럼 추출할 수 있다
//
//        String[] params = session.getQueryString().split("&");
//
//    	String usr = params[0].split("=")[1];
//
//    	try{
//
//    		usr = URLDecoder.decode(usr,"EUC-KR");//파라미터로 전달된 데이터는 URLDecoder를 사용하여 복원한다
//
//    	}catch(Exception e){
//
//    		
//
//    	}
//
//
//
//        // JSON-Simple 라이브러리를 사용하여 JSON 문자열을 처리한다
//
//    	JSONObject jobj = (JSONObject)JSONValue.parse(msg);
//
//    	
//
//    	String usrName = (String)jobj.get("usrName");
//
//    	String phone = (String)jobj.get("phone");
//
//    	String content = (String)jobj.get("content");
//
//    	
//
//        try {
//
//            if (session.isOpen()) { //클라이언트 측으로 다시 전송한다
//
//                session.getBasicRemote().sendText(usrName+","+phone+","+content, last);
//
//            }
//
//        } catch (IOException e) {
//
//            try {
//
//                session.close();
//
//            } catch (IOException e1) {
//
//                // Ignore
//
//            }
//
//        }
//
//    }
//
//
//
//     // 바이너리 데이터를 수신할 때 호출됨
//
//    @OnMessage
//
//    public void echoBinaryMessage(Session session, ByteBuffer bb,
//
//            boolean last) {
//
//        try {
//
//            if (session.isOpen()) {
//
//                //session.getBasicRemote().sendBinary(bb, last);
//
//            	session.getBasicRemote().sendText("파일이 서버에 도착했어요~", last);
//
//                File file = new File("D:/test/sample.txt");
//
//
//
//            	// Set to true if the bytes should be appended to the file;
//
//            	// set to false if the bytes should replace current bytes
//
//            	// (if the file exists)
//
//            	boolean append = false;
//
//
//
//            	try {
//
//            	    // Create a writable file channel
//
//            	    FileChannel wChannel = new FileOutputStream(file, append).getChannel();
//
//
//
//            	    // Write the ByteBuffer contents; the bytes between the ByteBuffer's
//
//            	    // position and the limit is written to the file
//
//            	    wChannel.write(bb);
//
//
//
//            	    // Close the file
//
//            	    wChannel.close();
//
//            	} catch (IOException e) {
//
//            	}
//
//            }
//
//        } catch (IOException e) {
//
//            try {
//
//                session.close();
//
//            } catch (IOException e1) {
//
//                // Ignore
//
//            }
//
//        }
//
//    }
//
//
//
//    /**
//
//     * Process a received pong. This is a NO-OP.
//
//     *
//
//     * @param pm    Ignored.
//
//     */
//
//    @OnMessage
//
//    public void echoPongMessage(PongMessage pm) {
//
//        // NO-OP
//
//    }
//
//    
//
//    @OnClose
//
//    public void onClose() {
//
//        // disconnection handling
//
//    }
//
//    
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//
//        // Error handling
//
//    }
//}
