package com.lxj022.lifeassistant.data;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @des 时间格式化工具类
 */
public class DateFormatUtil {

    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getCurrentMinute() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * long 转为 MM-dd
     * @param currentTime
     * @return
     */
    public static String longToString(long currentTime) {
        DateFormat formatter = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        return formatter.format(calendar.getTime());
    }
    /**
     * 得到现在小时
     *
     * @return
     */
    public static String getCurrentHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }
}
