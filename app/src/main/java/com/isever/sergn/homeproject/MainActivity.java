package com.isever.sergn.homeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.isever.sergn.homeproject.controllers.CityAdapter;
import com.isever.sergn.homeproject.controllers.LifeCycleApp;
import com.isever.sergn.homeproject.resource.City;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String lifeCycleName = "";

    private ArrayList<City> cities = new ArrayList<>();

    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lifeCycleName = "onCreate";
        new LifeCycleApp(lifeCycleName, getApplicationContext());

        setInitialData();
        countriesList = findViewById(R.id.countriesList);
        CityAdapter cityAdapter = new CityAdapter(this, R.layout.list_item, cities);
        countriesList.setAdapter(cityAdapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                City selectedCity = (City)parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("CITY", selectedCity.getCity());
                startActivity(intent);
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }
    private void setInitialData(){
        cities.add(new City("Московская область", "Москва", R.drawable.moscow));
        cities.add(new City("Московская область", "Коломна", R.drawable.colomna));
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifeCycleName = "onStart";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lifeCycleName = "onRestoreInstanceState";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        lifeCycleName = "onRestart";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifeCycleName = "onResume";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifeCycleName = "onPause";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        lifeCycleName = "onSaveInstanceState";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifeCycleName = "onStop";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifeCycleName = "onDestroy";
        new LifeCycleApp(lifeCycleName, getApplicationContext());
    }
}
