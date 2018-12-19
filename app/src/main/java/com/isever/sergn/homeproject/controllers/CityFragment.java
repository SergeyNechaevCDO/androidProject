package com.isever.sergn.homeproject.controllers;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.isever.sergn.homeproject.R;

import java.util.Objects;

public class CityFragment extends ListFragment {

    private boolean isExistWeather;
    private Parcel currentParcel;
    private OnClick onClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cities_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                Objects.requireNonNull(getActivity()),
                R.array.Cities,
                android.R.layout.simple_list_item_activated_1
        );

        setListAdapter(adapter);

        isExistWeather = getActivity().findViewById(R.id.weather) != null;

        if (savedInstanceState != null) {
            currentParcel = savedInstanceState.getParcelable("CurrentCity");
        } else {
            currentParcel = new Parcel(
                    0,
                    getResources().getTextArray(R.array.Cities)[0].toString()
            );
        }

        if (isExistWeather) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showWeather(currentParcel);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("CurrentCity", currentParcel);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView cityNameView = (TextView) v;

        currentParcel = new Parcel(position, cityNameView.getText().toString());

        showWeather(currentParcel);
        if (onClickListener != null) {
            onClickListener.onListClick(position);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onClickListener = null;
    }

    private void showWeather(Parcel parcel) {
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
            intent.setClass(getActivity(), WeatherActivity.class);
            intent.putExtra(WeatherFragment.PARCEL, parcel);
            startActivity(intent);
        }
    }

    public interface OnClick {
        void onListClick(int position);
    }
}
