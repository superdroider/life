package com.lxj022.lifeassistant.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.HourData;
import com.lxj022.lifeassistant.data.bean.LifeIndex;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.util.DateFormatUtil;
import com.lxj022.lifeassistant.util.WeatherUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @des 天气展示页面
 */
public class WeatherFragment extends Fragment {
    private static final String TAG = "WeatherFragment";
    private ListView lv_weather;
    private WeatherListAdapter mAdapter;
    private View headerView;
    private View contenView;
    private View footerView;
    private TextView tv_location;//城市
    private TextView tv_update_time;//更新时间
    private TextView tv_weather_desc;//天气描述
    private TextView tv_temperature;//今日温度
    private TextView tv_current_weather;//当前天气
    private TextView tv_wind;//风向风力
    private TextView tv_aqi;//空气指数
    private TextView tv_humi;//湿度
    private TextView tv_uv;//紫外线强度
    private TextView tv_rise_time;//日出时间
    private TextView tv_set_time;//日落时间
    private ImageView iv_weather_img;//天气图片
    //生活指数
    private TextView tv_tqzs;
    private TextView tv_cyzs;
    private ImageView iv_cyzs;
    private TextView tv_yszs;
    private ImageView iv_yszs;
    private TextView tv_clzs;
    private ImageView iv_clzs;
    private TextView tv_zwxzs;
    private ImageView iv_zwxzs;
    private TextView tv_gmzs;
    private ImageView iv_gmzs;
    private TextView tv_lszs;
    private ImageView iv_lszs;
    private TextView tv_xczs;
    private ImageView iv_xczs;
    private TextView tv_dyzs;
    private ImageView iv_dyzs;

