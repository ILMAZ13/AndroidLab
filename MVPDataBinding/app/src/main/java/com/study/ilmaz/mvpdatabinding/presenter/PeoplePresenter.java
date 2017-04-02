package com.study.ilmaz.mvpdatabinding.presenter;


import android.view.View;

import com.study.ilmaz.mvpdatabinding.model.entities.User;
import com.study.ilmaz.mvpdatabinding.model.service.UserHolder;
import com.study.ilmaz.mvpdatabinding.view.interfaces.PeopleActivityInterface;

import java.util.ArrayList;
import java.util.List;

public class PeoplePresenter {
    private PeopleActivityInterface activityInterface;
    private List<User> users;
    private UserHolder holder;

    public PeoplePresenter(PeopleActivityInterface activityInterface, UserHolder holder) {
        this.activityInterface = activityInterface;
        this.holder = holder;
        users = new ArrayList<>();
    }

    public void onLoadButtonPressed(View view) {
        activityInterface.showLoading();
       activityInterface.startLoadService(20);
    }

    public void onItemClicked(User user, View view) {
        activityInterface.navigateToDetailActivity(user, view);
    }

    public void onUsersLoaded(){
        List<User> temp = holder.readLastUsersList();
        users.addAll(temp);
        activityInterface.showPeopleList(users);
    }

    public void onError(){
        activityInterface.showError("Can't load new people");
    }
}
