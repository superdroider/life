package com.lxj022.lifeassistant.data.bean;

/**
 * Created by liuxuejiao on 2016/11/5.
 * 快递公司实体类
 */
public class ExpressType {
    private String name;//快递公司名
    private String type;//快递公司代号
    private String letter;//首字母
    private String tel;//电话

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
