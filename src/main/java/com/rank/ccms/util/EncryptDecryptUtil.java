package com.rank.ccms.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class EncryptDecryptUtil {

    private static final Logger logger = Logger.getLogger(EncryptDecryptUtil.class);
    public static final String key = "k12@sdfyfd!@d#d$"; // 128 bit key
    public static final String initVector = "RandomInitVector"; // 16 bytes IV

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(EncryptDecryptUtil.initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(EncryptDecryptUtil.key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            logger.info("encrypted string: "
                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
        }

        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(EncryptDecryptUtil.initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(EncryptDecryptUtil.key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
        }

        return null;
    }

    public static String randomNumberGeneration(int limit) {

        int min = 1, max = 9;
        long number;
        Random r = new Random();
        limit = limit > 10 ? 10 : limit;
        number = r.nextInt((max - min) + 1) + min;
        for (int i = 0; i < limit; i++) {

            number = (number * 10) + (r.nextInt((max - min) + 1) + min);

        }

        return Long.toString(number);

    }
}
