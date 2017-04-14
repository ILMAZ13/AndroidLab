package com.study.ilmaz.weatherwithcontentprovider.network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.study.ilmaz.weatherwithcontentprovider.model.net.City;
import com.study.ilmaz.weatherwithcontentprovider.model.simple.SimpleCity;
import com.study.ilmaz.weatherwithcontentprovider.model.tables.CityContract;
import com.study.ilmaz.weatherwithcontentprovider.model.tables.Where;

import java.io.IOException;

public class MyIntentService extends IntentService {
    private static final String CITY_NAME_KEY = "city_name";

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void start(@NonNull Context context, @NonNull String cityName) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.putExtra(CITY_NAME_KEY, cityName);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String cityName = intent.getStringExtra(CITY_NAME_KEY);
        try {
            City city = WeatherFactory.getWeatherService()
                    .getCity(cityName)
                    .execute()
                    .body();
            if (city != null) {
                Where mWhere = Where.create().equalTo(CityContract.CityEntry.COLUMN_NAME, cityName);
                getContentResolver().delete(CityContract.getUri(), mWhere.where(), mWhere.whereArgs());
                getContentResolver().insert(CityContract.getUri(), new CityContract().toValues(new SimpleCity(city)));
            }
            getContentResolver().notifyChange(CityContract.getUri(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
