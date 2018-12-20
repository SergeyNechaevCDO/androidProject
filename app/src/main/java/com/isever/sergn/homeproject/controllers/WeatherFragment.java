package com.isever.sergn.homeproject.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.isever.sergn.homeproject.MainActivity;
import com.isever.sergn.homeproject.R;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class WeatherFragment extends Fragment {
    public static final String PARCEL = "parcel";

    public static WeatherFragment create(Parcel parcel) {
        WeatherFragment f = new WeatherFragment();

        Bundle args = new Bundle();
        args.putParcelable(PARCEL, parcel);
        f.setArguments(args);
        return f;
    }

    public Parcel getParcel() {
        assert getArguments() != null;
        return (Parcel) getArguments().getParcelable(PARCEL);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_weather, container, false);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            ImageView imageView = layout.findViewById(R.id.mainImageWeather);
            imageView.setMaxHeight(70);
        }

        TextView cityNameView = layout.findViewById(R.id.main_city);
        cityNameView.setText(getParcel().getCityName());
        MainMenu mainMenu = MainActivity.mainMenu;
        Log.d("#######", String.valueOf(mainMenu.getWind().isChecked()));
        if (mainMenu.getWind().isChecked()){
            layout.findViewById(R.id.wind).setVisibility(View.VISIBLE);
        }
        if (mainMenu.getHumidity().isChecked()){
            layout.findViewById(R.id.humidity).setVisibility(View.VISIBLE);
        }
        if (mainMenu.getPressure().isChecked()){
            layout.findViewById(R.id.pressure).setVisibility(View.VISIBLE);
        }
        return layout;
    }
}
