package com.lxj022.lifeassistant.data.bean;

/**
 * @author liuxuejiao
 * @des 未来七日天气
 */
public class DayWeather {
    private long time;

    private String wholeWea;//天气

    private String wholeTemp;//温度

    private String wholeWindDirection;//风力

    private String dayImg;//天气图片

    private String nightImg;//天气图片

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getWholeWea() {
        return wholeWea;
    }

    public void setWholeWea(String wholeWea) {
        this.wholeWea = wholeWea;
    }

    public String getWholeTemp() {
        return wholeTemp;
    }

    public void setWholeTemp(String wholeTemp) {
        this.wholeTemp = wholeTemp;
    }

    public String getWholeWindDirection() {
        return wholeWindDirection;
    }

    public void setWholeWindDirection(String wholeWindDirection) {
        this.wholeWindDirection = wholeWindDirection;
    }

    public String getDayImg() {
        return dayImg;
    }

    public void setDayImg(String dayImg) {
        this.dayImg = dayImg;
    }

    public String getNightImg() {
        return nightImg;
    }

    public void setNightImg(String nightImg) {
        this.nightImg = nightImg;
    }
}
