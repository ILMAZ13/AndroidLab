package com.study.ilmaz.mvpdatabinding.model.service;


import com.study.ilmaz.mvpdatabinding.model.entities.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserService {
    @GET("api")
    Call<Result> getResult(@Query("results") int count);
}
