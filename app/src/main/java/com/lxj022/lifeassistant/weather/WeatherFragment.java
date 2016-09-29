package com.lxj022.lifeassistant.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.HourData;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.util.WeatherUtil;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contenView = inflater.inflate(R.layout.frag_weather, container, false);
        headerView = View.inflate(getActivity(), R.layout.header_weather, null);
        bindView();
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        WeatherBean weatherBean = (WeatherBean) bundle.getSerializable("obj");
        if (type == 0) {

        } else if (type == 1) {

        } else if (type == 2) {

        }
        tv_update_time.setText("最后更新：" + weatherBean.getUpdateTime());

        if (weatherBean.getDays7() != null && weatherBean.getDays7().size() > 0) {
            tv_temperature.setText(weatherBean.getDays7().get(0).getWholeTemp());
            tv_weather_desc.setText(weatherBean.getDays7().get(0).getWholeWea());
        } else if (weatherBean.getDays8() != null && weatherBean.getDays8().size() > 0) {
            tv_temperature.setText(weatherBean.getDays8().get(0).getWholeTemp());
            tv_weather_desc.setText(weatherBean.getDays8().get(0).getWholeWea());
        }
        tv_current_weather.setText("当前：" + weatherBean.getCurrentTemp() + "° " + weatherBean.getCurrentWea());
        tv_wind.setText(weatherBean.getCurrentWindDirection() + weatherBean.getCurrentWindPower());
        tv_aqi.setText(weatherBean.getCurrentAqi());
        tv_humi.setText(weatherBean.getCurrentHumidity());
        tv_uv.setText(weatherBean.getTodayUv());
        tv_rise_time.setText(weatherBean.getTodayRise());
        tv_set_time.setText(weatherBean.getTodaySet());
        iv_weather_img.setImageResource(WeatherUtil.getImageResourceId("a_" + weatherBean.getCurrentWeaType()));

        lv_weather.addHeaderView(headerView);
        Log.e(TAG, "onCreateView: ");
        mAdapter = new WeatherListAdapter(weatherBean.getHourDatas(), getActivity());
        lv_weather.setAdapter(mAdapter);

        return contenView;
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
    }
}
