package com.isever.sergn.homeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.isever.sergn.homeproject.controllers.CityAdapter;
import com.isever.sergn.homeproject.resource.City;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<City> cities = new ArrayList<>();

    ListView countriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
