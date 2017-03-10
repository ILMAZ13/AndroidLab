package com.study.ilmaz.patternmvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.study.ilmaz.patternmvvm.R;
import com.study.ilmaz.patternmvvm.databinding.ActivityDetailBinding;
import com.study.ilmaz.patternmvvm.model.entities.User;
import com.study.ilmaz.patternmvvm.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {
    private static final String KEY_USER = "USER";

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        displayUp();
        getUserFromIntent();
    }

    private void getUserFromIntent() {
        User user = (User) getIntent().getSerializableExtra(KEY_USER);
        DetailViewModel viewModel = new DetailViewModel(user);
        binding.setDetailViewModel(viewModel);
    }

    private void displayUp() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public static Intent launchDeatail(Context context, User user){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_USER, user);
        return intent;
    }
}
