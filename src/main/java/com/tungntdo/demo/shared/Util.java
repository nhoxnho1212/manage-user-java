package com.tungntdo.demo.shared;

import com.tungntdo.demo.config.constant.GlobalConstants;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Util {
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte h : hash) {
            String hex = Integer.toHexString(0xff & h);
            if (1 == hex.length()) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String generateUniqueKeyWithUUIDv4() throws NoSuchAlgorithmException {
        UUID uuid = UUID.randomUUID();
        MessageDigest salt = MessageDigest.getInstance(GlobalConstants.SECURITY_HASH_ALGORITHM.SHA_256);
        salt.update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        String digest = Util.bytesToHex(salt.digest());

        return digest;
    }
}
