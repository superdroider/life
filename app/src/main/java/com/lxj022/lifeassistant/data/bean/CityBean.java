package com.lxj022.lifeassistant.data.bean;

/**
 * @author Superdroid
 * @time 2016/9/1 20:11
 * @des
 */
public class CityBean {
    private String weaid;
    private String citynm;
    private String cityno;
    private String cityid;

    public CityBean(String weaid, String citynm, String cityno, String cityid) {
        this.weaid = weaid;
        this.citynm = citynm;
        this.cityno = cityno;
        this.cityid = cityid;
    }

    public String getWeaid() {
        return weaid;
    }

    public void setWeaid(String weaid) {
        this.weaid = weaid;
    }

    public String getCitynm() {
        return citynm;
    }

    public void setCitynm(String citynm) {
        this.citynm = citynm;
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
}
