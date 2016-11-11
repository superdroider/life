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
import com.lxj022.lifeassistant.express.ExpressActivity;
import com.lxj022.lifeassistant.notepad.NotepadListActivity;
import com.lxj022.lifeassistant.util.WeatherUtil;
import com.lxj022.lifeassistant.weather.WeatherActivity;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_weather;
    private LinearLayout ll_express;
    private LinearLayout ll_notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_weather = (LinearLayout) findViewById(R.id.ll_weather);
        ll_express = (LinearLayout) findViewById(R.id.ll_express);
        ll_notepad = (LinearLayout) findViewById(R.id.ll_notepad);
        ll_weather.setOnClickListener(this);
        ll_express.setOnClickListener(this);
        ll_notepad.setOnClickListener(this);
//        复制天气城市数据
        WeatherUtil.copyWeatherDatabase(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.ll_weather:
                intent = new Intent(MainActivity.this, WeatherActivity.class);
                break;
            case R.id.ll_express:
                intent = new Intent(MainActivity.this, ExpressActivity.class);
                break;
            case R.id.ll_notepad:
                intent = new Intent(MainActivity.this, NotepadListActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
