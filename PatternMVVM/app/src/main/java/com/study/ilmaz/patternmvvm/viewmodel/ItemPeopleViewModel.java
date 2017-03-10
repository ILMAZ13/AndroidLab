package com.study.ilmaz.patternmvvm.viewmodel;


import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.study.ilmaz.patternmvvm.model.entities.User;
import com.study.ilmaz.patternmvvm.view.DetailActivity;

public class ItemPeopleViewModel extends BaseObservable{
    private User user;
    private Context context;

    public ItemPeopleViewModel(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    public String getFullName(){
        return user.getFullName();
    }

    public String getPhone(){
        return user.getPhone();
    }

    public void onItemClick(View view){
        context.startActivity(DetailActivity.launchDeatail(context, user));
    }

    public void setUser(User user){
        this.user = user;
        notifyChange();
    }
}
