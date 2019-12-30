package com.example.navigationbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class DealsFragment extends Fragment {

    ListView deals_listview;
    String[] deals_itemnames;
    String[] deals_price;
    String[] deals_description;
    Integer[] deals_images = {R.drawable.zingeroffer, R.drawable.chickenkarhai, R.drawable.bff, R.drawable.chinesetoday
            , R.drawable.italiantaste, R.drawable.bandofburger, R.drawable.chickenlover};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deals, container, false);

        Resources res = getResources();

        deals_listview = (ListView) view.findViewById(R.id.deals_listview);
        deals_itemnames = res.getStringArray(R.array.deals_items);
        deals_price = res.getStringArray(R.array.deals_price);
        deals_description = res.getStringArray(R.array.deals_description);


        Adapter_Today_Deals adapterTodayspecial = new Adapter_Today_Deals(getActivity(), deals_itemnames,
                deals_description, deals_price, deals_images);

        deals_listview.setAdapter(adapterTodayspecial);
        deals_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Add_to_Cart.class);
                intent.putExtra("com.example.navigationbar.DEALS_ITEM_INDEX", position);
                startActivity(intent);
            }
        });


        return view;

    }

}

