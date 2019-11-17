package com.tsystem.urlcrawler.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsystem.urlcrawler.model.PageDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class PageDetailsDto{
	
	@JsonProperty("page_title")
	private String pageTitle;
	
	@JsonProperty("page_link")
	private String pageLink;
	
	@JsonProperty("image_count")
	private long imageCount;
	
	public PageDetails toEntity() {
		PageDetails dto = new PageDetails();
		dto.setPageLink(this.getPageLink());
		dto.setPageTitle(this.getPageTitle());
		dto.setImageCount(this.getImageCount());
		dto.setCreatedDate(LocalDateTime.now());
		dto.setModifiedDate(LocalDateTime.now());
		return dto;
	}
	
	
	

}
