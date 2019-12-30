package com.example.navigationbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ItalianFood extends AppCompatActivity {

    RecyclerView italian_rv;
    All_Adapter italian_alladapter;
    List<MenuItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_italian_food);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        italian_rv = findViewById(R.id.italian_rv);
        mData = new ArrayList<>();

        mData.add(new MenuItem("Carbonara", "Full and Half Bowl", "Rs 150", R.drawable.carbonara));
        mData.add(new MenuItem("Italian Salad", "Full Plate", "Rs 100", R.drawable.italian_salad));
        mData.add(new MenuItem("Lasagna", "Buy 2 get 1 Free", "Rs 250", R.drawable.lasagna_italian));
        mData.add(new MenuItem("Mushroom Risotto", "Full and Half Bowl", "Rs 200", R.drawable.mushroom_risotto));
        mData.add(new MenuItem("Penne Pasta", "Full and Half Bowl", "Rs 600", R.drawable.penne_pasta));

        italian_alladapter = new All_Adapter(this, mData);
        italian_rv.setAdapter(italian_alladapter);
        italian_rv.setLayoutManager(new LinearLayoutManager(this));

        italian_alladapter.setOnItemClickListener(new All_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.ITALIAN_ITEM_INDEX", position);
                startActivity(intent);
            }
        });



    }
}
