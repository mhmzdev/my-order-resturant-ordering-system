package com.example.navigationbar;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_to_Cart extends AppCompatActivity {
    TextView qty_counter;
    int counter = 1;

    public static int totalPrice_int;

    String[] fastfood_items; String[] fastfood_description; String[] fastfood_price;
    String[] desi_items; String[] desi_description;String[] desi_price;
    String[] italian_items;String[] italian_description;String[] italian_price;
    String[] chinese_items;String[] chinese_description;String[] chinese_price;
    String[] hotdrinks_items;String[] hotdrinks_description;String[] hotdrinks_price;
    String[] deals_items;String[] deals_description;String[] deals_price;
    String[] today_items;String[] today_description;String[] today_price;
    String[] ice_items;String[] ice_description;String[] ice_price;

    private DatabaseReference db;
    private int index,desi_index,italian_index,chinese_index,hotdrinks_index,deals_index,today_index,ice_index;
    TextView atc_name;
    TextView atc_disc;
    TextView atc_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_to__cart);

        //Giving Reference from FireBase
        db = FirebaseDatabase.getInstance().getReference().child("Cart");

        //Finding all the TextViews
        atc_name = findViewById(R.id.atc_itemName);
        atc_disc = findViewById(R.id.atc_description);
        atc_price = findViewById(R.id.atc_price);
        qty_counter = findViewById(R.id.qty_counter);


        FloatingActionButton btnCart = findViewById(R.id.atc_button);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int price = Integer.parseInt(atc_price.getText().toString());
                int qnt = Integer.parseInt(qty_counter.getText().toString());

                //Getting Data from TextViews
                String itemName = atc_name.getText().toString();
                String itemQuantity = qty_counter.getText().toString();
                final String itemPrice = String.valueOf(price * qnt);

                //Uploading to Firebase
                final Cart_itemInfo c = new Cart_itemInfo(itemName, itemPrice, itemQuantity);

                db.child(String.valueOf(index)).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Add_to_Cart.this, "Added In Cart!", Toast.LENGTH_SHORT).show();
                            totalPrice_int = totalPrice_int + Integer.valueOf(itemPrice);
                            isFinishing();
                        } else {
                            Toast.makeText(Add_to_Cart.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //For Finishing the Activity
                finish();
            }
        });

        Intent in = getIntent();
        index = in.getIntExtra("com.example.navigationbar.ITEM_INDEX", -1);
        desi_index = in.getIntExtra("com.example.navigationbar.DESI_ITEM_INDEX", -1);
        italian_index = in.getIntExtra("com.example.navigationbar.ITALIAN_ITEM_INDEX", -1);
        chinese_index = in.getIntExtra("com.example.navigationbar.CHINESE_ITEM_INDEX", -1);
        hotdrinks_index = in.getIntExtra("com.example.navigationbar.HOT_ITEM_INDEX", -1);
        deals_index = in.getIntExtra("com.example.navigationbar.DEALS_ITEM_INDEX", -1);
        today_index = in.getIntExtra("com.example.navigationbar.TODAY_ITEM_INDEX", -1);
        ice_index = in.getIntExtra("com.example.navigationbar.ICE_ITEM_INDEX", -1);

        if (index == getIntent().getIntExtra("com.example.navigationbar.ITEM_INDEX", -1)) {
            if (index > -1) {
                InfoAdapter pic = getImage(index);
                ImageView img = (ImageView) findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }
        }
        if (desi_index == getIntent().getIntExtra("com.example.navigationbar.DESI_ITEM_INDEX", -1)) {
            if (desi_index > -1) {
                InfoAdapter pic = getImage_desi(desi_index);
                ImageView img = (ImageView) findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }
        }
        if (italian_index == getIntent().getIntExtra("com.example.navigationbar.ITALIAN_ITEM_INDEX", -1)) {
            if (italian_index > -1) {
                InfoAdapter pic = getImage_italian(italian_index);
                ImageView img = (ImageView) findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }

        }
        if (chinese_index == getIntent().getIntExtra("com.example.navigationbar.CHINESE_ITEM_INDEX", -1)) {
            if (chinese_index > -1) {
                InfoAdapter pic = getImage_chinese(chinese_index);
                ImageView img = (ImageView) findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }
        }
        if (hotdrinks_index == getIntent().getIntExtra("com.example.navigationbar.HOT_ITEM_INDEX", -1)) {
            if (hotdrinks_index > -1) {
                InfoAdapter pic = getImage_hotdrinks(hotdrinks_index);
                ImageView img = findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }
        }

        if (deals_index == getIntent().getIntExtra("com.example.navigationbar.DEALS_ITEM_INDEX", -1)) {
            if (deals_index > -1) {
                InfoAdapter pic = getImage_deals(deals_index);
                ImageView img = findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }
        }
        if (today_index == getIntent().getIntExtra("com.example.navigationbar.TODAY_ITEM_INDEX", -1)) {
            if (today_index > -1) {
                InfoAdapter pic = getImage_today(today_index);
                ImageView img = findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }
        }
        if (ice_index == getIntent().getIntExtra("com.example.navigationbar.ICE_ITEM_INDEX", -1)) {
            if (ice_index > -1) {
                InfoAdapter pic = getImage_ice(ice_index);
                ImageView img = findViewById(R.id.atc_image);
                scaleImage(img, pic);
            }
        }

    }

    public void countInc(View v) {
        counter++;
        qty_counter.setText(Integer.toString(counter));
    }

    public void countDec(View v) {
        counter--;
        if (counter <= 0) {
            counter = 1;
        }
        qty_counter.setText(Integer.toString(counter));
    }

    private InfoAdapter getImage(int index) {

        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        fastfood_items = res.getStringArray(R.array.fastfood_items);
        fastfood_description = res.getStringArray(R.array.fastfood_description);
        fastfood_price = res.getStringArray(R.array.fastfood_price);

        switch (index) {
            case 0:
                info.setPrice(fastfood_price[0]);
                info.setName(fastfood_items[0]);
                info.setDisp(fastfood_description[0]);
                info.setID(R.drawable.zinger);
                return info;

            case 1:
                info.setPrice(fastfood_price[1]);
                info.setName(fastfood_items[1]);
                info.setDisp(fastfood_description[1]);
                info.setID(R.drawable.chicken);
                return info;


            case 2:

                info.setPrice(fastfood_price[2]);
                info.setName(fastfood_items[2]);
                info.setDisp(fastfood_description[2]);
                info.setID(R.drawable.crunchburger);
                return info;
            case 3:

                info.setPrice(fastfood_price[3]);
                info.setName(fastfood_items[3]);
                info.setDisp(fastfood_description[3]);
                info.setID(R.drawable.wings);
                return info;


            case 4:

                info.setPrice(fastfood_price[4]);
                info.setName(fastfood_items[4]);
                info.setDisp(fastfood_description[4]);
                info.setID(R.drawable.rollparatha);
                return info;

            case 5:

                info.setPrice(fastfood_price[5]);
                info.setName(fastfood_items[5]);
                info.setDisp(fastfood_description[5]);
                info.setID(R.drawable.shuwarma);
                return info;

            default:
                return info;

        }
    }

    private InfoAdapter getImage_ice(int ice_index) {
        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        ice_items = res.getStringArray(R.array.icecream_items);
        ice_description = res.getStringArray(R.array.icecream_description);
        ice_price = res.getStringArray(R.array.icecream_price);

        switch (ice_index) {
            case 0:
                info.setPrice(ice_price[0]);
                info.setName(ice_items[0]);
                info.setDisp(ice_description[0]);
                info.setID(R.drawable.chocolate);
                return info;

            case 1:
                info.setPrice(ice_price[1]);
                info.setName(ice_items[1]);
                info.setDisp(ice_description[1]);
                info.setID(R.drawable.min_chocolate_chip);
                return info;

            case 2:
                info.setPrice(ice_price[2]);
                info.setName(ice_items[2]);
                info.setDisp(ice_description[2]);
                info.setID(R.drawable.tutti_fruti);
                return info;

            case 3:
                info.setPrice(ice_price[4]);
                info.setName(ice_items[4]);
                info.setDisp(ice_description[4]);
                info.setID(R.drawable.vanilla);
                return info;

            default:
                return info;

        }
    }

    private InfoAdapter getImage_desi(int desi_index) {
        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        desi_items = res.getStringArray(R.array.desifood_items);
        desi_description = res.getStringArray(R.array.desifood_description);
        desi_price = res.getStringArray(R.array.desifood_price);
        switch (desi_index) {
            case 0:
                info.setName(desi_items[0]);
                info.setDisp(desi_description[0]);
                info.setPrice(desi_price[0]);
                info.setID(R.drawable.chickenkarhai);
                return info;
            //return R.drawable.chickenkarhai;
            case 1:
                info.setName(desi_items[1]);
                info.setDisp(desi_description[1]);
                info.setPrice(desi_price[1]);
                info.setID(R.drawable.tikka_karhai);
                return info;
            //return R.drawable.tikka_karhai;
            case 2:
                info.setName(desi_items[2]);
                info.setDisp(desi_description[2]);
                info.setPrice(desi_price[2]);
                info.setID(R.drawable.daal_mash);
                return info;
            //return R.drawable.daal_mash;
            case 3:
                info.setName(desi_items[3]);
                info.setDisp(desi_description[3]);
                info.setPrice(desi_price[3]);
                info.setID(R.drawable.daal_channa);
                return info;
            //return R.drawable.daal_channa;
            case 4:
                info.setName(desi_items[4]);
                info.setDisp(desi_description[4]);
                info.setPrice(desi_price[4]);
                info.setID(R.drawable.fry_fish);
                return info;
            //return R.drawable.fry_fish;
            case 5:
                info.setName(desi_items[5]);
                info.setDisp(desi_description[5]);
                info.setPrice(desi_price[5]);
                info.setID(R.drawable.sajji);
                return info;
            //return R.drawable.sajji;
            default:
                return info;
            //return -1;
        }
    }

    private InfoAdapter getImage_italian(int italian_index) {

        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        italian_items = res.getStringArray(R.array.italian_items);
        italian_description = res.getStringArray(R.array.italian_description);
        italian_price = res.getStringArray(R.array.italian_price);

        switch (italian_index) {
            case 0:
                info.setName(italian_items[0]);
                info.setDisp(italian_description[0]);
                info.setPrice(italian_price[0]);
                info.setID(R.drawable.carbonara);
                return info;
            case 1:
                info.setName(italian_items[1]);
                info.setDisp(italian_description[1]);
                info.setPrice(italian_price[1]);
                info.setID(R.drawable.italian_salad);
                return info;
            case 2:
                info.setName(italian_items[2]);
                info.setDisp(italian_description[2]);
                info.setPrice(italian_price[2]);
                info.setID(R.drawable.lasagna_italian);
                return info;
            case 3:
                info.setName(italian_items[3]);
                info.setDisp(italian_description[3]);
                info.setPrice(italian_price[3]);
                info.setID(R.drawable.mushroom_risotto);
                return info;
            case 4:
                info.setName(italian_items[4]);
                info.setDisp(italian_description[4]);
                info.setPrice(italian_price[4]);
                info.setID(R.drawable.penne_pasta);
                return info;
            default:
                return info;
        }
    }

    private InfoAdapter getImage_chinese(int chinese_index) {
        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        chinese_items = res.getStringArray(R.array.chinesefood_items);
        chinese_description = res.getStringArray(R.array.chinese_description);
        chinese_price = res.getStringArray(R.array.chinese_price);

        switch (chinese_index) {
            case 0:
                info.setName(chinese_items[0]);
                info.setDisp(chinese_description[0]);
                info.setPrice(chinese_price[0]);
                info.setID(R.drawable.dumpling);
                return info;
            case 1:
                info.setName(chinese_items[1]);
                info.setDisp(chinese_description[1]);
                info.setPrice(chinese_price[1]);
                info.setID(R.drawable.kung_pao_chicken);
                return info;
            case 2:
                info.setName(chinese_items[2]);
                info.setDisp(chinese_description[2]);
                info.setPrice(chinese_price[2]);
                info.setID(R.drawable.mapo_tofu);
                return info;
            case 3:
                info.setName(chinese_items[3]);
                info.setDisp(chinese_description[3]);
                info.setPrice(chinese_price[3]);
                info.setID(R.drawable.spring_rolls);
                return info;
            case 4:
                info.setName(chinese_items[4]);
                info.setDisp(chinese_description[4]);
                info.setPrice(chinese_price[4]);
                info.setID(R.drawable.wonton_soup);
                return info;
            default:
                return info;
        }
    }

    private InfoAdapter getImage_hotdrinks(int hotdrinks_index) {
        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        hotdrinks_items = res.getStringArray(R.array.hot_drink_items_array);
        hotdrinks_description = res.getStringArray(R.array.hot_drink_decription_array);
        hotdrinks_price = res.getStringArray(R.array.hot_drink_price_array);

        switch (hotdrinks_index) {
            case 0:
                info.setName(hotdrinks_items[0]);
                info.setDisp(hotdrinks_description[0]);
                info.setPrice(hotdrinks_price[0]);
                info.setID(R.drawable.tea);
                return info;
            case 1:
                info.setName(hotdrinks_items[1]);
                info.setDisp(hotdrinks_description[1]);
                info.setPrice(hotdrinks_price[1]);
                info.setID(R.drawable.coffee);
                return info;
            case 2:
                info.setName(hotdrinks_items[2]);
                info.setDisp(hotdrinks_description[2]);
                info.setPrice(hotdrinks_price[2]);
                info.setID(R.drawable.cappuccino);
                return info;
            case 3:
                info.setName(hotdrinks_items[3]);
                info.setDisp(hotdrinks_description[3]);
                info.setPrice(hotdrinks_price[3]);
                info.setID(R.drawable.green_tea);
                return info;

            default:
                return info;
        }
    }

    private InfoAdapter getImage_deals(int deals_index) {
        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        deals_items = res.getStringArray(R.array.deals_items);
        deals_description = res.getStringArray(R.array.deals_description);
        deals_price = res.getStringArray(R.array.deals_price);

        switch (deals_index) {
            case 0:
                info.setPrice(deals_price[0]);
                info.setName(deals_items[0]);
                info.setDisp(deals_description[0]);
                info.setID(R.drawable.fastfamily);
                return info;

            case 1:
                info.setPrice(deals_price[1]);
                info.setName(deals_items[1]);
                info.setDisp(deals_description[1]);
                info.setID(R.drawable.chickenkarhai);
                return info;

            case 2:
                info.setPrice(deals_price[2]);
                info.setName(deals_items[2]);
                info.setDisp(deals_description[2]);
                info.setID(R.drawable.bff);
                return info;

            case 3:

                info.setPrice(deals_price[3]);
                info.setName(deals_items[3]);
                info.setDisp(deals_description[3]);
                info.setID(R.drawable.chinesetoday);
                return info;


            case 4:

                info.setPrice(deals_price[4]);
                info.setName(deals_items[4]);
                info.setDisp(deals_description[4]);
                info.setID(R.drawable.italiantaste);
                return info;

            case 5:

                info.setPrice(deals_price[5]);
                info.setName(deals_items[5]);
                info.setDisp(deals_description[5]);
                info.setID(R.drawable.bandofburger);
                return info;

            case 6:
                info.setPrice(deals_price[6]);
                info.setName(deals_items[6]);
                info.setDisp(deals_description[6]);
                info.setID(R.drawable.chickenlover);
                return info;

            default:
                return info;
        }

    }

    private InfoAdapter getImage_today(int today_index) {
        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        today_items = res.getStringArray(R.array.todayspecial_items);
        today_description = res.getStringArray(R.array.todayspecial_description);
        today_price = res.getStringArray(R.array.todayspecial_price);

        switch (today_index) {
            case 0:
                info.setPrice(today_price[0]);
                info.setName(today_items[0]);
                info.setDisp(today_description[0]);
                info.setID(R.drawable.zingeroffer);
                return info;
            case 1:
                info.setPrice(today_price[1]);
                info.setName(today_items[1]);
                info.setDisp(today_description[1]);
                info.setID(R.drawable.familyfreedom);
                return info;
            case 2:
                info.setPrice(today_price[2]);
                info.setName(today_items[2]);
                info.setDisp(today_description[2]);
                info.setID(R.drawable.pizzaoffertody);
                return info;
            case 3:
                info.setPrice(today_price[3]);
                info.setName(today_items[3]);
                info.setDisp(today_description[3]);
                info.setID(R.drawable.chinesetoday);
                return info;

            default:
                return info;
        }
    }


    private void scaleImage(ImageView img, InfoAdapter info) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), info.getID(), options);

        int imagewidth = options.outWidth;
        int screenwidth = screen.getWidth();

        if (imagewidth > screenwidth) {
            int ratio = Math.round((float) imagewidth / (float) screenwidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaledImage = BitmapFactory.decodeResource(getResources(), info.getID(), options);
        img.setImageBitmap(scaledImage);


        atc_disc.setText(info.getDisp());
        atc_name.setText(info.getName());
        atc_price.setText(info.getPrice());


    }


}