    private WeatherBean weatherBean;
    private int type;
    List<HourData> todayHourDatas;
    List<HourData> tomorrowHourDatas;
    List<HourData> hourDatas;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (weatherBean != null)
            Log.e(TAG, "onDestroyView: " + weatherBean.getCityName());
        else
            Log.e(TAG, "onDestroyView: " + weatherBean);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contenView = inflater.inflate(R.layout.frag_weather, container, false);
        headerView = View.inflate(getActivity(), R.layout.header_weather, null);
        footerView = View.inflate(getActivity(), R.layout.footer_weather, null);
        bindView();
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        weatherBean = (WeatherBean) bundle.getSerializable("obj");
        tv_location.setText(weatherBean.getCityName());
        setDataToHeaderView(type);
        setDataToFooterView(type);
        lv_weather.addHeaderView(headerView);
        lv_weather.addFooterView(footerView);
        Log.e(TAG, "onCreateView: " + type + weatherBean.getCityName());
        initData();
        if (type == 0) {
            mAdapter = new WeatherListAdapter(todayHourDatas, getActivity());
        } else if (type == 1) {
            mAdapter = new WeatherListAdapter(tomorrowHourDatas, getActivity());
        } else if (type == 2) {
            List dayWeaDatas = weatherBean.getDays7();
            dayWeaDatas.addAll(weatherBean.getDays8());
            mAdapter = new WeatherListAdapter(dayWeaDatas, getActivity());
        }
        lv_weather.setAdapter(mAdapter);
        return contenView;
    }

    public void initData() {
        hourDatas = weatherBean.getHourDatas();
        todayHourDatas = new ArrayList<>();
        tomorrowHourDatas = new ArrayList<>();
        boolean isTodayHourData = false;
        for (HourData hourData : hourDatas) {
            String hour = hourData.getHour();
            if (hour.equals("00")) {
                isTodayHourData = !isTodayHourData;
            }
            if (isTodayHourData){
                todayHourDatas.add(hourData);
            }else{
                tomorrowHourDatas.add(hourData);
            }
        }
    }

    public void updateData(WeatherBean weatherBean) {
        Log.e(TAG, "updateData: " + weatherBean.getCityName());
        this.weatherBean = weatherBean;
        tv_location.setText(weatherBean.getCityName());
        initData();
        setDataToHeaderView(type);
        setDataToFooterView(type);
        if (type == 0) {
            mAdapter.updateData(todayHourDatas);
        } else if (type == 1) {
            mAdapter.updateData(tomorrowHourDatas);
        } else if (type == 2) {
            List dayWeaDatas = weatherBean.getDays7();
            dayWeaDatas.addAll(weatherBean.getDays8());
            mAdapter.updateData(dayWeaDatas);
        }
    }

    private void setDataToHeaderView(int type) {
        if (type == 2) {
            type = 0;
        }
        String wind = null;
        String img = null;
        tv_update_time.setText("最后更新：" + weatherBean.getUpdateTime());
        if (weatherBean.getDays7() != null && weatherBean.getDays7().size() > 0) {
            tv_temperature.setText(weatherBean.getDays7().get(type).getWholeTemp());
            tv_weather_desc.setText(weatherBean.getDays7().get(type).getWholeWea());
            wind = weatherBean.getDays7().get(type).getWholeWindDirection();
            img = weatherBean.getDays7().get(type).getDayImg();
        } else if (weatherBean.getDays8() != null && weatherBean.getDays8().size() > 0) {
            tv_temperature.setText(weatherBean.getDays8().get(type).getWholeTemp());
            tv_weather_desc.setText(weatherBean.getDays8().get(type).getWholeWea());
            wind = weatherBean.getDays8().get(type).getWholeWindDirection();
            img = weatherBean.getDays7().get(type).getDayImg();
        }
        if (wind == null) {
            wind = "";
        }

        if (type == 0) {
            tv_current_weather.setText("当前：" + weatherBean.getCurrentTemp() + "° " + weatherBean.getCurrentWea());
            tv_aqi.setText(weatherBean.getCurrentAqi());
            tv_humi.setText(weatherBean.getCurrentHumidity());
            tv_uv.setText(weatherBean.getTodayUv());
            tv_rise_time.setText(weatherBean.getTodayRise());
            tv_set_time.setText(weatherBean.getTodaySet());
            iv_weather_img.setImageResource(WeatherUtil.getImageResourceId("a_" + weatherBean.getCurrentWeaType()));
            tv_wind.setText(weatherBean.getCurrentWindDirection() + weatherBean.getCurrentWindPower());
        } else {
            tv_current_weather.setText("");
            tv_aqi.setText(weatherBean.getTomorrowAqi());
            tv_humi.setText(weatherBean.getTomorrowHumidity());
            tv_uv.setText(weatherBean.getTomorrowUv());
            tv_rise_time.setText(weatherBean.getTomorrowRise());
            tv_set_time.setText(weatherBean.getTomorrowSet());
            iv_weather_img.setImageResource(WeatherUtil.getImageResourceId("a_" + img));
            tv_wind.setText(wind);
        }
    }

    private void setDataToFooterView(int type) {
        List<LifeIndex> zs = null;
        if (type == 0 || type == 2) {
            tv_tqzs.setText("今日天气指数");
            zs = weatherBean.getZs();
        } else if (type == 1) {
            tv_tqzs.setText("明日天气指数");
            zs = weatherBean.getZs_tomorrow();
        }
        for (LifeIndex lifeIndex : zs) {
            if (lifeIndex.getName().equals("穿衣指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_cyzs.setImageResource(R.mipmap.live_yifu_no);
                    tv_cyzs.setText(lifeIndex.getLevel());
                }
            } else if (lifeIndex.getName().equals("雨伞指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_yszs.setImageResource(R.mipmap.live_san_no);
                } else {
                    iv_yszs.setImageResource(R.mipmap.live_san_yes);
                }
                tv_yszs.setText(lifeIndex.getLevel());
            } else if (lifeIndex.getName().equals("晨练指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_clzs.setImageResource(R.mipmap.live_chenlian_no);
                } else {
                    iv_clzs.setImageResource(R.mipmap.live_chenlian_yes);
                }
                tv_clzs.setText(lifeIndex.getLevel());
            } else if (lifeIndex.getName().equals("紫外线指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_zwxzs.setImageResource(R.mipmap.live_yanjing_no);
                } else {
                    iv_zwxzs.setImageResource(R.mipmap.live_yanjing_yes);
                }
                tv_zwxzs.setText(lifeIndex.getLevel());
            } else if (lifeIndex.getName().equals("感冒指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_gmzs.setImageResource(R.mipmap.live_yao_no);
                } else {
                    iv_gmzs.setImageResource(R.mipmap.live_yao_yes);
                }
                tv_gmzs.setText(lifeIndex.getLevel());
            } else if (lifeIndex.getName().equals("晾晒指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_lszs.setImageResource(R.mipmap.live_yifu_no);
                } else {
                    iv_lszs.setImageResource(R.mipmap.live_yifu_yes);
                }
                tv_lszs.setText(lifeIndex.getLevel());
            } else if (lifeIndex.getName().equals("洗车指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_xczs.setImageResource(R.mipmap.live_xiche_no);
                } else {
                    iv_xczs.setImageResource(R.mipmap.live_xiche_yes);
                }
                tv_xczs.setText(lifeIndex.getLevel());
            } else if (lifeIndex.getName().equals("钓鱼指数")) {
                if (lifeIndex.getLevelColor().equals("red")) {
                    iv_dyzs.setImageResource(R.mipmap.live_diaoyu_no);
                } else {
                    iv_dyzs.setImageResource(R.mipmap.live_diaoyu_yes);
                }
                tv_dyzs.setText(lifeIndex.getLevel());
            }
        }
    }


    /**
     * 绑定view
     */
    private void bindView() {
        lv_weather = (ListView) contenView.findViewById(R.id.lv_weather);

        tv_location = (TextView) headerView.findViewById(R.id.tv_location);
        tv_update_time = (TextView) headerView.findViewById(R.id.tv_update_time);
        tv_weather_desc = (TextView) headerView.findViewById(R.id.tv_weather_desc);
        tv_temperature = (TextView) headerView.findViewById(R.id.tv_temperature);
        tv_current_weather = (TextView) headerView.findViewById(R.id.tv_current_weather);
        tv_wind = (TextView) headerView.findViewById(R.id.tv_wind);
        tv_aqi = (TextView) headerView.findViewById(R.id.tv_aqi);
        tv_humi = (TextView) headerView.findViewById(R.id.tv_humi);
        tv_uv = (TextView) headerView.findViewById(R.id.tv_uv);
        tv_rise_time = (TextView) headerView.findViewById(R.id.tv_rise_time);
        tv_set_time = (TextView) headerView.findViewById(R.id.tv_set_time);
        iv_weather_img = (ImageView) headerView.findViewById(R.id.iv_weather_img);


        tv_tqzs = (TextView) footerView.findViewById(R.id.tv_tqzs);
//穿衣指数
        tv_cyzs = (TextView) footerView.findViewById(R.id.tv_tqzs);
        iv_cyzs = (ImageView) footerView.findViewById(R.id.iv_cyzs);
//雨伞指数
        tv_yszs = (TextView) footerView.findViewById(R.id.tv_yszs);
        iv_yszs = (ImageView) footerView.findViewById(R.id.iv_yszs);
//晨练指数
        tv_clzs = (TextView) footerView.findViewById(R.id.tv_clzs);
        iv_clzs = (ImageView) footerView.findViewById(R.id.iv_clzs);
//紫外线指数
        tv_zwxzs = (TextView) footerView.findViewById(R.id.tv_zwxzs);
        iv_zwxzs = (ImageView) footerView.findViewById(R.id.iv_zwxzs);
//感冒指数
        tv_gmzs = (TextView) footerView.findViewById(R.id.tv_gmzs);
        iv_gmzs = (ImageView) footerView.findViewById(R.id.iv_gmzs);
//晾晒指数
        tv_lszs = (TextView) footerView.findViewById(R.id.tv_lszs);
        iv_lszs = (ImageView) footerView.findViewById(R.id.iv_lszs);
//洗车指数
        tv_xczs = (TextView) footerView.findViewById(R.id.tv_xczs);
        iv_xczs = (ImageView) footerView.findViewById(R.id.iv_xczs);
//钓鱼指数
        tv_dyzs = (TextView) footerView.findViewById(R.id.tv_dyzs);
        iv_dyzs = (ImageView) footerView.findViewById(R.id.iv_dyzs);
    }


}
