package com.rank.ccms.util;

import io.socket.SocketIO;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import static jxl.biff.BaseCellFeatures.logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.ocpsoft.rewrite.servlet.config.Scheme;

public class SocketCommunication {

    public static void main(String s[]) {
        try {
            File ff = new File("D:\\chayan per\\piku\\PAN CARD\\Ranabir.jpg");
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://local.ranktechsolutions.com/visionapi/task/detect");
//            httpPost.addHeader("Content-type", "multipart/form-data");
//            httpPost.addHeader("Accept", "*/*");
            //StringBody langBody = new StringBody(LANG , ContentType.TEXT_PLAIN);
            FileBody uploadFilePart = new FileBody(ff);
            //logger.info("imagePath==11=="+imagePath);
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("docImg", uploadFilePart);
            reqEntity.addPart("app_id", new StringBody("609cd14f96d4556af013fc79"));
            reqEntity.addPart("extract", new StringBody("true"));
            reqEntity.addPart("type", new StringBody("pan"));
            httpPost.setEntity(reqEntity);
            //logger.info("imagePath==22=="+imagePath);

            HttpResponse response1 = httpclient.execute(httpPost);
            HttpEntity responseEntity = response1.getEntity();
            //logger.info("imagePath=33==="+imagePath);
            String response = "Failure";
            if (responseEntity != null) {
                //logger.info("imagePath==44=="+imagePath);
                response = EntityUtils.toString(responseEntity);
            }
            logger.info("Response==========" + response);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void send(SocketIO socket, final String sendTo, final String msg) {
        JSONObject payLoad = new JSONObject();

        try {
            payLoad.put("customer", sendTo);
            payLoad.put("message", msg);
        } catch (JSONException e) {
        }
    }
}
