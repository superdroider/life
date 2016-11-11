package com.lxj022.lifeassistant.data.bean;

import java.io.Serializable;

/**
 * Created by liuxuejiao on 2016/11/6.
 */
public class Note implements Serializable {
    private String content;
    private String time;
    private int _id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
