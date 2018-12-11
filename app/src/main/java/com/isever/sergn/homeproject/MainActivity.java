package com.isever.sergn.homeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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


    private MenuItem wind;
    private MenuItem humidity;
    private MenuItem pressure;

    private ArrayList<City> cities = new ArrayList<>();

    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();
        createCountriesList();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_wind:
                if (item.isCheckable() && item.isChecked()){
                    wind.setChecked(false);
                    wind.setTitle(R.string.action_wind);
                }else{
                    wind.setChecked(true);
                    wind.setTitle(R.string.deactivate_wind);
                }
                return true;
            case R.id.action_humidity:
                if (item.isCheckable() && item.isChecked()){
                    humidity.setChecked(false);
                    humidity.setTitle(R.string.action_humidity);
                }else{
                    humidity.setChecked(true);
                    humidity.setTitle(R.string.deactivate_humidity);
                }
                return true;
            case R.id.action_pressure:
                if (item.isCheckable() && item.isChecked()){
                    pressure.setChecked(false);
                    pressure.setTitle(R.string.action_pressure);
                }else{
                    pressure.setChecked(true);
                    pressure.setTitle(R.string.deactivate_pressure);
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
        return true;
    }

    private void createCountriesList(){
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

    private void setIntent(Intent intent, City city){
        intent.putExtra(CITY, city.getCity());
        intent.putExtra(WIND, wind.isChecked());
        intent.putExtra(HUMIDITY, humidity.isChecked());
        intent.putExtra(PRESSURE, pressure.isChecked());
        startActivity(intent);
    }

    private void setInitialData() {
        cities.add(new City("Московская область", "Москва", R.drawable.moscow));
        cities.add(new City("Московская область", "Коломна", R.drawable.colomna));
    }
}
