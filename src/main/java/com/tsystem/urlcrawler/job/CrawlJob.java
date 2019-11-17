package com.tsystem.urlcrawler.job;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tsystem.urlcrawler.dto.PageDetailsDto;
import com.tsystem.urlcrawler.enums.RequestStatus;
import com.tsystem.urlcrawler.model.PageDetails;
import com.tsystem.urlcrawler.repository.CrawlRequestRepository;
import com.tsystem.urlcrawler.service.WebCrawlerWithDepth;

@EnableAsync
@Component
public class CrawlJob {
	
	
	
	@Autowired
	private CrawlRequestRepository requestRepository;
	
	//BlockingQueue blockingQueue = new LinkedBlockingDeque(2);
	
	@Async
    @Scheduled(fixedRate = 2000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
		requestRepository.findByStatus(RequestStatus.SUBMITTED).forEach(request->{
			System.out.println("found request");
			request.setStatus(RequestStatus.INPROCESS);
			requestRepository.save(request);
			WebCrawlerWithDepth crawler = new WebCrawlerWithDepth();
			crawler.crawl(request.getLink(), request.getDepth());
			request.setStatus(RequestStatus.PROCESSED);
			Set<PageDetails> dto = crawler.getResponse().getDetails().stream().map(PageDetailsDto::toEntity).collect(Collectors.toSet());
			request.setDetails(dto);
			request.setTotalImages(crawler.getResponse().getTotalImages());
			request.setTotalLinks(crawler.getResponse().getTotalLinks());
			requestRepository.save(request);
		});
    }
}
