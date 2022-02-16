package com.rajan.websocketclient.config;

import com.rajan.websocketclient.domain.Message;
import com.rajan.websocketclient.domain.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

@Slf4j
public class MyStompSessionHandler extends StompSessionHandlerAdapter {
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("New session is established: " + session.getSessionId());
        session.subscribe("/topic/messages", this);
        log.info("Subscribed to /topic/messages");
        session.send("/app/chat", Message.builder()
                .from("Rajan Singh Baliyan")
                .text("How are you doing")
                .build()
        );
        log.info("Message send to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("The error occured: " + exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OutputMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        OutputMessage message = (OutputMessage) payload;
        log.info("Message recived: " + message.getFrom() + " content is: " + message.getText() + " time: " + message.getTime());
    }
}
