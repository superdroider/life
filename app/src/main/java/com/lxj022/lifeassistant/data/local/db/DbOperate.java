package com.lxj022.lifeassistant.data.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lxj022.lifeassistant.data.bean.CityBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Superdroid
 * @time 2016/9/1 20:21
 * @des
 */
public class DbOperate {
    private Context context;

    public DbOperate(Context context) {
        this.context = context;
    }

    /**
     * 将获取的城市数据添加到数据库中
     *
     * @param cityList 城市数据集合
     */
    public void insertCity(List<CityBean> cityList) {
        Log.e("tag", "insertCity: ");
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER_CITY, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (CityBean city : cityList) {
            values.clear();
            values.put("weaid", city.getWeaid());
            values.put("citynm", city.getCitynm());
            values.put("cityno", city.getCityno());
            values.put("cityid", city.getCityid());
            //            insert into city (weaid,cityid,citynm,cityno) values ('1','101010100','北京','beijing')
            //            使用sql命令插入
            //            database.execSQL("insert into " + DataBaseHelper.TABLE_CITY
            //                    + " (weaid,cityid,citynm,cityno) values ('"
            //                    + city.getWeaid() + "','"
            //                    + city.getCityid() + "','"
            //                    + city.getCitynm() + "','"
            //                    + city.getCityno() + "')");
            //            使用contentValue插入
            database.insert(DataBaseHelper.TABLE_CITY, null, values);
        }
        database.close();
        Log.e("tag", "insertCity: ok");
    }

    /**
     * 通过城市名汉字或城市名拼音查找城市
     *
     * @param key
     * @return
     */
    public Map<String, String> getWeaidByNameOrNo(String key) {
        Map<String, String> resultMap = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER_CITY, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select citynm,weaid from " + DataBaseHelper.TABLE_CITY + " where citynm like ? or cityno like ?", new String[]{key + "%", key + "%"});
        if (cursor != null && cursor.getCount() > 0) {
            resultMap = new HashMap<>();
            while (cursor.moveToNext()) {
                resultMap.put(cursor.getString(cursor.getColumnIndex("citynm")), cursor.getString(cursor.getColumnIndex("weaid")));
            }
            cursor.close();
        }
        database.close();
        return resultMap;
    }

    /**
     * 根据城市名查找对应weaid
     *
     * @param cityName 城市名
     * @return
     */
    public String getWeaidByName(String cityName) {
        String weaid = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER_CITY, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select weaid from " + DataBaseHelper.TABLE_CITY + " where citynm = ?", new String[]{cityName});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                weaid = cursor.getString(cursor.getColumnIndex("weaid"));
            }
            cursor.close();
        }
        database.close();
        return weaid;
    }

    /**
     * 查找所有城市信息
     *
     * @return
     */
    public List<CityBean> getAllCity() {
        List<CityBean> resultList = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER_CITY, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from " + DataBaseHelper.TABLE_CITY, null);
        if (cursor != null && cursor.getCount() > 0) {
            resultList = new ArrayList<>();
            CityBean cityBean = null;
            while (cursor.moveToNext()) {
                cityBean = new CityBean(
                        cursor.getString(cursor.getColumnIndex("weaid")),
                        cursor.getString(cursor.getColumnIndex("citynm")),
                        cursor.getString(cursor.getColumnIndex("cityno")),
                        cursor.getString(cursor.getColumnIndex("cityid"))
                );
                resultList.add(cityBean);
            }
            cursor.close();
        }
        database.close();
        return resultList;
    }
}
