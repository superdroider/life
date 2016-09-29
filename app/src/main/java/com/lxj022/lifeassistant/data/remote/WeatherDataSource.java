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

    /**
     * 联网获取天气数据
     *
     * @param cityId 城市id
     * @return
     */
    public WeatherBean getWeatherData(String cityId) {
        WeatherBean weatherBean = null;
//        获取请求地址
        String futureWeatherDataUrl = new UrlBuilder().getWeatherDataUrl(cityId);
        if (!TextUtils.isEmpty(futureWeatherDataUrl)) {
            String response = HttpRequest.doGet(futureWeatherDataUrl);
            if (!TextUtils.isEmpty(response)) {
                String result = AES.decrypt(response);
                //请求结果不为空则进行解析
                if (!TextUtils.isEmpty(result)) {
                    weatherBean = new BeanPaser().parseWeatherData(result);
                }
            }
        }
        return weatherBean;
    }
}
