package com.study.ilmaz.patternmvvm.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.ilmaz.patternmvvm.R;
import com.study.ilmaz.patternmvvm.databinding.ItemPeopleBinding;
import com.study.ilmaz.patternmvvm.model.entities.User;
import com.study.ilmaz.patternmvvm.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{
    private List<User> userList;

    public PeopleAdapter() {
        this.userList = Collections.emptyList();
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPeopleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people, parent, false);
        return new PeopleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        holder.bindPeople(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder{
        ItemPeopleBinding binding;

        public PeopleViewHolder(ItemPeopleBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }

        void bindPeople(User user){
            if(binding.getItemViewModel() == null){
                binding.setItemViewModel(
                        new ItemPeopleViewModel(user, itemView.getContext())
                );
            } else {
                binding.getItemViewModel().setUser(user);
            }
        }
    }
}
