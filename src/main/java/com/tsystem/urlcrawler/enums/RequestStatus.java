package com.tsystem.urlcrawler.enums;

import com.tsystem.urlcrawler.exception.InValidStausException;

public enum RequestStatus {
  
   SUBMITTED("Submitted"),INPROCESS("In-Process"),PROCESSED("Processed"),FAILED("Failed");
    
	String key;
	
	RequestStatus(String key) {
		this.key = key;
	}
	
	
	public static RequestStatus getStatus(String status) {
		try {
			return RequestStatus.valueOf(status.toUpperCase());
		} catch (Exception e) {
			throw new InValidStausException("Invalid status");
		}
	}

}
