package com.example.navigationbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HotDrinks extends AppCompatActivity {
    RecyclerView hotdrinks_rv;
    All_Adapter hd_alladapter;
    List<MenuItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_drinks);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hotdrinks_rv = findViewById(R.id.hotdrinks_rv);
        mData = new ArrayList<>();

        mData.add(new MenuItem("Tea", "Regular Cup : Non-Sugar Available", "Rs 30", R.drawable.tea));
        mData.add(new MenuItem("Coffee", "Regular Cup : Non-Sugar Available", "Rs 40", R.drawable.coffee));
        mData.add(new MenuItem("Cappuccino", "Regular Cup : Non-Sugar Available", "Rs 50", R.drawable.cappuccino));
        mData.add(new MenuItem("Green Tea", "Regular Cup : Non-Sugar Available", "Rs 40", R.drawable.green_tea));


        hd_alladapter = new All_Adapter(this, mData);
        hotdrinks_rv.setAdapter(hd_alladapter);
        hotdrinks_rv.setLayoutManager(new LinearLayoutManager(this));

        hd_alladapter.setOnItemClickListener(new All_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.HOT_ITEM_INDEX", position);
                startActivity(intent);
            }
        });

    }
}
