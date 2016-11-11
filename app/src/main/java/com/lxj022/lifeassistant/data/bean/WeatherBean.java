package com.lxj022.lifeassistant.data.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @des 天气实体类
 */
public class WeatherBean implements Serializable {
    private String cityId;//城市id
    private String cityName;//城市名
    private List<DayWeather> days7;//未来七天天气
    private List<LifeIndex> zs;//今日生活指数
    private List<LifeIndex> zs_tomorrow;//明日生活指数
    private List<DayAqi> dayAqiList;//未来空气质量
    private List<DayWeather> days8;//未来8天天气
    private String currentTemp;//当前温度
    private String currentHumidity;//当前天气湿度
    private String tomorrowHumidity;//当前天气湿度
    private String currentWindDirection;//当前风向
    private String currentWindPower;//当前风力
    private String currentWea;//当前天气描述
    private String currentWeaType;//当前天气描述
    private String currentAqi;//当前空气质量
    private String tomorrowAqi;//明天空气质量
    private String updateTime;//更新时间
    private String stm;//当前大气压
    private String todayUv;//今日紫外线强度
    private String tomorrowUv;//明日紫外线强度
    private String todayRise;
    private String todaySet;
    private String tomorrowRise;
    private String tomorrowSet;
    private List<HourData> hourDatas;//48小时天气数据

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<DayWeather> getDays7() {
        return days7;
    }

    public void setDays7(List<DayWeather> days7) {
        this.days7 = days7;
    }

    public List<LifeIndex> getZs() {
        return zs;
    }

    public void setZs(List<LifeIndex> zs) {
        this.zs = zs;
    }

    public List<LifeIndex> getZs_tomorrow() {
        return zs_tomorrow;
    }

    public void setZs_tomorrow(List<LifeIndex> zs_tomorrow) {
        this.zs_tomorrow = zs_tomorrow;
    }

    public List<DayAqi> getDayAqiList() {
        return dayAqiList;
    }

    public void setDayAqiList(List<DayAqi> dayAqiList) {
        this.dayAqiList = dayAqiList;
    }

    public List<DayWeather> getDays8() {
        return days8;
    }

    public void setDays8(List<DayWeather> days8) {
        this.days8 = days8;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getCurrentWea() {
        return currentWea;
    }

    public void setCurrentWea(String currentWea) {
        this.currentWea = currentWea;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStm() {
        return stm;
    }

    public void setStm(String stm) {
        this.stm = stm;
    }

    public String getTodayUv() {
        return todayUv;
    }

    public void setTodayUv(String todayUv) {
        this.todayUv = todayUv;
    }

    public String getTomorrowUv() {
        return tomorrowUv;
    }

    public void setTomorrowUv(String tomorrowUv) {
        this.tomorrowUv = tomorrowUv;
    }

    public String getTodayRise() {
        return todayRise;
    }

    public void setTodayRise(String todayRise) {
        this.todayRise = todayRise;
    }

    public String getTodaySet() {
        return todaySet;
    }

    public void setTodaySet(String todaySet) {
        this.todaySet = todaySet;
    }

    public String getTomorrowRise() {
        return tomorrowRise;
    }

    public void setTomorrowRise(String tomorrowRise) {
        this.tomorrowRise = tomorrowRise;
    }

    public String getTomorrowSet() {
        return tomorrowSet;
    }

    public void setTomorrowSet(String tomorrowSet) {
        this.tomorrowSet = tomorrowSet;
    }

    public List<HourData> getHourDatas() {
        return hourDatas;
    }

    public void setHourDatas(List<HourData> hourDatas) {
        this.hourDatas = hourDatas;
    }

    public String getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(String currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public String getCurrentWindDirection() {
        return currentWindDirection;
    }

    public void setCurrentWindDirection(String currentWindDirection) {
        this.currentWindDirection = currentWindDirection;
    }

    public String getCurrentWindPower() {
        return currentWindPower;
    }

    public void setCurrentWindPower(String currentWindPower) {
        this.currentWindPower = currentWindPower;
    }

    public String getCurrentWeaType() {
        return currentWeaType;
    }

    public void setCurrentWeaType(String currentWeaType) {
        this.currentWeaType = currentWeaType;
    }

    public String getCurrentAqi() {
        return currentAqi;
    }

    public void setCurrentAqi(String currentAqi) {
        this.currentAqi = currentAqi;
    }

    public String getTomorrowAqi() {
        return tomorrowAqi;
    }

    public void setTomorrowAqi(String tomorrowAqi) {
        this.tomorrowAqi = tomorrowAqi;
    }

    public String getTomorrowHumidity() {
        return tomorrowHumidity;
    }

    public void setTomorrowHumidity(String tomorrowHumidity) {
        this.tomorrowHumidity = tomorrowHumidity;
    }
}
