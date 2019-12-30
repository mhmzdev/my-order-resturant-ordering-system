package com.example.navigationbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_SoftDrinks extends BaseAdapter {
    LayoutInflater mInflater;
    String[] sd_items;
    String[] sd_description;
    String[] sd_price;
    Integer[] sd_images;

    Context context;

    public Adapter_SoftDrinks(Context c, String[] softdrinks_item, String[] softdrinks_description, String[] softdrinks_price,
                              Integer[] softdrinks_images){
        sd_items = softdrinks_item;
        sd_description = softdrinks_description;
        sd_price = softdrinks_price;
        sd_images = softdrinks_images;
        context = c;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return sd_images.length;
    }

    @Override
    public Object getItem(int position) {
        return sd_items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.fastfood_menu_layout, null);

        TextView sd_items_tv = (TextView) v.findViewById(R.id.ff_itemnameTextView);
        TextView sd_description_tv = (TextView) v.findViewById(R.id.ff_descriptionTextView);
        TextView sd_price_tv = (TextView) v.findViewById(R.id.ff_pricetextview);
        ImageView sd_images_iv = (ImageView) v.findViewById(R.id.itemPicture);

        String name = sd_items[position];
        String description = sd_description[position];
        String price = sd_price[position];
        Integer images = sd_images[position];

        sd_items_tv.setText(name);
        sd_price_tv.setText(price);
        sd_description_tv.setText(description);
        sd_images_iv.setImageResource(images);

        return v;
    }
}
