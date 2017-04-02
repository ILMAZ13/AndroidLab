package com.study.ilmaz.mvpdatabinding.view.interfaces;


import android.view.View;

import com.study.ilmaz.mvpdatabinding.model.entities.User;

import java.util.List;

public interface PeopleActivityInterface {
    void showPeopleList(List<User> userList);
    void showLoading();
    void navigateToDetailActivity(User user, View view);
    void startLoadService(int count);
    void showError(String message);
}
