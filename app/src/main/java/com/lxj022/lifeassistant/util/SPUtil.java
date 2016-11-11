package com.lxj022.lifeassistant.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by superdroid on 2016/10/23.
 */
public class SPUtil {

    private static final String SP_FILE_NAME = "sp_data";
    public static final String CITY_KEY = "city";

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static String getString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, value);
    }
}
