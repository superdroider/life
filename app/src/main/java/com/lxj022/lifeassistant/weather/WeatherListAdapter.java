package com.lxj022.lifeassistant.weather;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.view.SmartImageView;

import java.util.List;

/**
 * @des 未来七天天气列表适配器
 */
public class WeatherListAdapter extends BaseAdapter {

    private List<WeatherBean> mWeatherList;
    private Context mContext;

    public WeatherListAdapter(List<WeatherBean> weatherList, Context context) {
        mWeatherList = weatherList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mWeatherList.size();
    }

    @Override
    public Object getItem(int i) {
        return mWeatherList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_weather, null);
            holder = new ViewHolder();
            holder.tv_temperature = (TextView) view.findViewById(R.id.tv_temperature);
            holder.tv_weather_date = (TextView) view.findViewById(R.id.tv_weather_date);
            holder.tv_weather = (TextView) view.findViewById(R.id.tv_weather);
            holder.tv_winp = (TextView) view.findViewById(R.id.tv_winp);
            holder.tv_wind = (TextView) view.findViewById(R.id.tv_wind);
            holder.iv_weather = (SmartImageView) view.findViewById(R.id.iv_weather);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        WeatherBean weatherBean = mWeatherList.get(i);
//        holder.iv_weather.setNetImage(weatherBean.getWeather_icon());
//        holder.tv_temperature.setText(weatherBean.getTemperature());
//        holder.tv_weather.setText(weatherBean.getWeather());
//        holder.tv_weather_date.setText(weatherBean.getDays().substring(5) + "  " + weatherBean.getWeek());
//        holder.tv_wind.setText(weatherBean.getWind());
//        holder.tv_winp.setText(weatherBean.getWinp());
        return view;
    }

    static class ViewHolder {
        TextView tv_weather_date;
        TextView tv_weather;
        TextView tv_temperature;
        TextView tv_winp;
        TextView tv_wind;
        SmartImageView iv_weather;
    }
}
