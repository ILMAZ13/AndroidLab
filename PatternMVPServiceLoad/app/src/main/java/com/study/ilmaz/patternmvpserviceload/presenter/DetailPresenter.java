package com.study.ilmaz.patternmvpserviceload.presenter;


import com.study.ilmaz.patternmvpserviceload.model.entities.User;
import com.study.ilmaz.patternmvpserviceload.view.interfaces.DetailActivityInterface;

public class DetailPresenter {
    private DetailActivityInterface activityInterface;

    public DetailPresenter(DetailActivityInterface activityInterface) {
        this.activityInterface = activityInterface;
    }

    public void onUserExtraLoaded(User user){
        activityInterface.showUserInformation(user);
    }
}
