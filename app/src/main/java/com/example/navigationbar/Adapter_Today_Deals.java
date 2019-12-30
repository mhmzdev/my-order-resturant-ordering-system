package com.example.navigationbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_Today_Deals extends BaseAdapter {
    LayoutInflater mInflater;
    String[] ts_itemnames;
    String[] ts_description;
    String[] ts_price;
    Integer[] ts_images;

    public Adapter_Today_Deals(Context c, String[] today_items, String[] today_description, String[] today_price, Integer[] today_images) {
        ts_itemnames = today_items;
        ts_description = today_description;
        ts_price = today_price;
        ts_images = today_images;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ts_itemnames.length;
    }

    @Override
    public Object getItem(int position) {
        return ts_itemnames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.today_menu_layout, null);

        TextView ts_items_tv = (TextView) v.findViewById(R.id.ts_itemnameTextView);
        TextView ts_price_tv = (TextView) v.findViewById(R.id.ts_pricetextview);
        TextView ts_description_tv = (TextView) v.findViewById(R.id.ts_descriptionTextView);
        ImageView ts_image_view = v.findViewById(R.id.ts_itemPicture);

        String name = ts_itemnames[position];
        String description = ts_description[position];
        String price = ts_price[position];
        Integer images = ts_images[position];


        ts_items_tv.setText(name);
        ts_price_tv.setText(price);
        ts_description_tv.setText(description);
        ts_image_view.setImageResource(images);


        return v;
    }
}
