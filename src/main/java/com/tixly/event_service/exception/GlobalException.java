package com.tixly.event_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){
        ErorrResponse erorrResponse = new ErorrResponse(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus())
                .body(erorrResponse);
    }
}
