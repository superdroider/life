package com.lxj022.lifeassistant.data.remote;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lxj022.lifeassistant.data.AES;
import com.lxj022.lifeassistant.data.BeanPaser;
import com.lxj022.lifeassistant.data.HttpRequest;
import com.lxj022.lifeassistant.data.UrlBuilder;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.data.local.db.DbOperate;

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
    public WeatherBean getWeatherData(Context context, String cityId) {
        WeatherBean weatherBean = null;
        String result = null;
        DbOperate dbOperate = new DbOperate(context);
//        获取请求地址
        String futureWeatherDataUrl = new UrlBuilder().getWeatherDataUrl(cityId);

        if (TextUtils.isEmpty(futureWeatherDataUrl)) {
            return null;
        }
        String response = HttpRequest.doGet(futureWeatherDataUrl);
        if (TextUtils.isEmpty(response)) {
            result = dbOperate.readWeatherData(cityId);
        } else {
            result = AES.decrypt(response);
            if (TextUtils.isEmpty(result)) {
                result = dbOperate.readWeatherData(cityId);
            } else {
                new DbOperate(context).saveWeatherData(cityId, result);
            }
        }
        weatherBean = new BeanPaser().parseWeatherData(result);
        return weatherBean;
    }
}
