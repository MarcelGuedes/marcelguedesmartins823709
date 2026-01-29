package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class RawWebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SimpleTextHandler(), "/ws-simple")
                .setAllowedOriginPatterns("*");
    }

    private static class SimpleTextHandler extends TextWebSocketHandler {
        @Override
        public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            // ecoa a mensagem recebida
            String payload = message.getPayload();
            session.sendMessage(new TextMessage("Servidor recebeu: " + payload));
        }
    }
}
