package com.rank.ccms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.stereotype.Service;

@Service("vidyoConstants")
public class Constants {

    private static final Logger logger = Logger.getLogger(Constants.class);

    public static String vidyoportal;
    public static String vidyoportalUrl;
    public static String vidyoportalAdminServiceWSDL;
    public static String vidyoportalUserServiceWSDL;
    public static String vidyoportalReplayServiceWSDL;
    public static String adminUserId;
    public static String adminPwd;
    public static String recorderPrefix;
    public static String portalExtention;
    public static String socketHostPublic;

    public static String smtp_host;
    public static String from_address;
    public static String password;

    public static String ocrUrl;
    public static final String USER_TYPE_CUST = "CUSTOMER";
    public static final String USER_TYPE_EMP = "EMPLOYEE";
    public static String WEB_PATH_URL;
    public static String SCHEDULE_NOTIFICATION_TIME;
    public static String SCHEDULE_RM_NOT_READY_TIME;
    public static String RM1_Service_Start_Time;
    public static String RM1_Service_End_Time;
    public static String RM2_Service_Start_Time;
    public static String RM2_Service_End_Time;
    public static String appId;

    public Constants() {
        addUrlConstants();
    }

    private void addUrlConstants() {
        String projectHome = System.getenv("VIDEOBANKING_HOME");   // it will be FUTURE GENERALI HOME
        if (null == projectHome) {
            logger.info("VIDEOBANKING_HOME not set properly. Please do correction. So initializing Vidyo Portal with Default 'rank1.sandboxga.vidyo.com'.");
        } else {
            String vidyoPortalProps = projectHome + File.separator + "configuration" + File.separator + "vidyoConstants.properties";

            File vidyoPropsFile = new File(vidyoPortalProps);
            if (vidyoPropsFile.exists()) {
                Properties properties = new ManagedProperties();
                try {
                    properties.load(new FileInputStream(vidyoPropsFile));
                } catch (IOException e) {
                    logger.error("Error:vidyoPropsFile" + e.getMessage());
                }

                if (!properties.isEmpty()) {
                    vidyoportal = properties.getProperty("vidyoportal");
                    vidyoportalUrl = properties.getProperty("vidyoportalUrl");
                    vidyoportalAdminServiceWSDL = properties.getProperty("vidyoportalAdminServiceWSDL");
                    vidyoportalUserServiceWSDL = properties.getProperty("vidyoportalUserServiceWSDL");
                    vidyoportalReplayServiceWSDL = properties.getProperty("vidyoportalReplayServiceWSDL");
                    adminUserId = properties.getProperty("adminUserId");
                    adminPwd = properties.getProperty("adminPwd");
                    portalExtention = properties.getProperty("portalExtention");
                    recorderPrefix = properties.getProperty("recorderPrefix");
                    socketHostPublic = properties.getProperty("socketHostPublic");

                    smtp_host = properties.getProperty("smtp_host");
                    from_address = properties.getProperty("from_address");
                    password = properties.getProperty("password");
                    ocrUrl = properties.getProperty("ocrUrl");

                    WEB_PATH_URL = properties.getProperty("WEB_PATH_URL");
                    SCHEDULE_NOTIFICATION_TIME = properties.getProperty("SCHEDULE_NOTIFICATION_TIME");
                    SCHEDULE_RM_NOT_READY_TIME = properties.getProperty("SCHEDULE_RM_NOT_READY_TIME");
                    RM1_Service_Start_Time = properties.getProperty("RM1_Service_Start_Time");
                    RM1_Service_End_Time = properties.getProperty("RM1_Service_End_Time");
                    RM2_Service_Start_Time = properties.getProperty("RM2_Service_Start_Time");
                    RM2_Service_End_Time = properties.getProperty("RM2_Service_End_Time");
                    appId = properties.getProperty("appId");

                    logger.info("Property set was succesful............................");

                }
            }
        }
    }

}
