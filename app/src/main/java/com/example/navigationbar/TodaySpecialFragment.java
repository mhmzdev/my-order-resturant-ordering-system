package com.example.navigationbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class TodaySpecialFragment extends Fragment {

    RecyclerView todayspecial_rv;
    All_Adapter today_alladapter;
    List<MenuItem> mData;

    ListView ts_listview;
    String[] ts_itemnames;
    String[] ts_price;
    String[] ts_description;
    Integer[] ts_images = {R.drawable.zingeroffer, R.drawable.familyfreedom, R.drawable.pizzaoffertody,R.drawable.chinesetoday};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todayspecial, container, false);

        /*todayspecial_rv = view.findViewById(R.id.today_rv);
        mData = new ArrayList<>();

        mData.add(new MenuItem("Fast Family", "3 Pizza(M) + 1.5 Ltr + 3 Fries (S)", "Rs 180", R.drawable.zinger));
        mData.add(new MenuItem("Desi Family", "Buy 2 get 1 Free!", "Rs 120", R.drawable.chicken));
        mData.add(new MenuItem("BFF!", "Buy 2 get 1 Free!", "Rs 130", R.drawable.crunchburger));
        mData.add(new MenuItem("Chinese Friends", "12 Wings 1 Bucket", "Rs 760", R.drawable.wings));
        mData.add(new MenuItem("Italian Taste", "Non-Spicy Available", "Rs 100", R.drawable.rollparatha));
        mData.add(new MenuItem("Band of Burgers", "Non-Spicy Available", "Rs 110", R.drawable.shuwarma));
        mData.add(new MenuItem("Chicken lovers", "Non-Spicy Available", "Rs 110", R.drawable.shuwarma));

        today_alladapter = new All_Adapter(getActivity(), mData);
        todayspecial_rv.setAdapter(today_alladapter);
        todayspecial_rv.setLayoutManager(new LinearLayoutManager(getActivity()));*/

         Resources res = getResources();

        ts_listview = (ListView) view.findViewById(R.id.list_todayspecial);
        ts_itemnames = res.getStringArray(R.array.todayspecial_items);
        ts_price = res.getStringArray(R.array.todayspecial_price);
        ts_description = res.getStringArray(R.array.todayspecial_description);

        Adapter_Today_Deals adapterTodayspecial = new Adapter_Today_Deals(getActivity(), ts_itemnames,
                ts_description, ts_price, ts_images);

        ts_listview.setAdapter(adapterTodayspecial);

        ts_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.TODAY_ITEM_INDEX", position);
                startActivity(intent);
            }
        });


        return view;
    }
}
