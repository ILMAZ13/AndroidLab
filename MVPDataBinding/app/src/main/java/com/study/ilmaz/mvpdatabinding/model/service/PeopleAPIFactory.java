package com.study.ilmaz.mvpdatabinding.model.service;


import com.study.ilmaz.mvpdatabinding.model.entities.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleAPIFactory {
    public static List<User> getUsers(int count) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RandomUserService service = retrofit.create(RandomUserService.class);
        List<User> userList = service.getResult(count).execute().body().getResults();
        return userList;
    }
}
