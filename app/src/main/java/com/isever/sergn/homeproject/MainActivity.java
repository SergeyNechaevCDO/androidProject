package com.isever.sergn.homeproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.isever.sergn.homeproject.controllers.CityAdapter;
import com.isever.sergn.homeproject.resource.City;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
     * param String
     */
    public final static String CITY = "CITY";
    /**
     * param boolean
     */
    public final static String WIND = "WIND";
    /**
     * param boolean
     */
    public final static String HUMIDITY = "HUMIDITY";
    /**
     * param boolean
     */
    public final static String PRESSURE = "PRESSURE";
    /**
     * param int
     */
    public final static String THEME = "THEME";


    private MenuItem wind;
    private MenuItem humidity;
    private MenuItem pressure;
    private MenuItem myTheme;
    private SharedPreferences sPref;
    public final static String SAVED_TEXT = "myTheme";

    private ArrayList<City> cities = new ArrayList<>();

    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadTheme();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setInitialData();
        createCountriesList();
    }

    private void saveTheme(int theme, int themeId){
        sPref = getPreferences(MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(SAVED_TEXT, themeId);
        ed.apply();
        if (theme == 1){
            Toast.makeText(this, R.string.editWhiteTheme, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.editDarkTheme, Toast.LENGTH_SHORT).show();
        }

    }

    public void loadTheme() {
        sPref = getPreferences(MODE_PRIVATE);
        int savedText = sPref.getInt(SAVED_TEXT, R.style.AppTheme);
        this.setTheme(savedText);
    }

    private int getThemeId(){
        sPref = getPreferences(MODE_PRIVATE);
        return sPref.getInt(SAVED_TEXT, R.style.AppTheme);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
            case R.id.action_theme:
                if (item.isChecked()) {
                    myTheme.setChecked(false);
                    myTheme.setTitle(R.string.whiteTheme);
                    saveTheme(1, R.style.AppTheme);
                } else {
                    myTheme.setChecked(true);
                    myTheme.setTitle(R.string.darkTheme);
                    saveTheme(2, R.style.MyAppTheme);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        wind = menu.findItem(R.id.action_wind);
        humidity = menu.findItem(R.id.action_humidity);
        pressure = menu.findItem(R.id.action_pressure);
        myTheme = menu.findItem(R.id.action_theme);
        return true;
    }

    private void createCountriesList() {
        countriesList = findViewById(R.id.countriesList);
        CityAdapter cityAdapter = new CityAdapter(this, R.layout.list_item, cities);
        countriesList.setAdapter(cityAdapter);
        countriesList.setOnItemClickListener(itemListener);
    }

    private AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            City selectedCity = (City) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            setIntent(intent, selectedCity);
        }
    };

    private void setIntent(Intent intent, City city) {
        intent.putExtra(CITY, city.getCity());
        intent.putExtra(WIND, wind.isChecked());
        intent.putExtra(HUMIDITY, humidity.isChecked());
        intent.putExtra(PRESSURE, pressure.isChecked());
        intent.putExtra(THEME, getThemeId());
        startActivity(intent);
    }

    private void setInitialData() {
        cities.add(new City("Московская область", "Москва", R.drawable.moscow));
        cities.add(new City("Московская область", "Коломна", R.drawable.colomna));
    }
}
