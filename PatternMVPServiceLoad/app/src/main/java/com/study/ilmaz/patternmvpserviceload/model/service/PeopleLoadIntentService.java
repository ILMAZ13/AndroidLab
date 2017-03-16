package com.study.ilmaz.patternmvpserviceload.model.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.study.ilmaz.patternmvpserviceload.model.entities.User;

import java.io.IOException;
import java.util.List;

public class PeopleLoadIntentService extends IntentService {
    private static final String KEY_COUNT = "COUNT";
    public static final String KEY_LOAD_BUNDLE = "com.study.ilmaz.patternmvpserviceload.model.service.LOAD_BUNDLE";
    public static final String KEY_STATUS = "STATUS";
    public static final int KEY_ACCEPT = 200;
    public static final int KEY_ERROR = 404;

    public PeopleLoadIntentService() {
        super("PeopleLoadIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int count = intent.getIntExtra(KEY_COUNT, 1);
            try {
                List<User> userList = PeopleAPIFactory.getUsers(count);
                new UserHolder(getApplicationContext()).writeUserList(userList);
                Intent intent1 = new Intent(KEY_LOAD_BUNDLE);
                intent1.putExtra(KEY_STATUS, KEY_ACCEPT);
                sendBroadcast(intent1);
            } catch (IOException e) {
                e.printStackTrace();
                Intent intent1 = new Intent(KEY_LOAD_BUNDLE);
                intent1.putExtra(KEY_STATUS, KEY_ERROR);
                sendBroadcast(intent1);
            }
        }
    }

    public static Intent launchService(Context context, int count){
        Intent intent = new Intent(context, PeopleLoadIntentService.class);
        intent.putExtra(KEY_COUNT, count);
        return intent;
    }
}
