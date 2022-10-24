package com.rank.ccms.util;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSafeSimpleDateFormat extends SimpleDateFormat {

    private final String pattern;

    public ThreadSafeSimpleDateFormat(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return new SimpleDateFormat(pattern).format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return new SimpleDateFormat(pattern).parse(source, pos);
    }
}
