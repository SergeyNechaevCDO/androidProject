package com.isever.sergn.homeproject.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.isever.sergn.homeproject.MainActivity;
import com.isever.sergn.homeproject.R;
import com.isever.sergn.homeproject.request.OkHttpRequester;

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
        MainMenu mainMenu = MainActivity.mainMenu;

        View layout = inflater.inflate(R.layout.fragment_weather, container, false);

        if (!mainMenu.getHttp().isChecked()) {
            layout.findViewById(R.id.main_web).setVisibility(View.VISIBLE);
            final WebView page = layout.findViewById(R.id.main_web);

            OkHttpRequester requester = new OkHttpRequester(new OkHttpRequester.OnResponseCompleted() {
                @Override
                public void onCompleted(String content) {
                    page.loadData(content, "text/html; charset=utf-8", "utf-8");
                }
            });

            requester.run("https://weather.com/ru-RU/weather/today/l/55.75,37.58");

        } else {
            layout.findViewById(R.id.main_params_layout_weather).setVisibility(View.VISIBLE);

            if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
                ImageView imageView = layout.findViewById(R.id.mainImageWeather);
                imageView.setMaxHeight(70);
            }

            TextView cityNameView = layout.findViewById(R.id.main_city);
            cityNameView.setText(getParcel().getCityName());

            if (mainMenu.getWind().isChecked()) {
                layout.findViewById(R.id.wind).setVisibility(View.VISIBLE);
            }
            if (mainMenu.getHumidity().isChecked()) {
                layout.findViewById(R.id.humidity).setVisibility(View.VISIBLE);
            }
            if (mainMenu.getPressure().isChecked()) {
                layout.findViewById(R.id.pressure).setVisibility(View.VISIBLE);
            }
        }
        return layout;
    }
}
