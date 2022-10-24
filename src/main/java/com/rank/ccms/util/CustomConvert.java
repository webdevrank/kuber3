package com.rank.ccms.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class CustomConvert {

    private static final Logger logger = Logger.getLogger(CustomConvert.class);

    public static Timestamp javaDateToTimeStamp(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS zzz");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        return new Timestamp(dateFormat.parse(dateFormat.format(date)).getTime());
    }

    public static Timestamp javaDateToTimeStampWithoutDate(Date date) throws ParseException {
        logger.info("" + date);
        SimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("HH:mm");
        return new Timestamp(dateFormat.parse(dateFormat.format(date)).getTime());
    }

    public static Date convertTimeZone(String dateInString, String timezone, String serverTimezone) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date = formatter.parse(dateInString);

        TimeZone tz = TimeZone.getTimeZone(timezone);
        Calendar cal1 = GregorianCalendar.getInstance(tz);
        int offsetInMillis1 = tz.getOffset(cal1.getTimeInMillis());

        TimeZone tzInAmerica = TimeZone.getTimeZone(serverTimezone);
        Calendar cal2 = GregorianCalendar.getInstance(tzInAmerica);
        int offsetInMillis2 = tzInAmerica.getOffset(cal2.getTimeInMillis());

        int diff = offsetInMillis2 - offsetInMillis1;

        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime() + diff);
        Date date1 = new Date(timestamp.getTime());

        return date1;
    }

    public static Date convertScheduleTimeZone(String dateInString, String timezone, String serverTimezone) throws ParseException {

        SimpleDateFormat formatter1 = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date date = formatter1.parse(dateInString);

        TimeZone tz = TimeZone.getTimeZone(timezone);
        Calendar cal1 = GregorianCalendar.getInstance(tz);
        cal1.setTime(date);
        int offsetInMillis1 = tz.getOffset(cal1.getTimeInMillis());

        TimeZone tzInAmerica = TimeZone.getTimeZone(serverTimezone);
        Calendar cal2 = GregorianCalendar.getInstance(tzInAmerica);
        int offsetInMillis2 = tzInAmerica.getOffset(cal2.getTimeInMillis());

        int diff = offsetInMillis2 - offsetInMillis1;
        cal1.add(Calendar.SECOND, diff / 1000);

        Date date1 = new Date(cal1.getTimeInMillis());
        formatter1.setTimeZone(tzInAmerica);
        String result = formatter1.format(date1);

        return formatter1.parse(result);
    }

    public static Date convertScheduleTimeZoneF(String dateInString, String servertimezone, String clientTimezone) throws ParseException {

        SimpleDateFormat formatter1 = new ThreadSafeSimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = formatter1.parse(dateInString);

        TimeZone tz = TimeZone.getTimeZone(servertimezone);
        Calendar cal1 = GregorianCalendar.getInstance(tz);
        cal1.setTime(date);
        int offsetInMillis1 = tz.getOffset(cal1.getTimeInMillis());

        TimeZone tzInAmerica = TimeZone.getTimeZone(clientTimezone);
        Calendar cal2 = GregorianCalendar.getInstance(tzInAmerica);
        int offsetInMillis2 = tzInAmerica.getOffset(cal2.getTimeInMillis());

        int diff = offsetInMillis2 - offsetInMillis1;
        cal1.add(Calendar.SECOND, diff / 1000);

        Date date1 = new Date(cal1.getTimeInMillis());
        formatter1.setTimeZone(tzInAmerica);
        String result = formatter1.format(date1);

        return formatter1.parse(result);
    }

}
