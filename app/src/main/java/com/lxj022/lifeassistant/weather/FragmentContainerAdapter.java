package com.lxj022.lifeassistant.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @des
 */
public class FragmentContainerAdapter extends FragmentPagerAdapter {

    public FragmentContainerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putParcelable("obj", null);
                break;
            case 1:
                bundle.putParcelable("obj", null);
                break;
            case 2:
                bundle.putParcelable("obj", null);
                break;
        }
        bundle.putInt("type", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
