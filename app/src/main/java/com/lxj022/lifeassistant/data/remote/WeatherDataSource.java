package com.lxj022.lifeassistant.data.remote;

import android.text.TextUtils;
import android.util.Log;

import com.lxj022.lifeassistant.data.AES;
import com.lxj022.lifeassistant.data.BeanPaser;
import com.lxj022.lifeassistant.data.HttpRequest;
import com.lxj022.lifeassistant.data.UrlBuilder;
import com.lxj022.lifeassistant.data.bean.WeatherBean;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @des 天气数据源类，用来联网获取天气数据
 */
public class WeatherDataSource {

    public WeatherBean getFeatureWeatherData(String cityId) {
        String futureWeatherDataUrl = new UrlBuilder().getWeatherDataUrl(cityId);
        if (!TextUtils.isEmpty(futureWeatherDataUrl)) {
            String result = AES.decrypt(HttpRequest.doGet(futureWeatherDataUrl));
            try {
                AES.WriteStringToFile("/mnt/sdcard/decrypt",new String(result.getBytes(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.e("tag", "getFeatureWeatherData: " + result);
            //请求结果不为空则进行解析
            if (!TextUtils.isEmpty(result)) {
                new BeanPaser().parseWeatherData(result);
            }
        }
        return null;
    }
}
