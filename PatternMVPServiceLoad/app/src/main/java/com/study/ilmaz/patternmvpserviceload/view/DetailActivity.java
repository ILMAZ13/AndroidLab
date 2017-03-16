package com.study.ilmaz.patternmvpserviceload.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.study.ilmaz.patternmvpserviceload.R;
import com.study.ilmaz.patternmvpserviceload.model.entities.User;
import com.study.ilmaz.patternmvpserviceload.presenter.DetailPresenter;
import com.study.ilmaz.patternmvpserviceload.view.interfaces.DetailActivityInterface;

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface {
    private static final String KEY_USER = "USER";
    private DetailPresenter presenter;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvSex;
    private TextView tvLogin;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = new DetailPresenter(this);

        initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUserFromExtra();
    }

    private void initViews() {
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    private void getUserFromExtra() {
        User user = (User) getIntent().getSerializableExtra(KEY_USER);
        presenter.onUserExtraLoaded(user);
    }

    public static Intent launchDetail(Context context, User user) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_USER, user);
        return intent;
    }

    @Override
    public void showUserInformation(User user) {
        tvName.setText(user.getFullName());
        tvPhone.setText(user.getPhone());
        tvSex.setText(user.getGender());
        tvEmail.setText(user.getEmail());
        tvLogin.setText(user.getLogin().getUsername());
        Picasso.with(this)
                .load(user.getPicture().getLarge())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imageView);
    }
}
