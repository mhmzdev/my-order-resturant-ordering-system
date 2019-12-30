package com.example.navigationbar;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FastFood extends AppCompatActivity {

    RecyclerView fastfood_rv;
    All_Adapter ff_alladapter;
    List<MenuItem> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_fast_food);

        fastfood_rv = findViewById(R.id.fastfood_rv);
        mData = new ArrayList<>();

        mData.add(new MenuItem("Zinger Burger", "Buy 2 get 1 Free!", "Rs 180", R.drawable.zinger));
        mData.add(new MenuItem("Chicken Burger", "Buy 2 get 1 Free!", "Rs 120", R.drawable.chicken));
        mData.add(new MenuItem("Crunch Burger", "Buy 2 get 1 Free!", "Rs 130", R.drawable.crunchburger));
        mData.add(new MenuItem("Wings", "12 Wings 1 Bucket", "Rs 760", R.drawable.wings));
        mData.add(new MenuItem("Shuwarma", "Non-Spicy Available", "Rs 100", R.drawable.shuwarma));
        mData.add(new MenuItem("Roll Paratha", "Non-Spicy Available", "Rs 110", R.drawable.rollparatha));

        ff_alladapter = new All_Adapter(this, mData);
        fastfood_rv.setAdapter(ff_alladapter);
        fastfood_rv.setLayoutManager(new LinearLayoutManager(this));

        ff_alladapter.setOnItemClickListener(new All_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.ITEM_INDEX", position);
                startActivity(intent);
            }
        });


    }
}
