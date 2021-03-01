package com.tungntdo.demo.exception;

import com.tungntdo.demo.model.response.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                false,
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
