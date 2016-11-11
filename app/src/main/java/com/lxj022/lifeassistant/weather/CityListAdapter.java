package com.lxj022.lifeassistant.weather;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.CityBean;

import java.util.List;

/**
 * Created by superdroid on 2016/10/16.
 */
public class CityListAdapter extends BaseAdapter {
    private List<CityBean> cityBeanList;
    private Context context;
    private int currentViewType;

    public CityListAdapter(List<CityBean> cityBeanList, Context context, int currentViewType) {
        this.cityBeanList = cityBeanList;
        this.context = context;
        this.currentViewType = currentViewType;
    }

    /**
     * 更新列表数据
     *
     * @param cityBeanList
     */
    public void updataData(List<CityBean> cityBeanList, int currentViewType) {
        this.cityBeanList = cityBeanList;
        this.currentViewType = currentViewType;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cityBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = null;
        if (view == null) {
            itemView = View.inflate(context, R.layout.item_city, null);
        } else {
            itemView = view;
        }
        TextView tv_city_name = (TextView) itemView.findViewById(R.id.tv_city_name);
        String cityName = cityBeanList.get(i).getCityName();
        if (currentViewType == CityListActivity.PROVINCE_DATA_VIEW) {
            tv_city_name.setText(cityBeanList.get(i).getProvinceName());
            tv_city_name.setGravity(Gravity.CENTER);
        } else if (currentViewType == CityListActivity.CITY_DATA_VIEW) {
            tv_city_name.setText(cityBeanList.get(i).getCityName());
            tv_city_name.setGravity(Gravity.CENTER);
        } else if (currentViewType == CityListActivity.SEARCH_LIST_VIEW) {
            tv_city_name.setText(cityName + "-" + cityBeanList.get(i).getProvinceName());
            tv_city_name.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        }
        return itemView;
    }
}
