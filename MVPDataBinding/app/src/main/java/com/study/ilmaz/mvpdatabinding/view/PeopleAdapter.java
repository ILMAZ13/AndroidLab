package com.study.ilmaz.mvpdatabinding.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.study.ilmaz.mvpdatabinding.R;
import com.study.ilmaz.mvpdatabinding.databinding.ItemPeopleBinding;
import com.study.ilmaz.mvpdatabinding.model.entities.User;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{
    private List<User> userList;
    private ItemClickListener itemClickListener;

    public PeopleAdapter() {
        userList = Collections.emptyList();
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPeopleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people, parent, false);
        return new PeopleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final PeopleViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.bind(user);
        if(itemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(user, v);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder{
        ItemPeopleBinding binding;

        public PeopleViewHolder(ItemPeopleBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }

        void bind(User user){
            binding.tvName.setText(user.getFullName());
            binding.tvPhone.setText(user.getPhone());
            Picasso.with(itemView.getContext())
                    .load(user.getPicture().getLarge())
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(binding.imageView);
        }
    }

    public interface ItemClickListener {
        void onItemClick(User user, View view);
    }
}
