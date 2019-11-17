package com.tsystem.urlcrawler.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CrawlResponse {
	
	@JsonProperty("total_links")
	private int totalLinks;
	
	@JsonProperty("total_images")
	private int totalImages;
	
	private Set<PageDetailsDto> details = new HashSet<>();
	
	
	

}
