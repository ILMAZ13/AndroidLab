package com.study.ilmaz.weatherwithcontentprovider.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.study.ilmaz.weatherwithcontentprovider.R;
import com.study.ilmaz.weatherwithcontentprovider.databinding.ItemWeatherBinding;
import com.study.ilmaz.weatherwithcontentprovider.model.simple.SimpleCity;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder> {
    private List<SimpleCity> cityList;
    private DelClickListener clickListener;

    public RecyclerAdapter() {
        cityList = Collections.emptyList();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWeatherBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_weather, parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(ItemHolder h, int position) {
        final SimpleCity tCity = cityList.get(position);
        h.bind(tCity);
        if(clickListener != null){
            h.r.ivClose.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickListener.onClick(tCity, v);
                        }
                    }
            );
        }
    }

    public void setCityList(List<SimpleCity> cityList){
        this.cityList = cityList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setOnDelClickListener(DelClickListener listener){
        this.clickListener = listener;
    }

    static class ItemHolder extends ViewHolder {
        ItemWeatherBinding r;

        ItemHolder(ItemWeatherBinding itemView) {
            super(itemView.itemView);
            this.r = itemView;
        }

        void bind(SimpleCity city) {
            Picasso.with(r.itemView.getContext())
                    .load("http://openweathermap.org/img/w/" + city.getIcon() + ".png")
                    .into(r.image);
            r.tvCityName.setText(city.getName());
            r.tvTemp.setText(city.getTemp() + "°C");
            r.tvHumidity.setText(city.getHumidity() + "%");
            r.tvPressure.setText(city.getPressure() + "мм.рт.ст");
        }
    }

    public interface DelClickListener {
        void onClick(SimpleCity city, View view);
    }
}
