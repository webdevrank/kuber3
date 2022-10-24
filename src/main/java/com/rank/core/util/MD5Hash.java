package com.rank.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;


public class MD5Hash {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(MD5Hash.class);

    public static String md5Java(String message) throws Exception {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));

            //converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            digest = sb.toString();

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        return digest;
    }
}
