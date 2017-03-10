package com.study.ilmaz.patternmvvm.viewmodel;


import android.content.Context;
import android.databinding.ObservableInt;
import android.view.View;

import com.study.ilmaz.patternmvvm.model.entities.User;
import com.study.ilmaz.patternmvvm.model.service.PeopleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PeopleViewModel extends Observable{
    private Context context;
    private List<User> userList;
    public ObservableInt peopleProgress;
    public ObservableInt peopleRecycler;

    public PeopleViewModel(Context context) {
        this.context = context;
        userList = new ArrayList<>();
        peopleProgress = new ObservableInt(View.GONE);
        peopleRecycler = new ObservableInt(View.VISIBLE);
    }

    public void onClickFabLoad(View view){
        peopleProgress.set(View.VISIBLE);
        loadUsers();
    }

    private void loadUsers(){
        userList.addAll(PeopleFactory.getUsers(20));
        peopleProgress.set(View.GONE);
        setChanged();
        notifyObservers();
    }

    public List<User> getUserList() {
        return userList;
    }

}
