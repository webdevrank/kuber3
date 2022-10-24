package com.rank.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Random;

public class SendTranscationalSMS {

    public static String SMSSender(String user, String pwd, String to, String msg, String sid, String fl) throws IOException {
        String rsp;
        String retval = "";

        // Construct The Post Data
        String data = URLEncoder.encode("user", "UTF-8") + "="
                + URLEncoder.encode(user, "UTF-8");
        data += "&" + URLEncoder.encode("pwd", "UTF-8") + "="
                + URLEncoder.encode(pwd, "UTF-8");
        data += "&" + URLEncoder.encode("to", "UTF-8") + "="
                + URLEncoder.encode(to, "UTF-8");
        data += "&" + URLEncoder.encode("msg", "UTF-8") + "="
                + URLEncoder.encode(msg, "UTF-8");
        data += "&" + URLEncoder.encode("sid", "UTF-8") + "="
                + URLEncoder.encode(sid, "UTF-8");
        data += "&" + URLEncoder.encode("fl", "UTF-8") + "="
                + URLEncoder.encode(fl, "UTF-8");
        data += "&" + URLEncoder.encode("gwid", "UTF-8") + "="
                + URLEncoder.encode("2", "UTF-8");
        // Push the HTTP Request
        URL url = new URL("http://login.smsgatewayhub.com/smsapi/pushsms.aspx");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        BufferedReader rd;
        try (OutputStreamWriter wr = new OutputStreamWriter(
                conn.getOutputStream())) {
            wr.write(data);
            wr.flush();
            rd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line…
                retval += line;
            }
        }
        rd.close();

        rsp = retval;

        return rsp;
    }

    public static String generateOTP() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        final int PW_LENGTH = 6;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++) {
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return pass.toString();

    }
}
