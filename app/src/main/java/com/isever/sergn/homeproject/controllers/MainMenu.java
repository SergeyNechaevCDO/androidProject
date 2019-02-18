package com.isever.sergn.homeproject.controllers;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.isever.sergn.homeproject.R;

public class MainMenu {

    private MenuItem wind;
    private MenuItem humidity;
    private MenuItem pressure;
    private MenuItem http;

    public MenuItem getWind() {
        return wind;
    }

    public MenuItem getHumidity() {
        return humidity;
    }

    public MenuItem getPressure() {
        return pressure;
    }

    public MenuItem getHttp() {
        return http;

    }

    public boolean optionSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_wind:
                if (item.isCheckable() && item.isChecked()) {
                    wind.setChecked(false);
                    wind.setTitle(R.string.action_wind);
                } else {
                    wind.setChecked(true);
                    wind.setTitle(R.string.deactivate_wind);
                }
                return true;
            case R.id.action_humidity:
                if (item.isCheckable() && item.isChecked()) {
                    humidity.setChecked(false);
                    humidity.setTitle(R.string.action_humidity);
                } else {
                    humidity.setChecked(true);
                    humidity.setTitle(R.string.deactivate_humidity);
                }
                return true;
            case R.id.action_pressure:
                if (item.isCheckable() && item.isChecked()) {
                    pressure.setChecked(false);
                    pressure.setTitle(R.string.action_pressure);
                } else {
                    pressure.setChecked(true);
                    pressure.setTitle(R.string.deactivate_pressure);
                }
                return true;
            case R.id.action_http:
                if (item.isChecked()) {
                    http.setChecked(false);
                } else {
                    http.setChecked(true);
                }
                return true;
            default:
                return false;

        }
    }

    public boolean createOption(Menu menu) {
        wind = menu.findItem(R.id.action_wind);
        humidity = menu.findItem(R.id.action_humidity);
        pressure = menu.findItem(R.id.action_pressure);
        http = menu.findItem(R.id.action_http);
        return true;
    }
}
