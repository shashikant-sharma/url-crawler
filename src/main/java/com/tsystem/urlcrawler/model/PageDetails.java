package com.tsystem.urlcrawler.model;

import javax.persistence.Entity;

import com.tsystem.urlcrawler.dto.PageDetailsDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class PageDetails extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6950793176780580700L;

	private String pageTitle;
	
	private String pageLink;
	
	private long imageCount;
	
	public PageDetailsDto toDto() {
		PageDetailsDto dto = new PageDetailsDto();
		dto.setPageLink(this.getPageLink());
		dto.setPageTitle(this.getPageTitle());
		dto.setImageCount(this.getImageCount());
		return dto;
	}
	

}
