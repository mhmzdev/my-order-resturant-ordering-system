package com.example.navigationbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SoftDrinks extends AppCompatActivity {


    RecyclerView softdrinks_rv;
    All_Adapter sd_alladapter;
    List<MenuItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softdrinks);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        softdrinks_rv = findViewById(R.id.softdrinks_rv);
        mData = new ArrayList<>();


        mData.add(new MenuItem("Coke", "Regular, 1.5 Lts, 2.25 Ltr", "-", R.drawable.coke));
        mData.add(new MenuItem("Pepsi", "Regular, 1.5 Lts, 2.25 Ltr", "-", R.drawable.pepsi));
        mData.add(new MenuItem("Sprite", "Regular, 1.5 Lts, 2.25 Ltr", "-", R.drawable.sprite));
        mData.add(new MenuItem("Fanta", "Regular, 1.5 Lts, 2.25 Ltr", "-", R.drawable.fanta));
        mData.add(new MenuItem("Dew", "Regular, 1.5 Lts, 2.25 Ltr", "-", R.drawable.dew));


        sd_alladapter = new All_Adapter(this, mData);
        softdrinks_rv.setAdapter(sd_alladapter);
        softdrinks_rv.setLayoutManager(new LinearLayoutManager(this));

        sd_alladapter.setOnItemClickListener(new All_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Add_to_Cart_SoftDrinks.class);
                intent.putExtra("com.example.navigationbar.SOFT_DRINKS_ITEMS_INDEX", position);
                startActivity(intent);
            }
        });

    }
}
