package com.study.ilmaz.mvpdatabinding.model.service;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.ilmaz.mvpdatabinding.model.entities.User;

import java.lang.reflect.Type;
import java.util.List;

public class UserHolder {
    private SharedPreferences preferences;
    private static final String KEY_USER_HOLDER = "USER_HOLDER";
    private static final String KEY_LAST_LOAD_USERS = "LAST_LOAD_USERS";

    public UserHolder(@NonNull Context context) {
        context = context.getApplicationContext();
        preferences = context.getSharedPreferences(KEY_USER_HOLDER, Context.MODE_PRIVATE);
    }

    public void writeUserList(List<User> userList){
        SharedPreferences.Editor editor = preferences.edit();
        String json = new Gson().toJson(userList);
        editor.putString(KEY_LAST_LOAD_USERS, json);
        editor.apply();
    }

    public List<User> readLastUsersList(){
        String json = preferences.getString(KEY_LAST_LOAD_USERS, "");
        Type type = new TypeToken<List<User>>(){}.getType();
        List<User> userList = new Gson().fromJson(json, type);
        return userList;
    }
}
