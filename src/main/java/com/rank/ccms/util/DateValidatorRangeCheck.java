package com.rank.ccms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class DateValidatorRangeCheck {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DateValidatorRangeCheck.class);
    private static int hour;
    private static int min;
    private static String AM_PM;

    public static synchronized boolean checkCurrentDateTimeWithInRange() {

        String projectHome = System.getenv("VIDEOBANKING_HOME");
        Properties prop = new Properties();
        InputStream input;
        double strtTime = 8.0d;
        double endTime = 20.0d;
        boolean flag = false;
        try {
            try {
                String dateTimeProps = projectHome + File.separator + "configuration" + File.separator + "dateTimeValidationRange.properties";
                input = new FileInputStream(dateTimeProps);
                prop.load(input);
                strtTime = Double.parseDouble(prop.getProperty("startTime"));
                endTime = Double.parseDouble(prop.getProperty("endTime"));
                logger.info("Start Time:" + strtTime + " End Time :" + endTime);
            } catch (IOException | NumberFormatException e) {
                logger.info("Got Error @@checkCurrentDateTimeWithInRange" + e.getMessage());
            }
            Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            min = c.get(Calendar.MINUTE);
            int ds = c.get(Calendar.AM_PM);
            if (ds == 0) {
                AM_PM = "am";
            } else {
                AM_PM = "pm";
            }
            logger.info("Hour:" + hour + "Min:" + min + "AM_PM:" + AM_PM);
            if ((hour >= strtTime && AM_PM.matches("am"))
                    || (hour < endTime && AM_PM.matches("pm"))
                    || (hour == 12 && AM_PM.matches("pm"))) {
                logger.info("Not In Down Time..............................");
                flag = true;
            } else {
                logger.info("In Down Time.........");
                flag = false;
            }
        } catch (Exception e) {
            logger.info("Got Some Error In checkCurrentDateTimeWithInRange:" + e.getMessage());
        }
        return flag;
    }

    public static Date addTimeBySecondsCustom(Date date, int sec) {
        logger.info("Given date:" + date);
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(date.getTime());
        calender.add(Calendar.SECOND, sec);
        Date changeDate = calender.getTime();
        logger.info("changeDate ..:" + changeDate);
        return changeDate;
    }

    public static Date addTimeInMin(Date date, int min) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            calendar.add(Calendar.MINUTE, min);

            return calendar.getTime();
        } catch (Exception e) {
            logger.error("ERROR : ", e);
            return null;
        }
    }

}
