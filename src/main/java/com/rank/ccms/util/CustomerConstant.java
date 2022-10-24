package com.rank.ccms.util;

import com.rank.ccms.dto.ScheduleCallDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerConstant {

    public static List<ScheduleCallDto> scheduleCallList = Collections.synchronizedList(new ArrayList<ScheduleCallDto>());
    public static Map<String, Integer> customerLoginFlag = new HashMap<>();
    public static Map<String, String> customerMediumFlag = new HashMap<>();
    public static Map<String, Timestamp> customerLoginTime = new HashMap<>();

    public static boolean isNumericCustom(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        int i = 0;
        int stringLength = string.length();
        if (string.charAt(0) == '-') {
            if (stringLength > 1) {
                i++;
            } else {
                return false;
            }
        }
        if (!Character.isDigit(string.charAt(i))
                || !Character.isDigit(string.charAt(stringLength - 1))) {
            return false;
        }
        i++;
        stringLength--;
        if (i >= stringLength) {
            return true;
        }
        for (; i < stringLength; i++) {
            if (!Character.isDigit(string.charAt(i))
                    && string.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

}
