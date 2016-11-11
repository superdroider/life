package com.lxj022.lifeassistant.util;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.lxj022.lifeassistant.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by liuxuejiao
 * 天气工具类
 */
public class WeatherUtil {

    /**
     * 根据AQI的值判断空气质量级别
     *
     * @param aqi
     * @return 空气质量文字描述
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
     * 根据AQI的值判断空气质量级别
     *
     * @param aqi
     * @return 空气质量颜色描述
     */
    public static int getAqiColor(int aqi) {
        if (aqi < 51) {
            return Color.parseColor("#6ACD06");
        } else if (aqi < 101) {
            return Color.parseColor("#FACF29");
        } else if (aqi < 151) {
            return Color.parseColor("#FEA73F");
        } else if (aqi < 201) {
            return Color.parseColor("#E95B11");
        } else if (aqi < 301) {
            return Color.parseColor("#940351");
        } else {
            return Color.parseColor("#5F001C");
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

    public static void copyWeatherDatabase(final Context context) {
        new Thread() {
            @Override
            public void run() {
                File dir = new File("/data/data/com.lxj022.lifeassistant/databases");
                if (!dir.exists()) {
                    try {
                        dir.mkdir();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                File dest = new File(dir, "weather.db");
                if (dest.exists()) {
                    Log.e("tag", "copyWeatherDatabase: db exists");
                    return;
                }

                try {
                    dest.createNewFile();
                    InputStream in = context.getResources().openRawResource(R.raw.weather);
                    int size = in.available();
                    byte buf[] = new byte[size];
                    in.read(buf);
                    in.close();
                    FileOutputStream out = new FileOutputStream(dest);
                    out.write(buf);
                    out.close();
                    Log.e("tag", "copyWeatherDatabase: ok");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}
