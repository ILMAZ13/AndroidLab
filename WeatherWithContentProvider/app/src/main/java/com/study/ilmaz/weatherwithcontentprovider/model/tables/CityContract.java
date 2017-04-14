package com.study.ilmaz.weatherwithcontentprovider.model.tables;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.study.ilmaz.weatherwithcontentprovider.data.WeatherContentProvider;
import com.study.ilmaz.weatherwithcontentprovider.model.simple.SimpleCity;

public class CityContract {
    public static final String TABLE_NAME = "cities";

    public void createTable(@NonNull SQLiteDatabase database) {
        TableBuilder.create(TABLE_NAME)
                .intColumn(CityEntry._ID)
                .textColumn(CityEntry.COLUMN_NAME)
                .realColumn(CityEntry.COLUMN_TEMP)
                .realColumn(CityEntry.COLUMN_PRESSURE)
                .realColumn(CityEntry.COLUMN_HUMIDITY)
                .textColumn(CityEntry.COLUMN_ICON_URL)
                .primaryKey(CityEntry._ID)
                .execute(database);
    }

    public ContentValues toValues(@NonNull SimpleCity city){
        ContentValues values = new ContentValues();
        values.put(CityEntry._ID, city.getId());
        values.put(CityEntry.COLUMN_NAME, city.getName());
        values.put(CityEntry.COLUMN_TEMP, city.getTemp());
        values.put(CityEntry.COLUMN_PRESSURE, city.getPressure());
        values.put(CityEntry.COLUMN_HUMIDITY, city.getHumidity());
        values.put(CityEntry.COLUMN_ICON_URL, city.getIcon());
        return values;
    }

    public static SimpleCity fromCursor(Cursor cursor){
        return new SimpleCity(
                cursor.getInt(cursor.getColumnIndex(CityEntry._ID)),
                cursor.getString(cursor.getColumnIndex(CityEntry.COLUMN_NAME)),
                cursor.getDouble(cursor.getColumnIndex(CityEntry.COLUMN_TEMP)),
                cursor.getDouble(cursor.getColumnIndex(CityEntry.COLUMN_PRESSURE)),
                cursor.getDouble(cursor.getColumnIndex(CityEntry.COLUMN_HUMIDITY)),
                cursor.getString(cursor.getColumnIndex(CityEntry.COLUMN_ICON_URL))
        );
    }


    public static Uri getUri(){
        return WeatherContentProvider.getBaseUri().buildUpon().appendPath(TABLE_NAME).build();
    }

    public static final class CityEntry implements BaseColumns {
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TEMP = "temp";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_ICON_URL = "icon_url";
    }
}
