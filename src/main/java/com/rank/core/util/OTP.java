package com.rank.core.util;

import java.security.SecureRandom;
import java.util.Random;
import org.apache.log4j.Logger;

public class OTP {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(OTP.class);

    public static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        final int PW_LENGTH = 6;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++) {
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return pass.toString();
    }

    public static synchronized String generateFileName() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        String md5;
        final int PW_LENGTH = 9;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++) {
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        md5 = pass.toString();
        try {
            md5 = MD5Hash.md5Java(pass.toString());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.info(pass + "\t after md5 :" + md5);
        return md5 + pass.toString();
    }
}
