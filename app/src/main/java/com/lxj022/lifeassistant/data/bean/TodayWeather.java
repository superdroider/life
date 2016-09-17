package com.lxj022.lifeassistant.data.bean;

import java.util.List;

/**
 * @des 今日天气实体类
 */
public class TodayWeather {
    /**
     *  "sk_temp": "25",
     "sk_time": "12:55",
     "windDirection": "北风",
     "windPower": "2级",
     "humidity": "70%",
     "rainProbability": {
     "today": "81%",
     "tomorrow": "55%"
     },
     "sunTime": {
     "today": "- - 小时",
     "tomorrow": "- - 小时"
     },
     "sk_ATM": "1007 hPa",
     "uvDesc": {
     "today": "弱",
     "tomorrow": "弱",
     "afterTomorrow": "中等"
     },
     "humidity_48hours": "91%",
     "humidity_72hours": "85%"
     */

    private  String temp;
    private  String wholeTemp;
    private String time;
    private String windDirection;
    private String windPower;
    private String humidity;
    private String sk_ATM;//1000kpa
    private String condition;//多云
    private String type;//28
    private String tips;//今天有雨
    private String uvDesc;//紫外线弱
    private List<HourData> hourDatas;
    private List<LifeIndex> lifeIndices;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWholeTemp() {
        return wholeTemp;
    }

    public void setWholeTemp(String wholeTemp) {
        this.wholeTemp = wholeTemp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSk_ATM() {
        return sk_ATM;
    }

    public void setSk_ATM(String sk_ATM) {
        this.sk_ATM = sk_ATM;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getUvDesc() {
        return uvDesc;
    }

    public void setUvDesc(String uvDesc) {
        this.uvDesc = uvDesc;
    }

    public List<HourData> getHourDatas() {
        return hourDatas;
    }

    public void setHourDatas(List<HourData> hourDatas) {
        this.hourDatas = hourDatas;
    }

    public List<LifeIndex> getLifeIndices() {
        return lifeIndices;
    }

    public void setLifeIndices(List<LifeIndex> lifeIndices) {
        this.lifeIndices = lifeIndices;
    }
}
