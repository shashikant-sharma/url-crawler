package com.tsystem.urlcrawler.exception;

public class InValidStausException extends RuntimeException {

	private static final long serialVersionUID = -4250812576507248970L;
	

    public InValidStausException(Long id, String message) {
        super(String.format(message, id));
    }
    
    public InValidStausException(String message) {
        super(message);
    }

	public InValidStausException(String code, String message) {
		   super(String.format(message, code));
	}

	
    
}
