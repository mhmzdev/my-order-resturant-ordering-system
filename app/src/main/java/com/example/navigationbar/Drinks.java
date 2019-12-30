package com.example.navigationbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Drinks extends AppCompatActivity {
    private Button hotdrinks;
    private Button softdrinks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hotdrinks = (Button) findViewById(R.id.hot_drinks_button);
        softdrinks = (Button) findViewById(R.id.soft_drinks_button);

        hotdrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotdrinks();
            }
        });

        softdrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                softdrinks();
            }
        });
    }

    public void hotdrinks(){
        Intent intent = new Intent(this, HotDrinks.class);
        startActivity(intent);
    }

    public void softdrinks(){
        Intent intent = new Intent(this, SoftDrinks.class);
        startActivity(intent);
    }
}
