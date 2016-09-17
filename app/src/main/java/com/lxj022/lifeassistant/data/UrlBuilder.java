package com.lxj022.lifeassistant.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @des 负责构建请求链接
 */
public class UrlBuilder {
    private static final String WEATHER_APP_KEY = "10003";
    private static final String WEATHER_SIGN = "b59bc3ef6191eb9f747dd4e83c99f2a4";
    //    private static final String WEATHER_URL = "http://api.k780.com:88/?";
    private static final String WEATHER_URL = "http://tianqi.2345.com";

    /**
     * 获取天气城市列表URL
     *
     * @return
     */
    public String getWeaidUrl() {
        StringBuilder weaidUrl = new StringBuilder();
        weaidUrl.append(WEATHER_URL);
        Map<String, String> params = getBaseWeatherParams();
        params.put("app", "weather.city");
        for (String key : params.keySet()) {
            weaidUrl.append(key);
            weaidUrl.append("=");
            weaidUrl.append(params.get(key));
            weaidUrl.append("&");
        }
        return weaidUrl.substring(0, weaidUrl.length() - 1);
    }

    public String getWeatherDataUrl(String cityid) {
        StringBuilder weatherDataUrl = new StringBuilder();
        weatherDataUrl.append(WEATHER_URL);
        weatherDataUrl.append("/t/new_mobile_json/");
        weatherDataUrl.append(cityid);
        weatherDataUrl.append(".json");
        return weatherDataUrl.toString();
    }

    /**
     * 获取请求未来天气数据的url
     *
     * @return
     */
    public String getFutureWeatherDataUrl(String weaid) {
        StringBuilder featureUrl = new StringBuilder();
        featureUrl.append(WEATHER_URL);
        Map<String, String> params = getBaseWeatherParams();
        params.put("app", "weather.future");
        params.put("weaid", weaid);
        for (String key : params.keySet()) {
            featureUrl.append(key);
            featureUrl.append("=");
            featureUrl.append(params.get(key));
            featureUrl.append("&");
        }
        return featureUrl.substring(0, featureUrl.length() - 1);
    }

    /**
     * 获取天气请求基本参数
     *
     * @return
     */
    private Map<String, String> getBaseWeatherParams() {
        Map<String, String> params = new HashMap<>();
        params.put("appkey", WEATHER_APP_KEY);
        params.put("sign", WEATHER_SIGN);
        params.put("format", "json");
        return params;
    }
}
