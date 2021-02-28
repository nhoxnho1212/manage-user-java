package com.tungntdo.demo.config;

public class GlobalConstants {

    public interface SECURITY_HASH_ALGORITHM {
        String SHA_256 = "SHA-256";
    }

    public interface TIME {
        long TEN_SECONDS = 10 * 1000;
        long ONE_MINUS = 60 * 1000;
        long ONE_HOUR = 60 * 60 * 1000;
        long ONE_DAY = 24 * 60 * 1000;
    }

    public enum TOKEN_TYPE {
        ACCESS_TOKEN, REFRESH_TOKEN, EMAIL_TOKEN
    }

}
