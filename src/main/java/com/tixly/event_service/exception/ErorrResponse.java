package com.tixly.event_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ErorrResponse {
	private HttpStatus status;
	private String message;

}
