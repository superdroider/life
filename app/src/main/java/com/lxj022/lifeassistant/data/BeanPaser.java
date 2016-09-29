package com.lxj022.lifeassistant.data;

import android.text.TextUtils;

import com.lxj022.lifeassistant.data.bean.CityBean;
import com.lxj022.lifeassistant.data.bean.DayAqi;
import com.lxj022.lifeassistant.data.bean.DayWeather;
import com.lxj022.lifeassistant.data.bean.HourData;
import com.lxj022.lifeassistant.data.bean.LifeIndex;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.util.WeatherUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @des 实体解析类
 */
public class BeanPaser {

    /**
     * 解析天气城市对象
     *
     * @param jsonStr
     * @return
     */
    public List<CityBean> parseWeathercity(String jsonStr) {
        List<CityBean> cityList = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String success = jsonObject.getString("success");
            if (success.equals("1")) {
                cityList = new ArrayList<>();
                JSONObject result = jsonObject.getJSONObject("result");
                JSONObject object = null;
                String weaid;
                String citynm;
                String cityno;
                String cityid;
                CityBean cityBean;
                //通过获取的json数据分析得到 最多有2645条记录
                for (int i = 1; i <= 2645; i++) {
                    try {
                        object = result.getJSONObject("" + i);
                    } catch (JSONException e) {
                        //跳过不存在的数字
                        continue;
                    }
                    weaid = object.getString("weaid");
                    citynm = object.getString("citynm");
                    cityno = object.getString("cityno");
                    cityid = object.getString("cityid");
                    cityBean = new CityBean(weaid, citynm, cityno, cityid);
                    cityList.add(cityBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityList;
    }


    /**
     * 解析天气数据
     *
     * @param result
     */
    public WeatherBean parseWeatherData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            WeatherBean weatherBean = new WeatherBean();
            if (!jsonObject.isNull("cityId"))
                weatherBean.setCityId(jsonObject.getString("cityId"));
            if (!jsonObject.isNull("cityName"))
                weatherBean.setCityName(jsonObject.getString("cityName"));
            //            未来七天天气
            if (!jsonObject.isNull("days7")) {
                JSONArray days7Arr = jsonObject.getJSONArray("days7");
                DayWeather days7 = null;
                List<DayWeather> days7List = new ArrayList<>();
                for (int i = 0; i < days7Arr.length(); i++) {
                    JSONObject days7JsonObj = days7Arr.getJSONObject(i);
                    days7 = new DayWeather();
                    if (!days7JsonObj.isNull("time"))
                        days7.setTime(days7JsonObj.getLong("time"));
                    if (!days7JsonObj.isNull("wholeTemp"))
                        days7.setWholeTemp(days7JsonObj.getString("wholeTemp") + "°");
                    if (!days7JsonObj.isNull("wholeWea"))
                        days7.setWholeWea(days7JsonObj.getString("wholeWea"));
                    if (!days7JsonObj.isNull("wholeWindDirection"))
                        days7.setWholeWindDirection(days7JsonObj.getString("wholeWindDirection"));
                    if (!days7JsonObj.isNull("dayImg"))
                        days7.setDayImg(days7JsonObj.getString("dayImg"));
                    if (!days7JsonObj.isNull("nightImg"))
                        days7.setNightImg(days7JsonObj.getString("nightImg"));
                    days7List.add(days7);
                }
                weatherBean.setDays7(days7List);
            }
            //            未来8天天气
            if (!jsonObject.isNull("days8")) {
                JSONArray days8Arr = jsonObject.getJSONArray("days8");
                DayWeather days8 = null;
                List<DayWeather> days7List = new ArrayList<>();
                String dayTemp = null, nightTemp = null, dayWea = null, nightWea = null;

                for (int i = 0; i < days8Arr.length(); i++) {
                    JSONObject days8JsonObj = days8Arr.getJSONObject(i);
                    days8 = new DayWeather();
                    if (!days8JsonObj.isNull("time"))
                        days8.setTime(days8JsonObj.getLong("time"));
                    if (!days8JsonObj.isNull("dayTemp"))
                        dayTemp = days8JsonObj.getString("dayTemp");
                    if (!days8JsonObj.isNull("nightTemp"))
                        nightTemp = days8JsonObj.getString("nightTemp");
                    if (!days8JsonObj.isNull("dayWea"))
                        dayWea = days8JsonObj.getString("dayWea");
                    if (!days8JsonObj.isNull("nightWea"))
                        nightWea = days8JsonObj.getString("nightWea");
                    if (!days8JsonObj.isNull("nightImg"))
                        days8.setNightImg(days8JsonObj.getString("nightImg"));
                    if (!days8JsonObj.isNull("dayImg"))
                        days8.setDayImg(days8JsonObj.getString("dayImg"));
                    if (!TextUtils.isEmpty(dayTemp) && !TextUtils.isEmpty(nightTemp)) {
                        days8.setWholeTemp(nightTemp + "~" + dayTemp + "°");
                    }
                    if (!TextUtils.isEmpty(dayWea) && !TextUtils.isEmpty(nightWea)) {
                        if (dayWea.equals(nightWea)) {
                            days8.setWholeWea(dayWea);
                        } else {
                            days8.setWholeWea(dayWea + "转" + nightWea);
                        }
                    }
                    days7List.add(days8);
                }
                weatherBean.setDays7(days7List);
            }
            //                今日生活指数
            if (!jsonObject.isNull("zs")) {
                JSONArray zsArr = jsonObject.getJSONArray("zs");
                LifeIndex lifeIndex = null;
                List<LifeIndex> lifeIndices = new ArrayList<>();
                for (int i = 0; i < zsArr.length(); i++) {
                    JSONObject zsJsonObj = zsArr.getJSONObject(i);
                    lifeIndex = new LifeIndex();
                    if (!zsJsonObj.isNull("levelColor"))
                        lifeIndex.setLevelColor(zsJsonObj.getString("levelColor"));
                    if (!zsJsonObj.isNull("level"))
                        lifeIndex.setLevel(zsJsonObj.getString("level"));
                    if (!zsJsonObj.isNull("detail"))
                        lifeIndex.setDetail(zsJsonObj.getString("detail"));
                    if (!zsJsonObj.isNull("name"))
                        lifeIndex.setName(zsJsonObj.getString("name"));
                    if (!zsJsonObj.isNull("text"))
                        lifeIndex.setText(zsJsonObj.getString("text"));
                    lifeIndices.add(lifeIndex);
                }
                weatherBean.setZs(lifeIndices);
            }

            //                明日生活指数
            if (!jsonObject.isNull("zs_tomorrow")) {
                JSONArray zsArr = jsonObject.getJSONArray("zs_tomorrow");
                LifeIndex lifeIndex = null;
                List<LifeIndex> lifeIndices = new ArrayList<>();
                for (int i = 0; i < zsArr.length(); i++) {
                    JSONObject zsJsonObj = zsArr.getJSONObject(i);
                    lifeIndex = new LifeIndex();
                    if (!zsJsonObj.isNull("levelColor"))
                        lifeIndex.setLevelColor(zsJsonObj.getString("levelColor"));
                    if (!zsJsonObj.isNull("level"))
                        lifeIndex.setLevel(zsJsonObj.getString("level"));
                    if (!zsJsonObj.isNull("detail"))
                        lifeIndex.setDetail(zsJsonObj.getString("detail"));
                    if (!zsJsonObj.isNull("name"))
                        lifeIndex.setName(zsJsonObj.getString("name"));
                    if (!zsJsonObj.isNull("text"))
                        lifeIndex.setText(zsJsonObj.getString("text"));
                    lifeIndices.add(lifeIndex);
                }
                weatherBean.setZs_tomorrow(lifeIndices);
            }

            if (!jsonObject.isNull("aqi")) {
                JSONObject aqiObj = jsonObject.getJSONObject("aqi");
                if (!aqiObj.isNull("AQI")) {
                    weatherBean.setCurrentAqi("空气质量:" + aqiObj.getInt("AQI") + "|" + WeatherUtil.getAqiClass(aqiObj.getInt("AQI")));
                }
            }
            //            未来空气质量
            if (!jsonObject.isNull("aqi_5days")) {
                JSONArray aqi_5daysArr = jsonObject.getJSONArray("aqi_5days");
                DayAqi dayAqi = null;
                List<DayAqi> dayAqis = new ArrayList<>();
                for (int i = 0; i < aqi_5daysArr.length(); i++) {
                    JSONObject aqiJsonObj = aqi_5daysArr.getJSONObject(i);
                    dayAqi = new DayAqi();
                    if (!aqiJsonObj.isNull("aqi"))
                        dayAqi.setAqi(aqiJsonObj.getString("aqi"));
                    if (!aqiJsonObj.isNull("date"))
                        dayAqi.setDate(aqiJsonObj.getString("date"));
                    dayAqis.add(dayAqi);
                }
                weatherBean.setDayAqiList(dayAqis);
            }
            /**
             * 当前或今日天气数据
             */
            if (!jsonObject.isNull("sk")) {
                JSONObject skObj = jsonObject.getJSONObject("sk");
                if (!skObj.isNull("sk_time"))
                    weatherBean.setUpdateTime(skObj.getString("sk_time"));
                if (!skObj.isNull("sk_temp"))
                    weatherBean.setCurrentTemp(skObj.getString("sk_temp"));
                if (!skObj.isNull("humidity"))
                    weatherBean.setCurrentHumidity(skObj.getString("humidity"));
                if (!skObj.isNull("windDirection"))
                    weatherBean.setCurrentWindDirection(skObj.getString("windDirection"));
                if (!skObj.isNull("windPower"))
                    weatherBean.setCurrentWindPower(skObj.getString("windPower"));
                if (!skObj.isNull("uvDesc")) {
                    JSONObject uvObj = skObj.getJSONObject("uvDesc");
                    if (!uvObj.isNull("today"))
                        weatherBean.setTodayUv(uvObj.getString("today"));
                    if (!uvObj.isNull("tomorrow"))
                        weatherBean.setTomorrowUv(uvObj.getString("tomorrow"));
                }
            }
            if (!jsonObject.isNull("sk_weather")) {
                JSONObject sk_weatherObj = jsonObject.getJSONObject("sk_weather");
                if (!sk_weatherObj.isNull("condition"))
                    weatherBean.setCurrentWea(sk_weatherObj.getString("condition"));
                if (!sk_weatherObj.isNull("type"))
                    weatherBean.setCurrentWeaType(sk_weatherObj.getString("type"));
            }
            /**
             * 日出或日落时间
             */
            if (!jsonObject.isNull("sunrise")) {
                JSONObject sunriseObj = jsonObject.getJSONObject("sunrise");
                if (!sunriseObj.isNull("todayRise"))
                    weatherBean.setTodayRise(sunriseObj.getString("todayRise"));
                if (!sunriseObj.isNull("todaySet"))
                    weatherBean.setTodaySet(sunriseObj.getString("todaySet"));
                if (!sunriseObj.isNull("tomorrowRise"))
                    weatherBean.setTomorrowRise(sunriseObj.getString("tomorrowRise"));
                if (!sunriseObj.isNull("tomorrowSet"))
                    weatherBean.setTomorrowSet(sunriseObj.getString("tomorrowSet"));
            }
            if (!jsonObject.isNull("hourData")) {
                JSONArray hourDataArr = jsonObject.getJSONArray("hourData");
                List<HourData> hourDatas = new ArrayList<>();
                HourData hourData = null;
                JSONObject hourDataObj;
                for (int i = 0; i < hourDataArr.length(); i++) {
                    hourDataObj = hourDataArr.getJSONObject(i);
                    hourData = new HourData();
                    if (!hourDataObj.isNull("aqi")) {
                        hourData.setAqi(hourDataObj.getInt("aqi"));
                    }
                    if (!hourDataObj.isNull("day")) {
                        hourData.setDay(hourDataObj.getString("day"));
                    }
                    if (!hourDataObj.isNull("hour")) {
                        hourData.setHour(hourDataObj.getString("hour"));
                    }
                    if (!hourDataObj.isNull("icon")) {
                        hourData.setIcon(hourDataObj.getString("icon"));
                    }
                    if (!hourDataObj.isNull("temp")) {
                        hourData.setTemp(hourDataObj.getString("temp"));
                    }
                    if (!hourDataObj.isNull("tq")) {
                        hourData.setTq(hourDataObj.getString("tq"));
                    }
                    hourDatas.add(hourData);
                }
                weatherBean.setHourDatas(hourDatas);
                return weatherBean;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
