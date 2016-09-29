package com.lxj022.lifeassistant.util;

import android.util.Log;

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
     *
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

    /**
     * 根据日期获得星期
     *
     * @param time
     * @return
     */
    public static String getWeekOfDate(long time) {
        Date date = new Date();
        date.setTime(time);
        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * long 转化为MM-dd 周（一|二）例：09-29 周四
     *
     * @param timeStr
     * @return
     */
    public static String getWeekAndDate(long timeStr) {
        Log.e("tag", "getWeekAndDate: "+timeStr);
        String day = longToString(timeStr);
        String week = getWeekOfDate(timeStr);

        return day + " " + week;

    }

    /**
     * 格式化时间为HH:mm
     * @param timeStr
     * @return
     */
    public static String getHHmm(long timeStr) {
        Date currentTime = new Date();
        currentTime.setTime(timeStr);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateString = formatter.format(currentTime);
        Log.e("tag", "getHHmm: "+timeStr +">>>"+dateString);
        return dateString;

    }
}
