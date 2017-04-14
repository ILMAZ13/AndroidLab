package com.study.ilmaz.weatherwithcontentprovider.network;


import com.study.ilmaz.weatherwithcontentprovider.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class WeatherFactory {
    private static volatile WeatherService sService;

    public static WeatherService getWeatherService(){
        if(sService == null){
            synchronized (WeatherFactory.class){
                if (sService == null) {
                    sService = buildRetrofit().create(WeatherService.class);
                }
            }
        }
        return sService;
    }

    private static Retrofit buildRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
