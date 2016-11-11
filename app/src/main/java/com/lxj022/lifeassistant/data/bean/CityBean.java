package com.lxj022.lifeassistant.data.bean;

import java.io.Serializable;

/**
 * @author Superdroid
 * @time 2016/9/1 20:11
 * @des
 */
public class CityBean implements Serializable{
    private String areaId;
    private String cityName;
    private String provinceName;

    public CityBean(String areaId, String cityName, String provinceName) {
        this.areaId = areaId;
        this.cityName = cityName;
        this.provinceName = provinceName;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }
}
