package com.study.ilmaz.patternmvpserviceload.view.interfaces;


import android.view.View;

import com.study.ilmaz.patternmvpserviceload.model.entities.User;

import java.util.List;

public interface PeopleActivityInterface {
    void showPeopleList(List<User> userList);
    void showLoading();
    void navigateToDetailActivity(User user, View view);
    void startLoadService(int count);
    void showError(String message);
}
