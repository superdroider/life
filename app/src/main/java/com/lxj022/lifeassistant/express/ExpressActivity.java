package com.lxj022.lifeassistant.express;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.remote.ExpressDataSource;

public class ExpressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        new Thread() {
            @Override
            public void run() {
                new ExpressDataSource().getExpressType();
            }
        }.start();
    }
}
