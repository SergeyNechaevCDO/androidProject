package com.isever.sergn.homeproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.isever.sergn.homeproject.MainActivity.CITY;
import static com.isever.sergn.homeproject.MainActivity.HUMIDITY;
import static com.isever.sergn.homeproject.MainActivity.PRESSURE;
import static com.isever.sergn.homeproject.MainActivity.THEME;
import static com.isever.sergn.homeproject.MainActivity.WIND;



public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(getIntent().getIntExtra(THEME, R.style.AppTheme));
        setContentView(R.layout.activity_main2);
        buildLayout();
    }

    private void buildLayout() {

        Intent intent = getIntent();
        checkVisibility(intent);
        editMainLayoutWeather();
    }

    private void checkVisibility(Intent intent){

        String needCity = intent.getStringExtra(CITY);
        boolean wind = intent.getBooleanExtra(WIND, false);
        boolean humidity = intent.getBooleanExtra(HUMIDITY, false);
        boolean pressure = intent.getBooleanExtra(PRESSURE, false);

        if (wind){
            findViewById(R.id.wind).setVisibility(View.VISIBLE);
        }
        if (humidity){
            findViewById(R.id.humidity).setVisibility(View.VISIBLE);
        }
        if (pressure){
            findViewById(R.id.pressure).setVisibility(View.VISIBLE);
        }
        TextView textView = findViewById(R.id.main_city);
        textView.setText(needCity);

    }

    private void editMainLayoutWeather() {

        Drawable drawable;
        Drawable main_drawable;

        TextView textViewInfo = findViewById(R.id.main_text_info);
        TextView textViewTemper = findViewById(R.id.main_temperature);

        switch (Random()) {
            case 2:
                drawable = ContextCompat.getDrawable(this, R.drawable.snow);
                main_drawable = ContextCompat.getDrawable(this, R.drawable.main_snow);
                textViewInfo.setText(R.string.param_snow_text);
                textViewTemper.setText(R.string.temperature_snow);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(this, R.drawable.sunny);
                main_drawable = ContextCompat.getDrawable(this, R.drawable.main_wind);
                textViewInfo.setText(R.string.param_cloudless_text);
                textViewTemper.setText(R.string.temperature);
                break;
            default:
                drawable = ContextCompat.getDrawable(this, R.drawable.rain);
                main_drawable = ContextCompat.getDrawable(this, R.drawable.main_wind);
                textViewInfo.setText(R.string.param_cloudless_text);
                textViewTemper.setText(R.string.temperature);
                break;
        }
        ImageView imageView = findViewById(R.id.mainImageWeather);
        LinearLayout main_layout = findViewById(R.id.main_back_layout);
        if (drawable != null) {
            imageView.setImageDrawable(drawable.getCurrent());
        }
        if (main_drawable != null) {
            main_layout.setBackground(main_drawable.getCurrent());
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm");

        EditText dateTime = findViewById(R.id.main_date);
        dateTime.setText(formatForDateNow.format(new Date()));

    }

    private int Random() {
        int min = 1;
        int max = 3;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }
}
