package com.study.ilmaz.mvpdatabinding.presenter;


import com.study.ilmaz.mvpdatabinding.model.entities.User;
import com.study.ilmaz.mvpdatabinding.view.interfaces.DetailActivityInterface;

public class DetailPresenter {
    private DetailActivityInterface activityInterface;

    public DetailPresenter(DetailActivityInterface activityInterface) {
        this.activityInterface = activityInterface;
    }

    public void onUserExtraLoaded(User user){
        activityInterface.showUserInformation(user);
    }
}
