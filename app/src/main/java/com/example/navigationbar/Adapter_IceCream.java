package com.example.navigationbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_IceCream extends BaseAdapter {

    LayoutInflater mInflater;
    String[] ice_items;
    String[] ice_description;
    String[] ice_price;
    Integer[] ice_images;

    public Adapter_IceCream(Context c, String[] ic_item, String[] ic_price, String[] ic_description, Integer[] ic_images) {
        ice_items = ic_item;
        ice_price = ic_price;
        ice_description = ic_description;
        ice_images = ic_images;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ice_items.length;
    }

    @Override
    public Object getItem(int position) {
        return ice_items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.fastfood_menu_layout, null);

        TextView ic_items_tv = (TextView) v.findViewById(R.id.ff_itemnameTextView);
        TextView ic_price_tv = (TextView) v.findViewById(R.id.ff_pricetextview);
        TextView ic_description_tv = (TextView) v.findViewById(R.id.ff_descriptionTextView);
        ImageView ic_images_iv = (ImageView) v.findViewById(R.id.itemPicture);

        String name = ice_items[position];
        String description = ice_description[position];
        String price = ice_price[position];
        Integer images = ice_images[position];

        ic_items_tv.setText(name);
        ic_price_tv.setText(price);
        ic_description_tv.setText(description);
        ic_images_iv.setImageResource(images);

        return v;
    }
}
