package com.solidice.websockettestserver.http.websocket.controllers;

import com.solidice.websockettestserver.models.GreetingResponse;
import com.solidice.websockettestserver.models.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Kevin
 * @since 9/6/2019
 */
@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public GreetingResponse handleGreetingMessage(HelloMessage message) throws Exception {
		return new GreetingResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
}
