package com.sample.websocket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

/**
 * Created by justin on 13/03/2017.
 * TODO WORK IN PROGRESS
 */
public class WebSocketServiceClient {


        public static void main(String... argv) {
            WebSocketClient webSocketClient = new StandardWebSocketClient();
            WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());
            stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

            String url = "ws://127.0.0.1:8087/hello";
            StompSessionHandler sessionHandler = new WebClientSessionHandler();
            stompClient.connect(url, sessionHandler);

            new Scanner(System.in).nextLine(); //Don't close immediately.
        }

}
