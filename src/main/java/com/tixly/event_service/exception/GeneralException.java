package com.tixly.event_service.exception;

import org.springframework.http.HttpStatus;

public class GeneralException extends ApiException{
    public GeneralException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}
