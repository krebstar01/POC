package com.sample.websocket;

import com.sample.domain.model.Order;
import com.sample.service.controller.handler.GlobalExceptionHandler;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

/**
 * Created by justin on 13/03/2017.
 * TODO WORK IN PROGRESS
 */
@Slf4j
public class WebClientSessionHandler extends StompSessionHandlerAdapter {


    private static final Logger logger = LoggerFactory.getLogger(WebClientSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/greetings", this);
        session.send("/app/hello", "{\"name\":\"Client\"}".getBytes());

        logger.info("New session: {}", session.getSessionId());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Order.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        logger.info("Received: {}", ((Order) payload).getOrderNumber());
    }


    // http://niels.nu/blog/2017/spring-boot-websocket-client.html
    // https://github.com/nielsutrecht/spring-boot-websocket-client/tree/master/src/main/java/com/nibado/example/websocket/client
}