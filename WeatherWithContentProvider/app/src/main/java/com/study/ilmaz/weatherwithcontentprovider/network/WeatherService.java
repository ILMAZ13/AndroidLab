package com.study.ilmaz.weatherwithcontentprovider.network;


import android.support.annotation.NonNull;

import com.study.ilmaz.weatherwithcontentprovider.BuildConfig;
import com.study.ilmaz.weatherwithcontentprovider.model.net.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("/data/2.5/weather?units=metric&appid=" + BuildConfig.API_KEY)
    Call<City> getCity(@NonNull @Query("q") String name);
}
