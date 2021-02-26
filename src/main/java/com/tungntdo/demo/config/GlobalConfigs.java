package com.tungntdo.demo.config;

public class GlobalConfigs {
    public interface URL {
        interface USER {
            String MAIN = "/user";
            String LOGIN = MAIN + "/login";
        }
    }

    public interface TABLE {
        // Cannot use "user" as the name of table in PostgresSQL because it is a reserved name.
        String USER = "users";
    }

    public interface USER_REQUEST_VALIDATION {
        interface USER {
            int MIN_NAME_LENGTH = 1;
            int MAX_NAME_LENGTH = 50;
            int MIN_PASSWORD_LENGTH = 6;
            int MAX_PASSWORD_LENGTH = 256;
        }
    }

    public interface MESSAGES_VALIDATION {
        String FIRST_NAME_AT_LEAST_1_CHARACTERS = "First name at least 1 characters";
        String LAST_NAME_AT_LEAST_1_CHARACTERS = "Last name at least 1 characters";
        String EMAIL_NOT_VALID = "Email not valid";
        String PASSWORD_AT_LEAST_6_CHARACTERS = "Password at least 6 characters";
    }

    public interface JWT_SECURITY {
        interface ACCESS_TOKEN {
            long EXPIRATION_TIME = GlobalConstants.TIME.TEN_SECONDS;
            String TOKEN_PREFIX = "Bearer ";
            String HEADER_STRING = "Authorization";
            String TOKEN_SECRET = "2wqs12wsad43re";
        }
    }
}
