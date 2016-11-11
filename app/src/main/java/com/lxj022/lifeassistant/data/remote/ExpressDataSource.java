package com.lxj022.lifeassistant.data.remote;

import android.text.TextUtils;
import android.util.Log;

import com.lxj022.lifeassistant.data.HttpRequest;
import com.lxj022.lifeassistant.data.UrlBuilder;
import com.lxj022.lifeassistant.data.bean.ExpressType;

import java.util.List;

/**
 * Created by superdroid on 2016/11/5.
 */
public class ExpressDataSource {

    public List<ExpressType> getExpressType() {
        String expressTypeUrl = new UrlBuilder().getExpressTypeUrl();

        if (TextUtils.isEmpty(expressTypeUrl)) {
            return null;
        }
        String response = HttpRequest.doGet(expressTypeUrl);
        Log.e("tag", "getExpressType: " + response);
//        if (TextUtils.isEmpty(response)) {
//            result = dbOperate.readWeatherData(cityId);
//        } else {
//            result = AES.decrypt(response);
//            if (TextUtils.isEmpty(result)) {
//                result = dbOperate.readWeatherData(cityId);
//            } else {
//                new DbOperate(context).saveWeatherData(cityId, result);
//            }
//        }
//        weatherBean = new BeanPaser().parseWeatherData(result);
        return null;
    }
}
