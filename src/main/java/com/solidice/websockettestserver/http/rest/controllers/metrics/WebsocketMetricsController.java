package com.solidice.websockettestserver.http.rest.controllers.metrics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

/**
 * https://stackoverflow.com/a/49689313/2165116
 *
 * @author Kevin
 * @since 9/7/2019
 */
@RestController
public class WebsocketMetricsController {
	private WebSocketMessageBrokerStats webSocketMessageBrokerStats;

	public WebsocketMetricsController(WebSocketMessageBrokerStats webSocketMessageBrokerStats) {
		this.webSocketMessageBrokerStats = webSocketMessageBrokerStats;
	}

	@RequestMapping("/metrics/ws")
	public WebSocketMessageBrokerStats stats() {
		return webSocketMessageBrokerStats;
	}
}
