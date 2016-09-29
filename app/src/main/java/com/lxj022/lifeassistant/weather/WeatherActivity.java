package com.lxj022.lifeassistant.weather;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.data.local.db.DbOperate;
import com.lxj022.lifeassistant.data.remote.WeatherDataSource;

import java.util.List;

/**
 * @des 天气预报主页面
 */
public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton ib_weather_search;
    private ImageButton ib_back;
    private ViewPager vp_frag_container;
    private TextView tv_today;
    private TextView tv_tomorrow;
    private TextView tv_future;
    private FragmentContainerAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private LoadDataTask mTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ib_weather_search = (ImageButton) findViewById(R.id.ib_weather_search);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        tv_today = (TextView) findViewById(R.id.tv_today);
        tv_tomorrow = (TextView) findViewById(R.id.tv_tomorrow);
        tv_future = (TextView) findViewById(R.id.tv_future);
        vp_frag_container = (ViewPager) findViewById(R.id.vp_frag_container);
        ib_weather_search.setOnClickListener(this);
        ib_back.setOnClickListener(this);
        tv_today.setOnClickListener(this);
        tv_tomorrow.setOnClickListener(this);
        tv_future.setOnClickListener(this);
        mTask = new LoadDataTask();
        getFutureWeatherData();
        tv_today.setSelected(true);
    }

    private void getFutureWeatherData() {
        mTask.execute("58457");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_weather:

                break;
            case R.id.ib_back:
                if (mTask.getStatus() != AsyncTask.Status.FINISHED) {
                    mTask.cancel(true);
                }
                finish();
                break;
            case R.id.tv_today:
                resetSelectStatu();
                tv_today.setSelected(!tv_today.isSelected());
                break;
            case R.id.tv_tomorrow:
                resetSelectStatu();
                tv_tomorrow.setSelected(!tv_tomorrow.isSelected());
                break;
            case R.id.tv_future:
                resetSelectStatu();
                tv_future.setSelected(!tv_future.isSelected());
                break;
        }
    }

    private void resetSelectStatu() {
        tv_today.setSelected(false);
        tv_tomorrow.setSelected(false);
        tv_future.setSelected(false);
    }

    private void renderDataToView(WeatherBean weatherBean) {
        mAdapter = new FragmentContainerAdapter(getSupportFragmentManager(), weatherBean);
        vp_frag_container.setAdapter(mAdapter);
    }

    class LoadDataTask extends AsyncTask<String, Void, WeatherBean> {

        @Override
        protected WeatherBean doInBackground(String... strings) {
            return new WeatherDataSource().getWeatherData(strings[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(WeatherActivity.this);
            mProgressDialog.setMessage("数据正在努力加载……");
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(WeatherBean weatherBean) {
            mProgressDialog.dismiss();
            if (weatherBean == null) {
                Toast.makeText(WeatherActivity.this, "数据获取失败", Toast.LENGTH_SHORT).show();
            } else {
//                数据获取成功
                mAdapter = new FragmentContainerAdapter(getSupportFragmentManager(), weatherBean);
                vp_frag_container.setAdapter(mAdapter);

            }
        }
    }


}
