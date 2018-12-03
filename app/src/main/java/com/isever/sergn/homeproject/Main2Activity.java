package com.isever.sergn.homeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String needCity = intent.getStringExtra("CITY");

        TextView textView = findViewById(R.id.textCity);
        textView.setText(needCity);

        ImageView imageView = findViewById(R.id.imageWeather);
        imageView.setImageResource(R.drawable.test);


    }
}
