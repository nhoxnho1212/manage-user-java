package com.tungntdo.demo.exception;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 1427029383466218542L;

    public UserServiceException(String message) {
        super(message);
    }
}
