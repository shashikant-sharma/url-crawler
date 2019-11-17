package com.tsystem.urlcrawler.controller;


import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsystem.urlcrawler.dto.CrawlRequest;
import com.tsystem.urlcrawler.dto.CrawlResponse;
import com.tsystem.urlcrawler.dto.PageDetailsDto;
import com.tsystem.urlcrawler.dto.RequestAcknowledgement;
import com.tsystem.urlcrawler.enums.RequestStatus;
import com.tsystem.urlcrawler.exception.ResourceNotFoundException;
import com.tsystem.urlcrawler.model.CrawlRequestEntity;
import com.tsystem.urlcrawler.model.PageDetails;
import com.tsystem.urlcrawler.repository.CrawlRequestRepository;
import com.tsystem.urlcrawler.service.UrlCrawlerService;

@RestController
@RequestMapping("/api/crawl/request")
public class UrlCrawlerResource {
	
	@Autowired
	private UrlCrawlerService crawlService;
	
	@Autowired
	private CrawlRequestRepository requestRepository;
	
	@PostMapping
	public RequestAcknowledgement crawl(@Valid @RequestBody CrawlRequest request) {
		
		return crawlService.crawl(request);
	}
	
	@GetMapping("/list/{status}")
	public List<CrawlRequestEntity> list(@PathVariable String status) {
		return requestRepository.findByStatus(RequestStatus.getStatus(status));
	}
	
	@GetMapping("/{token}")
	public CrawlResponse getResult(@PathVariable String token) {
		CrawlRequestEntity entity =  requestRepository.findByToken(token);
		if(Objects.isNull(entity)) {
			throw new ResourceNotFoundException("request not found");
		}
		
		CrawlResponse response = new CrawlResponse();
		response.setTotalImages(entity.getTotalImages());
		response.setTotalLinks(entity.getTotalLinks());
		Set<PageDetailsDto> dto = entity.getDetails().stream().map(PageDetails::toDto).collect(Collectors.toSet());
		response.setDetails(dto);
		return response;
	}
	
	@GetMapping("/status/{token}")
	public RequestStatus getStatus(@PathVariable String token) {
		CrawlRequestEntity entity =  requestRepository.findByToken(token);
		if(Objects.isNull(entity)) {
			throw new ResourceNotFoundException("request not found");
		}
		return entity.getStatus();
	}
	

}
