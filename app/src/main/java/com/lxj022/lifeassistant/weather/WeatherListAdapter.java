package com.lxj022.lifeassistant.weather;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.DayAqi;
import com.lxj022.lifeassistant.data.bean.DayWeather;
import com.lxj022.lifeassistant.data.bean.HourData;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.util.DateFormatUtil;
import com.lxj022.lifeassistant.util.WeatherUtil;
import com.lxj022.lifeassistant.view.SmartImageView;

import java.util.List;

/**
 * @des 未来七天天气列表适配器
 */
public class WeatherListAdapter extends BaseAdapter {

    private static final String TAG = "WeatherListAdapter";
    private List<?> mDataList;
    private Context mContext;

    public WeatherListAdapter(List<?> mDataList, Context context) {
        this.mDataList = mDataList;
        mContext = context;
    }

    public void updateData(List<?> mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Math.min(mDataList.size(), 24);
    }

    @Override
    public Object getItem(int i) {
        return mDataList.get(i);
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
            holder.tv_aqi = (TextView) view.findViewById(R.id.tv_aqi);
            holder.iv_weather = (ImageView) view.findViewById(R.id.iv_weather);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Object object = mDataList.get(i);
        if (object instanceof HourData) {
            HourData hourData = (HourData) object;
            holder.tv_temperature.setText(hourData.getTemp() + "°");
            holder.tv_weather.setText(hourData.getTq());
            holder.tv_weather.setMinWidth(0);
            holder.tv_weather_date.setText(hourData.getHour() + ":00");
            holder.iv_weather.setImageResource(WeatherUtil.getImageResourceId("c_" + hourData.getIcon()));
            String aqiDesc = WeatherUtil.getAqiClass(hourData.getAqi());
            if (aqiDesc.length() == 1) {
                aqiDesc = "空气" + aqiDesc;
            }
            holder.tv_aqi.setVisibility(View.VISIBLE);
            holder.tv_aqi.setText(aqiDesc);
            holder.tv_aqi.setTextColor(WeatherUtil.getAqiColor(hourData.getAqi()));
        } else if (object instanceof DayWeather) {
            DayWeather dayWeather = (DayWeather) object;
            holder.tv_temperature.setText(dayWeather.getWholeTemp());
            holder.tv_weather.setText(dayWeather.getWholeWea());
            holder.tv_weather_date.setText(DateFormatUtil.getWeekAndDate(dayWeather.getTime() * 1000));
            holder.iv_weather.setImageResource(WeatherUtil.getImageResourceId("c_" + dayWeather.getDayImg()));
            holder.tv_aqi.setVisibility(View.GONE);

        }
        return view;
    }

    static class ViewHolder {
        TextView tv_weather_date;
        TextView tv_weather;
        TextView tv_temperature;
        TextView tv_aqi;
        ImageView iv_weather;
    }
}
