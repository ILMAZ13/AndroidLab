package com.study.ilmaz.weatherwithcontentprovider.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.ilmaz.weatherwithcontentprovider.model.tables.CityContract;

public class WeatherSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "weather.db";
    private static final int CURRENT_VERSION = 1;

    public WeatherSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new CityContract().createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ebigusey
    }
}
