package com.lxj022.lifeassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lxj022.lifeassistant.data.BeanPaser;
import com.lxj022.lifeassistant.data.HttpRequest;
import com.lxj022.lifeassistant.data.UrlBuilder;
import com.lxj022.lifeassistant.data.bean.CityBean;
import com.lxj022.lifeassistant.data.local.db.DbOperate;
import com.lxj022.lifeassistant.weather.WeatherActivity;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_weather = (LinearLayout) findViewById(R.id.ll_weather);
        ll_weather.setOnClickListener(this);
        //        new Thread() {
        //            public static final String TAG = "REQUEST";
        //
        //            @Override
        //            public void run() {
        //                                String response = HttpRequest.doGet("http://m.weather.com.cn/mweather/101010100.shtml");
        //
        //                //                List<CityBean> cityList = new BeanPaser().parseWeathercity(response);
        //                DbOperate dbOperate = new DbOperate(MainActivity.this);
        //                //                dbOperate.insertCity(cityList);
        //                Map<String, String> nameOrNo = dbOperate.getWeaidByNameOrNo("ke");
        //                Log.e(TAG, "run: " + nameOrNo);
        //                if (nameOrNo == null) {
        //                    return;
        //                }
        //                for (String citynm : nameOrNo.keySet()) {
        //                    Log.e(TAG, citynm + "=" + nameOrNo.get(citynm));
        //                }
        //            }
        //        }.start();
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.ll_weather:
                intent = new Intent(MainActivity.this, WeatherActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
