package com.study.ilmaz.weatherwithcontentprovider.view;

import android.database.ContentObserver;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.study.ilmaz.weatherwithcontentprovider.R;
import com.study.ilmaz.weatherwithcontentprovider.databinding.ActivityMainBinding;
import com.study.ilmaz.weatherwithcontentprovider.model.simple.SimpleCity;
import com.study.ilmaz.weatherwithcontentprovider.model.tables.CityContract;
import com.study.ilmaz.weatherwithcontentprovider.model.tables.Where;
import com.study.ilmaz.weatherwithcontentprovider.network.MyIntentService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private ActivityMainBinding r;
    private RecyclerAdapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private List<SimpleCity> cityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(r.toolbar);
        r.swipeRefresh.setOnRefreshListener(this);
        cityList = new ArrayList<>();
        initRecyclerView();
        r.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWeather();
            }
        });
        requestChanges(CityContract.getUri());
    }

    private final ContentObserver newDbObserver = new ContentObserver(mHandler) {
        @Override
        public void onChange(boolean selfChange, final Uri uri) {
            super.onChange(selfChange, uri);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    requestChanges(uri);
                }
            });
            r.swipeRefresh.setRefreshing(false);
        }
    };

    void loadWeather() {
        r.swipeRefresh.setRefreshing(true);
        MyIntentService.start(this, r.etSearch.getText().toString());
        getContentResolver().registerContentObserver(CityContract.getUri(), false, newDbObserver);
    }

    private void initRecyclerView() {
        adapter = new RecyclerAdapter();
        adapter.setOnDelClickListener(new RecyclerAdapter.DelClickListener() {
            @Override
            public void onClick(SimpleCity city, View view) {
                delete(city);
            }
        });
        r.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        r.recyclerView.setAdapter(adapter);
    }

    public void requestChanges(@NonNull Uri uri) {
        cityList = new ArrayList<>();
        Where cityWhere = Where.create();
        Cursor cityCursor = getContentResolver().query(CityContract.getUri(), null,
                cityWhere.where(), cityWhere.whereArgs(), null);
        if (cityCursor != null) {
            while (cityCursor.moveToNext()) {
                cityList.add(CityContract.fromCursor(cityCursor));
            }
            cityCursor.close();
            showWeather();
        }
    }

    void showWeather() {
        adapter.setCityList(cityList);
    }

    public void delete(SimpleCity city) {
        Where cityWhere = Where.create().equalTo(CityContract.CityEntry._ID, city.getId());
        getContentResolver().delete(CityContract.getUri(), cityWhere.where(), cityWhere.whereArgs());
        loadWeather();
    }

    @Override
    public void onRefresh() {
        List<SimpleCity> cities = new ArrayList<>();
        Where cityWhere = Where.create();
        Cursor cityCursor = getContentResolver().query(CityContract.getUri(), null,
                cityWhere.where(), cityWhere.whereArgs(), null);
        if (cityCursor != null) {
            while (cityCursor.moveToNext()) {
                cities.add(CityContract.fromCursor(cityCursor));
            }
            cityCursor.close();
        }
        for (SimpleCity city :
                cities) {
            r.swipeRefresh.setRefreshing(true);
            MyIntentService.start(this, city.getName());
            getContentResolver().registerContentObserver(CityContract.getUri(), false, newDbObserver);
        }
    }
}
