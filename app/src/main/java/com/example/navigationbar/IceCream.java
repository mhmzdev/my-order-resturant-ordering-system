package com.example.navigationbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IceCream extends AppCompatActivity {

    RecyclerView ice_rv;
    All_Adapter ice_alladapter;
    List<MenuItem> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_ice_cream);

        ice_rv = findViewById(R.id.icecream_rv);
        mData = new ArrayList<>();

        mData.add(new MenuItem("Chocolate", "Buy 2 get 1 Free!", "-", R.drawable.chocolate));
        mData.add(new MenuItem("Mint Chocolate", "Buy 2 get 1 Free!", "-", R.drawable.min_chocolate_chip));
        mData.add(new MenuItem("Tutti Frutti", "Non-Spicy Available", "-", R.drawable.tutti_fruti));
        mData.add(new MenuItem("Vanilla", "Non-Spicy Available", "-", R.drawable.vanilla));

        ice_alladapter = new All_Adapter(this, mData);
        ice_rv.setAdapter(ice_alladapter);
        ice_rv.setLayoutManager(new LinearLayoutManager(this));

        ice_alladapter.setOnItemClickListener(new All_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.ICE_ITEM_INDEX", position);
                startActivity(intent);
            }
        });

    }
}
