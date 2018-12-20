package com.isever.sergn.homeproject.controllers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isever.sergn.homeproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CityFragment extends Fragment {

    private boolean isExistWeather;
    private Parcel currentParcel;

    private List<PersonalData> persons;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cities_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isExistWeather = Objects.requireNonNull(getActivity()).findViewById(R.id.weather) != null;
        if (savedInstanceState != null) {
            currentParcel = savedInstanceState.getParcelable("CurrentCity");
        } else {
            currentParcel = new Parcel(0, getResources().getTextArray(R.array.Cities)[0].toString());
        }
        if (isExistWeather) {
            showWeather(currentParcel);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeData();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new CityAdapter(persons, new CityAdapter.ListInteractor() {
            @Override
            public void onItemClicked(int position) {
                currentParcel = new Parcel(position, persons.get(position).name);
                showWeather(currentParcel);
            }
        }));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("CurrentCity", currentParcel);
    }

    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new PersonalData("Москва", "Московская область", R.drawable.moscow));
        persons.add(new PersonalData("Самара", "Самарская область", R.drawable.samara));
        persons.add(new PersonalData("Якутия", "Якутская область", R.drawable.yakutia));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showWeather(Parcel parcel) {
        if (isExistWeather) {
            assert getFragmentManager() != null;
            WeatherFragment weatherFragment = (WeatherFragment) getFragmentManager().findFragmentById(R.id.weather);
            if (weatherFragment == null || !weatherFragment.getParcel().getCityName().equals(parcel.getCityName())) {
                weatherFragment = WeatherFragment.create(parcel);

                @SuppressLint("CommitTransaction") FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.weather, weatherFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(getActivity()), WeatherActivity.class);
            intent.putExtra(WeatherFragment.PARCEL, parcel);
            startActivity(intent);
        }
    }

    public interface OnClick {
        void onListClick(int position);
    }
}
