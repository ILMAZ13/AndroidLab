package com.study.ilmaz.patternmvvm.viewmodel;


import com.study.ilmaz.patternmvvm.model.entities.User;

public class DetailViewModel {
    private User user;

    public DetailViewModel(User user) {
        this.user = user;
    }

    public String getFullName(){
        return user.getFullName();
    }

    public String getSex(){
        return user.getGender();
    }

    public String getEmail(){
        return user.getEmail();
    }

    public String getPhone(){
        return user.getPhone();
    }

    public String getLogin(){
        return user.getLogin().getUsername();
    }
}
