package com.study.ilmaz.mvpdatabinding.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.study.ilmaz.mvpdatabinding.R;
import com.study.ilmaz.mvpdatabinding.databinding.ActivityDetailBinding;
import com.study.ilmaz.mvpdatabinding.model.entities.User;
import com.study.ilmaz.mvpdatabinding.presenter.DetailPresenter;
import com.study.ilmaz.mvpdatabinding.view.interfaces.DetailActivityInterface;

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface {
    private static final String KEY_USER = "USER";
    private DetailPresenter presenter;
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        presenter = new DetailPresenter(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUserFromExtra();
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
        binding.tvName.setText(user.getFullName());
        binding.tvPhone.setText(user.getPhone());
        binding.tvSex.setText(user.getGender());
        binding.tvEmail.setText(user.getEmail());
        binding.tvLogin.setText(user.getLogin().getUsername());
        Picasso.with(this)
                .load(user.getPicture().getLarge())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(binding.imageView);
    }
}
