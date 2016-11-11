package com.lxj022.lifeassistant.data.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lxj022.lifeassistant.data.bean.CityBean;
import com.lxj022.lifeassistant.data.bean.Note;

import java.util.ArrayList;
import java.util.List;

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
     * 通过城市名汉字或城市名拼音查找城市
     *
     * @param key
     * @return
     */
    public List<CityBean> searchCityName(String key) {
        List<CityBean> resultList = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getWritableDatabase();
//        select * from citys where (py like 'puy%' or pinyin like 'puy%' or cityName like 'puy%') and areaName==cityName
        Cursor cursor = database.rawQuery("select * from " + DataBaseHelper.TABLE_CITY + " where (py like ? or pinyin like ? or cityName like ?) and areaName==cityName", new String[]{key + "%", key + "%", key + "%"});
        if (cursor != null && cursor.getCount() > 0) {
            resultList = new ArrayList<>();
            CityBean cityBean;
            while (cursor.moveToNext()) {
                cityBean = new CityBean(cursor.getString(cursor.getColumnIndex("areaId")), cursor.getString(cursor.getColumnIndex("cityName")), cursor.getString(cursor.getColumnIndex("provinceName")));
                resultList.add(cityBean);
            }
            cursor.close();
        }
        database.close();
        return resultList;
    }


    /**
     * 获取所有省
     *
     * @return
     */
    public List<CityBean> getAllProvince() {
        List<CityBean> resultList = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select DISTINCT provinceName from " + DataBaseHelper.TABLE_CITY, null);
        if (cursor != null && cursor.getCount() > 0) {
            resultList = new ArrayList<>();
            CityBean cityBean;
            while (cursor.moveToNext()) {
                resultList.add(new CityBean(null, null, cursor.getString(cursor.getColumnIndex("provinceName"))));
            }
            cursor.close();
        }
        database.close();
        return resultList;
    }

    /**
     * 根据省获取所有省内城市
     *
     * @return
     */
    public List<CityBean> getCityName(String provinceName) {
        List<CityBean> resultList = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select areaId,cityName from " + DataBaseHelper.TABLE_CITY + " where areaName==cityName and provinceName='" + provinceName + "'", null);
        if (cursor != null && cursor.getCount() > 0) {
            resultList = new ArrayList<>();
            CityBean cityBean = null;
            while (cursor.moveToNext()) {
                cityBean = new CityBean(cursor.getString(cursor.getColumnIndex("areaId")), cursor.getString(cursor.getColumnIndex("cityName")), provinceName);
                resultList.add(cityBean);
            }
            cursor.close();
        }
        database.close();
        return resultList;
    }

    /**
     * 根据城市名查询城市id
     *
     * @param city
     * @return
     */
    public String getCityId(String city) {
        String areaId = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER, DataBaseHelper.CREATE_TABLE_CITY);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select areaId from " + DataBaseHelper.TABLE_CITY + " where areaName='" + city + "'", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            areaId = cursor.getString(cursor.getColumnIndex("areaId"));
            cursor.close();
        }
        database.close();
        return areaId;
    }

    /**
     * 缓存天气数据
     *
     * @param areaId 城市id
     * @param data   天气数据
     */
    public void saveWeatherData(String areaId, String data) {
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER, DataBaseHelper.CREATE_TABLE_WEATHER_DATA);
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelper.TABLE_WEATHER_DATA + " where areaId='" + areaId + "'", null);
        ContentValues values = new ContentValues();
        values.put("areaId", areaId);
        values.put("data", data);
        if (cursor != null && cursor.getCount() > 0) {
            Log.e("tag", "saveWeatherData: update");
            cursor.close();
            database.update(DataBaseHelper.TABLE_WEATHER_DATA, values, "areaId=?", new String[]{areaId});
        } else {
            Log.e("tag", "saveWeatherData: insert");
            database.insert(DataBaseHelper.TABLE_WEATHER_DATA, null, values);
        }
        database.close();
    }

    /**
     * 读取缓存的天气数据
     *
     * @param areaId 城市id
     * @return
     */
    public String readWeatherData(String areaId) {
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_WEATHER, DataBaseHelper.CREATE_TABLE_WEATHER_DATA);
        SQLiteDatabase database = helper.getReadableDatabase();
        String data = null;
        Log.e("tag", "readWeatherData: ");
        Cursor cursor = database.rawQuery("select * from " + DataBaseHelper.TABLE_WEATHER_DATA + " where areaId='" + areaId + "'", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            data = cursor.getString(cursor.getColumnIndex("data"));
            cursor.close();
        }
        return data;
    }

    /**
     * 添加便签
     *
     * @param note
     */
    public void addNote(Note note) {
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_NOTE, DataBaseHelper.CREATE_TABLE_NOTES);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time", System.currentTimeMillis());
        values.put("content", note.getContent());
        database.insert(DataBaseHelper.TABLE_NOTE, null, values);
        database.close();
    }

    /**
     * 删除便签
     *
     * @param note
     */
    public void deleteNote(Note note) {
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_NOTE, DataBaseHelper.CREATE_TABLE_NOTES);
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(DataBaseHelper.TABLE_NOTE, "_id=?", new String[]{note.get_id() + ""});
        database.close();
    }

    /**
     * 修改便签
     *
     * @param note
     */
    public void updateNote(Note note) {
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_NOTE, DataBaseHelper.CREATE_TABLE_NOTES);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", note.getContent());
        database.update(DataBaseHelper.TABLE_NOTE, values, "_id=?", new String[]{note.get_id() + ""});
        database.insert(DataBaseHelper.TABLE_NOTE, null, values);
        database.close();
    }

    /**
     * 获取所有便签
     *
     * @return
     */
    public List<Note> getAllNotes() {
        List<Note> notes = null;
        DataBaseHelper helper = new DataBaseHelper(context, DataBaseHelper.DB_NOTE, DataBaseHelper.CREATE_TABLE_NOTES);
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from " + DataBaseHelper.TABLE_NOTE, null);
        if (cursor != null && cursor.getCount() > 0) {
            notes = new ArrayList<>();
            Note note;
            while (cursor.moveToNext()) {
                note = new Note();

                note.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                note.setContent(cursor.getString(cursor.getColumnIndex("content")));
                note.setTime(cursor.getString(cursor.getColumnIndex("time")));
                notes.add(note);
            }
            cursor.close();
        }
        database.close();
        return notes;
    }

}
