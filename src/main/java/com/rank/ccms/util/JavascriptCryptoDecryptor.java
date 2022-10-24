package com.rank.ccms.util;

import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;

public class JavascriptCryptoDecryptor {

    public static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();

        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    public static void main(String args[]) {
        String password = "urjSGp+WgwFbFrt+6UeVXg==";
        try {
            String dpassword = JavascriptCryptoDecryptor.decryptAESEncryptWithSaltAndIV("H4GuXCaqfGIOggc81pP1Dg==", "1234", "9b7514a634049ecdea46276a1e177f8f", "fe0788c01aa06a777b291fe11bfe59c7");
            // Encode using basic encoder
            String base64encodedString = Base64.getEncoder().encodeToString(
                    "callId=208&resourceId=RM17908441".getBytes("utf-8"));
            System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);
            // Decode
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

            System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
            Map<String, String> getQueryMap = getQueryMap(new String(base64decodedBytes, "utf-8"));
            for (Map.Entry<String, String> entry : getQueryMap.entrySet()) {
                String string = entry.getKey();
                String string1 = entry.getValue();
                System.out.println("Key:" + string + "VALUE:" + string1);
            }

        } catch (Exception ex) {
            Logger.getLogger(JavascriptCryptoDecryptor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static SecretKey generateKeyFromPasswordWithSalt(String password, byte[] saltBytes) throws GeneralSecurityException {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltBytes, 100, 128);
        String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA1);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    public static String decryptAESEncryptWithSaltAndIV(String encryptedData, String key, String salt, String iv) throws Exception {

        byte[] saltBytes = hexStringToByteArray(salt);
        byte[] ivBytes = hexStringToByteArray(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec sKey = (SecretKeySpec) generateKeyFromPasswordWithSalt(key, saltBytes);

        Cipher c = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
        c.init(Cipher.DECRYPT_MODE, sKey, ivParameterSpec);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);

        return decryptedValue;
    }

    public String generateSecret() {
        return "1234455553dsfdfdsfdsf";   //generate always random number and send for each request
    }

}
