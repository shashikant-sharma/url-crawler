package com.tsystem.urlcrawler.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestAcknowledgement {
	
	private String token;
	
	public RequestAcknowledgement() {
		this.token = UUID.randomUUID().toString();
	}

}
