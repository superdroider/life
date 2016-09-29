package com.lxj022.lifeassistant.data.bean;

import java.io.Serializable;

/**
 * @des 每小时天气
 */
public class HourData implements Serializable{
    /**
     * "hour": "00",
     * "icon": "26",
     * "temp": "26",
     * "tq": "阴",
     * "day": "1473091200",
     * "aqi": "10"
     */
    private String hour;
    private String icon;
    private String temp;
    private String tq;
    private String day;
    private int aqi;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTq() {
        return tq;
    }

    public void setTq(String tq) {
        this.tq = tq;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }
}
