package com.commerce.cdbookstore.websocket;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint {

	// ==================================
	// = 		Injection point 		=
	// ==================================
	
	@Inject
	private Logger logger;

	// ==================================
	// = 		Business Method 		=
	// ==================================
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("######################");
        System.out.println("######################");
        System.out.println("######################");
        // ...
	}
	
	@OnMessage
	public void message(String message, Session client) throws Exception {
		System.out.println("######################");
        System.out.println("######################");
        System.out.println("######################");

        logger.info("Message: " + message);
        
        for (Session peer : client.getOpenSessions()) {
			peer.getBasicRemote().sendText(message);
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("######################");
        System.out.println("######################");
        System.out.println("######################");
        // ...
	}
	
	@OnError
	public void onError(Throwable t) {
		System.out.println("######################");
		System.out.println("######################");
		System.out.println("######################");
		// ...
	}
	
	
}
