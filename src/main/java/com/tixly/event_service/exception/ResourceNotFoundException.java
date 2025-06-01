package com.tixly.event_service.exception;

import org.springframework.http.HttpStatus;


public class ResourceNotFoundException extends ApiException {
	public ResourceNotFoundException(Integer id, String resourceName ) {
		super(HttpStatus.NOT_FOUND, String.format("%s with this %d isn't found", resourceName, id));
	}
}
