package com.lxj022.lifeassistant.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;

import com.lxj022.lifeassistant.data.bean.WeatherBean;

/**
 * @des
 */
public class FragmentContainerAdapter extends FragmentPagerAdapter {

    private WeatherBean weatherBean;

    public FragmentContainerAdapter(FragmentManager fm, WeatherBean weatherBean) {
        super(fm);
        Log.e("tag", "FragmentContainerAdapter: ");
        this.weatherBean = weatherBean;
        Log.e("ContainerAdapter", "FragmentContainerAdapter: " + weatherBean.getCityName());
    }

    @Override
    public int getItemPosition(Object object) {
        Log.e("tag", "getItemPosition: ");
//        切换城市后更新数据
        WeatherFragment frag = (WeatherFragment) object;
        frag.updateData(weatherBean);
        frag.getArguments().putSerializable("obj", weatherBean);
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("tag", "getItem: " + position + "--" + weatherBean.getCityName());
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", position);
        bundle.putSerializable("obj", weatherBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void updateData(WeatherBean weatherBean) {
        this.weatherBean = weatherBean;
        notifyDataSetChanged();
    }
}
