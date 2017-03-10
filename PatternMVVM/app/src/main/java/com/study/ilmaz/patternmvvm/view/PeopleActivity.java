package com.study.ilmaz.patternmvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.study.ilmaz.patternmvvm.R;
import com.study.ilmaz.patternmvvm.databinding.ActivityPeopleBinding;
import com.study.ilmaz.patternmvvm.viewmodel.PeopleViewModel;

import java.util.Observable;
import java.util.Observer;

public class PeopleActivity extends AppCompatActivity implements Observer{
    private ActivityPeopleBinding binding;
    private PeopleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people);
        viewModel = new PeopleViewModel(this);
        binding.setMainViewModel(viewModel);

        PeopleAdapter adapter = new PeopleAdapter();
        binding.rvPeople.setAdapter(adapter);
        binding.rvPeople.setLayoutManager(new LinearLayoutManager(this));

        viewModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof PeopleViewModel){
            PeopleAdapter adapter = (PeopleAdapter) binding.rvPeople.getAdapter();
            PeopleViewModel peopleViewModel = (PeopleViewModel) o;
            adapter.setUserList(peopleViewModel.getUserList());
        }
    }


}
