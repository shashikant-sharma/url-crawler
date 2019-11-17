package com.tsystem.urlcrawler.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystem.urlcrawler.dto.CrawlRequest;
import com.tsystem.urlcrawler.dto.RequestAcknowledgement;
import com.tsystem.urlcrawler.enums.RequestStatus;
import com.tsystem.urlcrawler.model.CrawlRequestEntity;
import com.tsystem.urlcrawler.repository.CrawlRequestRepository;

@Service
public class UrlCrawlerServiceImpl implements UrlCrawlerService {
	
	
	@Autowired
	private CrawlRequestRepository requestRepository;

	@Override
	public RequestAcknowledgement crawl(CrawlRequest request) {
		CrawlRequestEntity entity = new CrawlRequestEntity();
		entity.setStatus(RequestStatus.SUBMITTED);
		RequestAcknowledgement ack = new RequestAcknowledgement();
		entity.setToken(ack.getToken());
		entity.setCreatedDate(LocalDateTime.now());
		entity.setModifiedDate(LocalDateTime.now());
		entity.setDepth(request.getDepth());
		entity.setLink(request.getUrl());
		requestRepository.save(entity);
		return ack;
	}
	
	
	

}
