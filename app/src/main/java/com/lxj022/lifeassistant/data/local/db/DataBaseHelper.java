package com.lxj022.lifeassistant.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @des 数据库帮助类，用来创建数据库和表
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_WEATHER = "weather.db";
    public static final String DB_NOTE = "weather.db";
    public static final String TABLE_NOTE = "notes";
    public static final String TABLE_CITY = "citys";
    public static final String TABLE_WEATHER_DATA = "weather_data";
    private static final int VERSION = 9;
    public static final String CREATE_TABLE_NOTES = "create table if not exists notes (_id integer primary key autoincrement, content text,time text)";
    public static final String CREATE_TABLE_CITY = "create table if not exists citys (_id integer primary key autoincrement, weaid varchar(2), citynm varchar(20), cityno varchar(40),cityid varchar(20))";
    public static final String CREATE_TABLE_WEATHER_DATA = "create table if not exists weather_data (_id integer primary key autoincrement, areaId varchar(2), data text)";
    private String createTableSql;

    public DataBaseHelper(Context context, String name, String createTableSql) {
        super(context, name, null, VERSION);
        this.createTableSql = createTableSql;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(this.createTableSql);
    }
}
