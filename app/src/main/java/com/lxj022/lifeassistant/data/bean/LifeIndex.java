package com.lxj022.lifeassistant.data.bean;

import java.io.Serializable;

/**
 * @author liuxuejiao
 * @des 生活指数
 */
public class LifeIndex implements Serializable{
    /**
     * "name": "晨练指数",
     * "level": "较不宜晨练",
     * "text": "受天气影响，较不宜晨练。",
     * "levelColor": "red",
     * "detail": "有降水，风力稍大，较不宜晨练，室外锻炼请携带雨具。建议年老体弱人群适当减少晨练时间。"
     */
    private String name;
    private String level;
    private String text;
    private String levelColor;
    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLevelColor() {
        return levelColor;
    }

    public void setLevelColor(String levelColor) {
        this.levelColor = levelColor;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
