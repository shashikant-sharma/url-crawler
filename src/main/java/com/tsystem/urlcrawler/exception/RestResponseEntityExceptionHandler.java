package com.tsystem.urlcrawler.exception;

import java.io.CharConversionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tsystem.urlcrawler.dto.CustomApiResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<CustomApiResponse> handleConflict(RuntimeException ex, WebRequest request) {
		CustomApiResponse response = new CustomApiResponse();
        updateResponse(response,ex,HttpStatus.BAD_REQUEST);
		response.setMessage("Somthing went wrong with this request");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomApiResponse> resourceNotFound(ResourceNotFoundException ex) {
		CustomApiResponse response = new CustomApiResponse();
        updateResponse(response,ex,HttpStatus.OK);
		response.setMessage(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<CustomApiResponse> constraintViolationException(DataIntegrityViolationException ex) {
		CustomApiResponse response = new CustomApiResponse();
        updateResponse(response,ex,HttpStatus.BAD_REQUEST);
		response.setMessage("MySQLIntegrityConstraintViolationException");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		CustomApiResponse response = new CustomApiResponse();
		String exceptionName = mostSpecificCause.getClass().getName();
		String message = mostSpecificCause.getMessage();
		response.setMessage(exceptionName);
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setHasError(Boolean.TRUE);
		response.setErrors(message);
		return new ResponseEntity<>(response, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
		String error;
		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
			errors.add(error);
		}
		String errorMessage="Method Argument Not Valid";
		CustomApiResponse response = new CustomApiResponse();
		response.setErrors(errors);
		if(Objects.nonNull(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())) {
			errorMessage=ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		}
		response.setMessage(errorMessage);
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setHasError(Boolean.TRUE);
		return new ResponseEntity<>(response, headers, status);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName() + " but getting "
				+ ex.getValue();
		CustomApiResponse response = new CustomApiResponse();
		response.setMessage("Argument Type Mismatch");
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setErrors(error);
		response.setHasError(Boolean.TRUE);
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<CustomApiResponse> invalidFormatException(InvalidFormatException ex) {
		CustomApiResponse response = new CustomApiResponse();
		response.setMessage("InvalidFormatException");
		List<String> errors = new ArrayList<>();
		response.setHasError(Boolean.TRUE);
		errors.add(ex.getLocalizedMessage());
		response.setErrors(errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CharConversionException.class)
	public ResponseEntity<CustomApiResponse> charConversionException(CharConversionException ex) {
		CustomApiResponse response = new CustomApiResponse();
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setMessage("CharConversionException");
		List<String> errors = new ArrayList<>();
		response.setHasError(Boolean.TRUE);
		errors.add("Invalid file type. Please upload file with .csv extension");
		response.setErrors(errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JpaObjectRetrievalFailureException.class)
	public ResponseEntity<CustomApiResponse> entityNotFoundException(JpaObjectRetrievalFailureException ex) {
		CustomApiResponse response = new CustomApiResponse();
        updateResponse(response,ex,HttpStatus.BAD_REQUEST);
        response.setMessage(ex.getMostSpecificCause().getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> allExceptions(Exception ex, WebRequest request) {
		CustomApiResponse response = new CustomApiResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setHasError(Boolean.TRUE);
		response.setErrors(ex.getLocalizedMessage());
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}

	
	private CustomApiResponse updateResponse(CustomApiResponse response, RuntimeException e,HttpStatus status){
		response.setStatus(status);
		response.setMessage(e.getMessage());
		List<String> errors = new ArrayList<>();
		response.setHasError(Boolean.TRUE);
        errors.add(e.getLocalizedMessage());
        response.setErrors(errors);
		return response;
	}
	
}
