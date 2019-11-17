package com.tsystem.urlcrawler.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrawlRequest {
    
	@NotNull(message = "url can not be null")
	private String url;
	
	private int depth;
}
