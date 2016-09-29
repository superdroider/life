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
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("tag", "getItem: "+position );
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
}
