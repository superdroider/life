package com.lxj022.lifeassistant.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lxj022.lifeassistant.R;

import java.util.List;

/**
 * @des 天气展示页面
 */
public class WeatherFragment extends Fragment {
    private ListView lv_weather;
    private WeatherListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_weather, container);
        lv_weather = (ListView) view.findViewById(R.id.lv_weather);
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        if (type == 0) {

        } else if (type == 1) {

        } else if (type == 2) {

        }
        mAdapter = new WeatherListAdapter(null, getActivity());
        lv_weather.setAdapter(mAdapter);
        return view;
    }
}
