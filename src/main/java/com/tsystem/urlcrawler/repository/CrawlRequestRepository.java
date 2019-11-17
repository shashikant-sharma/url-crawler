package com.tsystem.urlcrawler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsystem.urlcrawler.enums.RequestStatus;
import com.tsystem.urlcrawler.model.CrawlRequestEntity;

@Repository
public interface CrawlRequestRepository extends JpaRepository<CrawlRequestEntity,Long> {
   
	List<CrawlRequestEntity> findByStatus(RequestStatus status);
	
	CrawlRequestEntity findByToken(String token);
	
}
