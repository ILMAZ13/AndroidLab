package com.study.ilmaz.patternmvpserviceload.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.study.ilmaz.patternmvpserviceload.R;
import com.study.ilmaz.patternmvpserviceload.model.entities.User;
import com.study.ilmaz.patternmvpserviceload.model.service.PeopleLoadIntentService;
import com.study.ilmaz.patternmvpserviceload.model.service.UserHolder;
import com.study.ilmaz.patternmvpserviceload.presenter.PeoplePresenter;
import com.study.ilmaz.patternmvpserviceload.view.interfaces.PeopleActivityInterface;

import java.util.List;

public class PeopleActivity extends AppCompatActivity implements PeopleActivityInterface{
    private PeoplePresenter presenter;
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private PeopleAdapter peopleAdapter;
    private ProgressBar progressBar;
    private BroadcastReceiver loadedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        presenter = new PeoplePresenter(this, new UserHolder(this));

        initViews();
        setupRecyclerView();
        registeringReceiver();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoadButtonPressed(v);
            }
        });
    }

    private void registeringReceiver() {
        loadedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = intent.getIntExtra(PeopleLoadIntentService.KEY_STATUS, PeopleLoadIntentService.KEY_ERROR);
                switch (result){
                    case PeopleLoadIntentService.KEY_ACCEPT :
                        presenter.onUsersLoaded();
                        break;
                    case PeopleLoadIntentService.KEY_ERROR :
                        presenter.onError();
                        break;
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(PeopleLoadIntentService.KEY_LOAD_BUNDLE);
        registerReceiver(loadedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(loadedReceiver);
    }

    private void setupRecyclerView() {
        peopleAdapter = new PeopleAdapter();
        peopleAdapter.setItemClickListener(new PeopleAdapter.ItemClickListener() {
            @Override
            public void onItemClick(User user, View view) {
                presenter.onItemClicked(user, view);
            }
        });
        recyclerView.setAdapter(peopleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_people);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (FloatingActionButton) findViewById(R.id.fab_add);
    }

    @Override
    public void showPeopleList(List<User> userList) {
        progressBar.setVisibility(View.GONE);
        peopleAdapter.setUserList(userList);
        peopleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToDetailActivity(User user, View view) {
        startActivity(DetailActivity.launchDetail(view.getContext(), user));
    }

    @Override
    public void startLoadService(int count) {
        startService(PeopleLoadIntentService.launchService(this, count));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
