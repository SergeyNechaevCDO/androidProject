package com.isever.sergn.homeproject.controllers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.isever.sergn.homeproject.R;

import java.util.Random;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;


public class WeatherActivity extends AppCompatActivity implements SensorEventListener {

    private final String TAG = "#WeatherActivity#";
    private SensorManager mSensorManager;
    private Sensor Temperature;
    private Sensor Humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Temperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        Humidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            WeatherFragment details = new WeatherFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }

    protected void onResume() {
        super.onResume();

        if (Temperature != null) {
            mSensorManager.registerListener(this, Temperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (Humidity != null) {
            mSensorManager.registerListener(this, Humidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    private void showLightSensors(SensorEvent event) {
        if (event.sensor.getStringType().equals("android.sensor.ambient_temperature")){
            TextView temperature = findViewById(R.id.main_temperature);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((int) event.values[0]).append("°");
            temperature.setText(stringBuilder);
        }else if(event.sensor.getStringType().equals("android.sensor.relative_humidity")){
            TextView humidity = findViewById(R.id.humidity_value);
            humidity.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        showLightSensors(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
