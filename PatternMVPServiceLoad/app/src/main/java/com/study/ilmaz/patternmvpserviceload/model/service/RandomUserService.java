package com.study.ilmaz.patternmvpserviceload.model.service;


import com.study.ilmaz.patternmvpserviceload.model.entities.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserService {
    @GET("api")
    Call<Result> getResult(@Query("results") int count);
}
