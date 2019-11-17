package com.tsystem.urlcrawler.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomApiResponse {
	private String message;

	private HttpStatus status = HttpStatus.OK;

	private List<String> errors = new ArrayList<>(0);

	private boolean hasError;

	private boolean warning;

	private Object data;
	

	public void setMessage(String variable, String message) {
		this.message = String.format(message, variable);
	}

	public void setMessage(String message, Object...var){
		this.message = String.format(message, var);
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void setErrors(String error) {
		this.errors = Arrays.asList(error);
	}

	public void setDataAndMessage(Object data, String message){
		this.data=data;
		this.message=message;
	}

	public void setErrorMessage(String message){
		this.hasError=true;
		this.message=message;
	}


	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", status=" + status + ", errors=" + errors + ", hasError="
				+ hasError + ", warning=" + warning + ", data=" + data + "]";
	}
}
