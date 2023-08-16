package com.project.buensabor.webSockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Habilita el broker para manejar los mensajes enviados a /topic
        config.setApplicationDestinationPrefixes("/app"); // Prefijo para los mensajes dirigidos al controlador
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Punto de entrada para WebSocket
                .setAllowedOrigins("*") // Permite todas las origenes por simplicidad
                .withSockJS(); // Habilita SockJS para navegadores que no soportan WebSocket nativo
    }
}