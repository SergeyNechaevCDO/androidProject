package com.isever.sergn.homeproject.controllers;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.isever.sergn.homeproject.R;
import com.isever.sergn.homeproject.interfaces.GetWeather;
import com.isever.sergn.homeproject.model.WeatherRequest;
import com.isever.sergn.homeproject.service.MainService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;


public class WeatherActivity extends AppCompatActivity implements SensorEventListener {

    private final String TAG = WeatherActivity.class.getCanonicalName();
    private GetWeather getWeather;
    private SensorManager mSensorManager;
    private Sensor Temperature;
    private Sensor Humidity;
    private Intent Intent;
    private TextView _temperature;

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

        Intent = new Intent(WeatherActivity.this, MainService.class);

        if (savedInstanceState == null) {
            WeatherFragment details = new WeatherFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }

    protected void onResume() {
        super.onResume();
        _temperature = findViewById(R.id.main_temperature);
        String API_KEY = "7671dde5a1b56a106b0aa1c6f32ee93b";
        initRetrofit();
        requestRetrofit("Moscow", API_KEY);

        getImage("http://s1.iconbird.com/ico/2013/6/303/w512h5121371727566CloudIconPlainBlue.png", (ImageView) findViewById(R.id.mainImageWeather));
        getImage("https://cdn4.iconfinder.com/data/icons/climatic-equipment-flat-colorful/2048/7891_-_Humidity-512.png", (ImageView) findViewById(R.id.main_image_humidity));
        getImage("https://cdn1.iconfinder.com/data/icons/hawcons/32/699847-icon-43-wind-512.png", (ImageView) findViewById(R.id.main_image_wind));
        getImage("https://www.shareicon.net/download/2016/08/18/815394_medical_512x512.png", (ImageView) findViewById(R.id.main_image_pressure));
        startService(Intent);
        if (Temperature != null) {
            mSensorManager.registerListener(this, Temperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (Humidity != null) {
            mSensorManager.registerListener(this, Humidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void getImage(String url, ImageView imageView) {
        Picasso
                .get()
                .load(url)
                .into(imageView);
    }

    protected void onPause() {
        super.onPause();
        // Остановка сервиса
        stopService(Intent);
        mSensorManager.unregisterListener(this);
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getWeather = retrofit.create(GetWeather.class);
    }

    private void requestRetrofit(String city, String keyApi) {
        getWeather.loadWeather(city, keyApi)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherRequest> call,
                                           @NonNull Response<WeatherRequest> response) {
                        if (response.body() != null) {
                            int temp = (int) response.body().getMain().getTemp();
                            String far = String.valueOf(temp) + getString(R.string.temperature_faringate);
                            _temperature.setText(far);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherRequest> call,
                                          @NonNull Throwable throwable) {
                        Log.e(TAG, "request failed", throwable);
                    }
                });
    }


    private void showLightSensors(SensorEvent event) {
        if (event.sensor.getStringType().equals("android.sensor.ambient_temperature")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((int) event.values[0]).append("°");
        } else if (event.sensor.getStringType().equals("android.sensor.relative_humidity")) {
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
