package com.solidice.websockettestserver.http.websocket.controllers;

import com.solidice.websockettestserver.models.PongResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * @author Kevin
 * @since 9/7/2019
 */
@Controller
public class PingPongController {
	@MessageMapping("/ping")
	@SendToUser("/queue/pong")
	public PongResponse handlePingMessage() throws Exception {
		return new PongResponse();
	}
}
