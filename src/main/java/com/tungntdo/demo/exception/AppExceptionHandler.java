package com.tungntdo.demo.exception;

import com.tungntdo.demo.config.constant.ErrorMessages;
import com.tungntdo.demo.payload.response.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleCustomValidationException(MethodArgumentNotValidException exception) {
        List<String> details = new ArrayList<>();

        for (ObjectError error : exception.getAllErrors()) {
            String detail = error.getDefaultMessage();
            details.add(detail);
        }

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                false,
                ErrorMessages.VALIDATION_ERROR.getErrorMessage(),
                details
        );

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

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
