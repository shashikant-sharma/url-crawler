package com.tsystem.urlcrawler.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tsystem.urlcrawler.dto.CrawlResponse;
import com.tsystem.urlcrawler.dto.PageDetailsDto;

public class WebCrawlerWithDepth {
    private static final int MAX_DEPTH = 2;
    
    private Set<String> links; 
    
    private Set<PageDetailsDto> details;
    
    private int totalImages;

    public WebCrawlerWithDepth() {
        links = new HashSet<>();
        details = new HashSet<>();
    }
    public void crawl(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
        	
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                Elements imagesOnPage = document.select("img[src]");
                PageDetailsDto linkDetails = new PageDetailsDto();
                linkDetails.setPageLink(URL);
                linkDetails.setPageTitle(document.title());
                linkDetails.setImageCount(imagesOnPage.size());
                totalImages = totalImages+imagesOnPage.size();
                details.add(linkDetails);
                System.out.println(linkDetails);
                depth++;
                for (Element page : linksOnPage) {
                	crawl(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
            catch (java.lang.IllegalArgumentException e) {
            	 System.err.println("For '" + URL + "': " + e.getMessage());
			}
        }
    }
    
    public CrawlResponse getResponse(){
    	CrawlResponse response = new CrawlResponse();
    	response.setDetails(this.details);
    	response.setTotalLinks(details.size());
    	response.setTotalImages(totalImages);
    	response.setDetails(details);
    	return response;
    }
    
    
    
	
}
