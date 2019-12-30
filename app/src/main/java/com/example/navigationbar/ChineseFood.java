package com.example.navigationbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChineseFood extends AppCompatActivity {

    RecyclerView chinese_rv;
    All_Adapter chinese_alladapter;
    List<MenuItem> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_food);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chinese_rv = findViewById(R.id.chinese_rv);
        mData = new ArrayList<>();

        mData.add(new MenuItem("Dumplings", "Pieces: 12", "Rs 500", R.drawable.dumpling));
        mData.add(new MenuItem("Kung Pao Chicken", "Single Bowl", "Rs 850", R.drawable.kung_pao_chicken));
        mData.add(new MenuItem("Mapo Tofu", "Free Drink Available!", "Rs 800", R.drawable.mapo_tofu));
        mData.add(new MenuItem("Spring Rolls", "Pieces: 6   Free 1 ltr Drink", "Rs 150", R.drawable.spring_rolls));
        mData.add(new MenuItem("Wonton Soup", "Buy 2 Get 1 Bowl Free", "Rs 800", R.drawable.wonton_soup));

        chinese_alladapter = new All_Adapter(this, mData);
        chinese_rv.setAdapter(chinese_alladapter);
        chinese_rv.setLayoutManager(new LinearLayoutManager(this));

        chinese_alladapter.setOnItemClickListener(new All_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.CHINESE_ITEM_INDEX", position);
                startActivity(intent);
            }
        });


    }
}
