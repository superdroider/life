package com.lxj022.lifeassistant.weather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.CityBean;
import com.lxj022.lifeassistant.data.bean.WeatherBean;
import com.lxj022.lifeassistant.data.local.db.DbOperate;
import com.lxj022.lifeassistant.data.remote.WeatherDataSource;
import com.lxj022.lifeassistant.util.SPUtil;

import java.util.List;

/**
 * @des 天气预报主页面
 */
public class WeatherActivity extends AppCompatActivity implements View.OnClickListener, AMapLocationListener {
    private static final int REQUEST_CODE = 1;
    private ImageButton ib_weather_search;
    private ImageButton ib_back;
    private ViewPager vp_frag_container;
    private TextView tv_today;
    private TextView tv_tomorrow;
    private TextView tv_future;
    private FragmentContainerAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private LoadDataTask mTask;

    //声明AMapLocationClient类对象
    public AMapLocationClient locationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;

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

        tv_today.setSelected(true);
        initLocation();
        startLocation();
        mProgressDialog = new ProgressDialog(WeatherActivity.this);
        mProgressDialog.setMessage("数据正在努力加载……");
        mProgressDialog.show();
        vp_frag_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetSelectStatu();
                if (position == 0) {
                    tv_today.setSelected(!tv_today.isSelected());
                } else if (position == 1) {
                    tv_tomorrow.setSelected(!tv_tomorrow.isSelected());
                } else if (position == 2) {
                    tv_future.setSelected(!tv_future.isSelected());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(this);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//设置定位模式为高精度
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        return mOption;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_weather_search:
                Intent intent = new Intent(this, CityListActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.ib_back:
                finish();
                break;
            case R.id.tv_today:
                resetSelectStatu();
                tv_today.setSelected(!tv_today.isSelected());
                vp_frag_container.setCurrentItem(0);
                break;
            case R.id.tv_tomorrow:
                resetSelectStatu();
                tv_tomorrow.setSelected(!tv_tomorrow.isSelected());
                vp_frag_container.setCurrentItem(1);
                break;
            case R.id.tv_future:
                resetSelectStatu();
                tv_future.setSelected(!tv_future.isSelected());
                vp_frag_container.setCurrentItem(2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("tag", "===onActivityResult");
        if (requestCode == REQUEST_CODE && data != null) {
            CityBean cityBean = (CityBean) data.getSerializableExtra("city");
            new LoadDataTask().execute(cityBean.getAreaId());
        }
    }

    private void resetSelectStatu() {
        tv_today.setSelected(false);
        tv_tomorrow.setSelected(false);
        tv_future.setSelected(false);
    }

    /**
     * 定位监听回调
     *
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.e("tag", "onLocationChanged: " + aMapLocation);
        String cityName = "";
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            cityName = aMapLocation.getCity().replace("市", "");
            SPUtil.putString(this, SPUtil.CITY_KEY, cityName);
        } else {
            cityName = SPUtil.getString(this, SPUtil.CITY_KEY, getString(R.string.default_city));
            Toast.makeText(this, "位置信息获取失败", Toast.LENGTH_SHORT).show();
        }
        new LoadDataTask().execute(new DbOperate(WeatherActivity.this).getCityId(cityName));
    }

    /**
     * 开始定位
     */
    private void startLocation() {
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    class LoadDataTask extends AsyncTask<String, Void, WeatherBean> {

        @Override
        protected WeatherBean doInBackground(String... strings) {
            return new WeatherDataSource().getWeatherData(WeatherActivity.this, strings[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(WeatherActivity.this);
                mProgressDialog.setMessage("数据正在努力加载……");
                mProgressDialog.show();
                return;
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }

        @Override
        protected void onPostExecute(WeatherBean weatherBean) {
            mProgressDialog.dismiss();
            if (weatherBean == null) {
                Toast.makeText(WeatherActivity.this, "数据获取失败", Toast.LENGTH_SHORT).show();
            } else {
//                数据获取成功
                if (mAdapter == null) {
                    Log.e("tag", "onPostExecute: " + weatherBean.getCityName());
                    mAdapter = new FragmentContainerAdapter(getSupportFragmentManager(), weatherBean);
                    mAdapter.getItem(0);
                    vp_frag_container.setAdapter(mAdapter);
                    vp_frag_container.setCurrentItem(0);
                } else {
                    mAdapter.updateData(weatherBean);
                }


            }
        }
    }

}
