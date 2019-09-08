package com.solidice.websockettestserver.models;

/**
 * @author Kevin
 * @since 9/6/2019
 */
public class GreetingResponse {

	private String content;

	public GreetingResponse() {
	}

	public GreetingResponse(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
