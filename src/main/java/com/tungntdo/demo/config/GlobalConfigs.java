package com.tungntdo.demo.config;

import com.tungntdo.demo.SpringApplicationContext;
import com.tungntdo.demo.config.constant.GlobalConstants;

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
        String ADDRESS = "address";
        String GEOGRAPHY = "geography";
        String TOKEN = "token";
        String TOKEN_TYPE = "token_type";

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

    public static class JWT_SECURITY {

        public interface ACCESS_TOKEN {
            long EXPIRATION_TIME = GlobalConstants.TIME.ONE_MINUS;
            String TOKEN_PREFIX = "Bearer ";
            String HEADER_STRING = "Authorization";
        }

        public static String getTokenSecret() {
             AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
             return appProperties.getTokenSecret();
        }
    }
}
