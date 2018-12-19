package com.isever.sergn.homeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.isever.sergn.homeproject.controllers.MainMenu;

public class MainActivity extends AppCompatActivity {

    //Не смог придумать как иначе работать с меню. Написыл костыль
    public final static MainMenu mainMenu = new MainMenu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mainMenu.optionSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return mainMenu.createOption(menu);
    }
}
