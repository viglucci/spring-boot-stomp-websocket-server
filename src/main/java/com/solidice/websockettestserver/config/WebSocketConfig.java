package com.solidice.websockettestserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Kevin
 * @since 9/6/2019
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		HandshakeHandler handshakeHandler = new HandshakeHandler() {
			@Override
			public boolean doHandshake(ServerHttpRequest serverHttpRequest,
			                           ServerHttpResponse serverHttpResponse,
			                           WebSocketHandler webSocketHandler,
			                           Map<String, Object> map) throws HandshakeFailureException {
				return false;
			}

			public boolean beforeHandshake(ServerHttpRequest request,
			                               ServerHttpResponse response,
			                               WebSocketHandler webSocketHandler,
			                               Map attributes) throws Exception {

				if (request instanceof ServletServerHttpRequest) {
					ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
					HttpSession session = servletRequest.getServletRequest().getSession();
					attributes.put("sessionId", session.getId());
				}

				return true;
			}
		};

		registry.addEndpoint("/ws")
				.setHandshakeHandler(handshakeHandler)
				.withSockJS();
	}
}