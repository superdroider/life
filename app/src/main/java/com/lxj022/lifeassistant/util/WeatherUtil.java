package com.lxj022.lifeassistant.util;

import com.lxj022.lifeassistant.R;

/**
 * Created by liuxuejiao
 * 天气工具类
 */
public class WeatherUtil {

    /**
     * 根据AQI的值判断空气质量级别
     *
     * @param aqi
     * @return
     */
    public static String getAqiClass(int aqi) {
        if (aqi < 51) {
            return "优";
        } else if (aqi < 101) {
            return "良";
        } else if (aqi < 151) {
            return "轻度污染";
        } else if (aqi < 201) {
            return "中度污染";
        } else if (aqi < 301) {
            return "重度污染";
        } else {
            return "严重污染";
        }
    }

    /**
     * 通过反射获取图片资源id
     *
     * @param name
     * @return
     */
    public static int getImageResourceId(String name) {
        R.mipmap drawables = new R.mipmap();
        //默认的id
        int resId = R.mipmap.a_10;
        try {
            //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            java.lang.reflect.Field field = R.mipmap.class.getField(name);
            //取值
            resId = (Integer) field.get(drawables);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resId;
    }
}
