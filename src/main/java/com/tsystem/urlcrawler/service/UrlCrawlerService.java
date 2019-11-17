package com.tsystem.urlcrawler.service;

import com.tsystem.urlcrawler.dto.CrawlRequest;
import com.tsystem.urlcrawler.dto.RequestAcknowledgement;

public interface UrlCrawlerService {
   
	public RequestAcknowledgement crawl(CrawlRequest request);
}
