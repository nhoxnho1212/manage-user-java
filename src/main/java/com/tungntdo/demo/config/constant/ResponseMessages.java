package com.tungntdo.demo.config.constant;

public interface ResponseMessages {
    enum USER {
        DELETE_SUCCESS("You successfully deleted profile of: ");

        private String message;

        USER(String message) {
            this.message = message;
        }
    }
}
