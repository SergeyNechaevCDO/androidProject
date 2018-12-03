package com.isever.sergn.homeproject.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isever.sergn.homeproject.R;
import com.isever.sergn.homeproject.resource.City;

import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {

    private LayoutInflater inflater;
    private int layout;
    private List<City> cities;

    public CityAdapter(Context context, int resource, List<City> cities) {
        super(context, resource, cities);
        this.cities = cities;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder") View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.gerb);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView capitalView = (TextView) view.findViewById(R.id.capital);

        City city = cities.get(position);

        flagView.setImageResource(city.getGerb());
        nameView.setText(city.getRegion());
        capitalView.setText(city.getCity());

        return view;
    }
}
