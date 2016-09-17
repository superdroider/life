package com.lxj022.lifeassistant.data;

import android.util.Log;

import com.lxj022.lifeassistant.data.bean.CityBean;
import com.lxj022.lifeassistant.data.bean.HourData;
import com.lxj022.lifeassistant.data.bean.LifeIndex;
import com.lxj022.lifeassistant.data.bean.TodayWeather;
import com.lxj022.lifeassistant.data.bean.WeatherBean;

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
    public void parseWeatherData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            //            解析今日天气数据
            JSONObject sk = jsonObject.getJSONObject("sk");
            TodayWeather todayWeather = new TodayWeather();
            todayWeather.setTemp(sk.getString("sk_temp"));//当前温度
            todayWeather.setTime(sk.getString("sk_time"));//更新时间
            todayWeather.setWindDirection(sk.getString("windDirection"));//风向
            todayWeather.setWindPower(sk.getString("windPower")); //风力
            todayWeather.setSk_ATM(sk.getString("sk_ATM"));  //气压
            todayWeather.setHumidity(sk.getString("humidity")); //湿度
            todayWeather.setCondition(sk.getString("condition")); //天气状况
            todayWeather.setType(sk.getString("type"));//天气类型
            todayWeather.setTips(sk.getString("tips"));//天气提示
            JSONObject uvDesc = sk.getJSONObject("uvDesc");
            todayWeather.setUvDesc(uvDesc.getString("today"));//紫外线强度
            JSONArray days7 = jsonObject.getJSONArray("days7");
            if (days7 != null && days7.length() > 0)
                todayWeather.setWholeTemp(days7.getJSONObject(0).getString("wholeTemp"));
            JSONArray hourDataArray = jsonObject.getJSONArray("hourData");
            List<HourData> hourDatas = new ArrayList<>();
            HourData hourData = null;
            for (int i = 0; i < hourDataArray.length(); i++) {
                JSONObject object = hourDataArray.getJSONObject(i);
                int hour = Integer.parseInt(object.getString("hour"));
                int currentHour = Integer.parseInt(DateFormatUtil.getCurrentHour());
                //                如果当前时间大于或等于解析得到的时间则跳过这条数据
                if (hour < currentHour) {
                    continue;
                }
                //                返回的数据是48小时的 我们只需要24小时就可以了
                if (hourDatas.size() == 24) {
                    break;
                }
                hourData = new HourData();
                hourData.setAqi(object.getInt("aqi"));
                hourData.setDay(DateFormatUtil.longToString(object.getLong("day")));
                hourData.setHour(DateFormatUtil.getCurrentHour());
                hourData.setIcon(object.getString("icon"));
                hourData.setTemp(object.getString("temp"));
                hourData.setTq(object.getString("tq"));
                hourDatas.add(hourData);
            }
            todayWeather.setHourDatas(hourDatas);

            JSONArray zs = jsonObject.getJSONArray("zs");
            List<LifeIndex> lifeIndices = new ArrayList<>();
            LifeIndex lifeIndex = null;
            for (int i = 0; i < zs.length(); i++) {
                JSONObject object = zs.getJSONObject(i);
                lifeIndex = new LifeIndex();
                lifeIndex.setDetail(object.getString("detail"));
                lifeIndex.setLevel(object.getString("level"));
                lifeIndex.setLevelColor(object.getString("levelColor"));
                lifeIndex.setName(object.getString("name"));
                lifeIndex.setText(object.getString("text"));
                lifeIndices.add(lifeIndex);
            }
            JSONArray zsOther = jsonObject.getJSONArray("zs_other");
            for (int i = 0; i < zsOther.length(); i++) {
                JSONObject object = zsOther.getJSONObject(i);
                lifeIndex = new LifeIndex();
                lifeIndex.setDetail(object.getString("detail"));
                lifeIndex.setLevel(object.getString("level"));
                lifeIndex.setLevelColor(object.getString("levelColor"));
                lifeIndex.setName(object.getString("name"));
                lifeIndex.setText(object.getString("text"));
                lifeIndices.add(lifeIndex);
            }
            todayWeather.setLifeIndices(lifeIndices);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
