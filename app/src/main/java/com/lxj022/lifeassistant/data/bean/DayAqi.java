package com.lxj022.lifeassistant.data.bean;

/**
 * @author liuxuejioa
 * @des 未来空气质量
 */
public class DayAqi {
    private String date;

    private String aqi;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAqi() {
        return this.aqi;
    }
}
