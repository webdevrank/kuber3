package com.rank.ccms.util;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;

public class GenerateCustId {

    private static final Logger logger = Logger.getLogger(GenerateCustId.class);

    public static String generateCustId() {

       
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

        final int PW_LENGTH = 15;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++) {
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        logger.info("Cust_Id<><><><><>" + pass.toString());
        return pass.toString();
    }
}
