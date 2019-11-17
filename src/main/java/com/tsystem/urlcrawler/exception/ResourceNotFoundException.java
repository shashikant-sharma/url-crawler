package com.tsystem.urlcrawler.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4250812576507248970L;


    public ResourceNotFoundException(Long id, String message) {
        super(String.format(message, id));
    }
    
    public ResourceNotFoundException(String message) {
        super(message);
    }

	public ResourceNotFoundException(String code, String message) {
		   super(String.format(message, code));
	}
	
	
    
}
