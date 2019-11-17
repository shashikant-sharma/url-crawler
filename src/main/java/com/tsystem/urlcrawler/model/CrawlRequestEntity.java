package com.tsystem.urlcrawler.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.tsystem.urlcrawler.enums.RequestStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CrawlRequestEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "status can not be null")
	private RequestStatus status;
	
	@NotNull(message = "token can not be null")
	private String token;
	
	@Column(unique = true)
	private String link;
	
	private int depth;
	
	private int totalLinks;
	
	private int totalImages;
	
	@OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
	private Set<PageDetails> details = new HashSet<>();

}
