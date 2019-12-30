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

public class Desi extends AppCompatActivity {

    RecyclerView desi_rv;
    All_Adapter desi_alladapter;
    List<MenuItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desi);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        desi_rv = findViewById(R.id.desi_rv);
        mData = new ArrayList<>();

        mData.add(new MenuItem("Chicken Karahi", "Full Only 1 KG", "Rs 800", R.drawable.chickenkarhai));
        mData.add(new MenuItem("Tikka Karahi", "Full Only 1 KG", "Rs 800", R.drawable.tikka_karhai));
        mData.add(new MenuItem("Daal Mash", "Full Plate", "Rs 80", R.drawable.daal_mash));
        mData.add(new MenuItem("Daal Channa", "Full Plate", "Rs 70", R.drawable.daal_channa));
        mData.add(new MenuItem("Desi Fry Fish", "Full 1 KG Karahi", "Rs 850", R.drawable.fry_fish));
        mData.add(new MenuItem("Sajji", "Full Sajji", "Rs 450", R.drawable.sajji));

        desi_alladapter = new All_Adapter(this, mData);
        desi_rv.setAdapter(desi_alladapter);
        desi_rv.setLayoutManager(new LinearLayoutManager(this));

        desi_alladapter.setOnItemClickListener(new All_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.DESI_ITEM_INDEX", position);
                startActivity(intent);
            }
        });

    }
}
