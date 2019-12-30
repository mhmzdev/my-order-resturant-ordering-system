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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_to_Cart_SoftDrinks extends AppCompatActivity {
    TextView qty_sd_counter;
    int counter = 1;
    RadioGroup radioGroup_soft;
    RadioButton radioButton_soft;

    private DatabaseReference db_soft;
    private int softdrinks_index;

    String[] soft_drink_items;
    String[] soft_drink_description;
    String[] soft_drink_price;
    TextView atc_sd_name;
    TextView atc_sd_disc;
    TextView atc_sd_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_to_cart_softdrinks);

        radioGroup_soft = findViewById(R.id.radiogroup_sd);
        qty_sd_counter = findViewById(R.id.qty_sd_counter);

        atc_sd_name = findViewById(R.id.atc_sd_itemName);
        atc_sd_disc = findViewById(R.id.atc_sd_description);
        atc_sd_price = findViewById(R.id.atc_sd_price);

        db_soft = FirebaseDatabase.getInstance().getReference().child("Cart");

        FloatingActionButton floatingActionButton =  findViewById(R.id.atc_sd_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price = Integer.parseInt(atc_sd_price.getText().toString());
                int qnt = Integer.parseInt(qty_sd_counter.getText().toString());
                final String total_Price = String.valueOf(price * qnt);

                String itemN = atc_sd_name.getText().toString();
                String itemQ = qty_sd_counter.getText().toString();
                String itemP = atc_sd_price.getText().toString();
                Cart_itemInfo c = new Cart_itemInfo(itemN, itemP, itemQ);

                Add_to_Cart.totalPrice_int =+ Integer.valueOf(total_Price);

                db_soft.child(String.valueOf(softdrinks_index)).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Add_to_Cart_SoftDrinks.this, "Added In Cart!", Toast.LENGTH_SHORT).show();
                            isFinishing();
                        } else {
                            Toast.makeText(Add_to_Cart_SoftDrinks.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                finish();
            }
        });

        Intent in = getIntent();
        softdrinks_index = in.getIntExtra("com.example.navigationbar.SOFT_DRINKS_ITEMS_INDEX", -1);

        if (softdrinks_index == getIntent().getIntExtra("com.example.navigationbar.SOFT_DRINKS_ITEMS_INDEX", -1)) {
            if (softdrinks_index > -1) {
                InfoAdapter pic = getImage_drinks(softdrinks_index);
                ImageView img =  findViewById(R.id.atc_sd_image);
                scaleImage(img, pic);
            }

        }
    }
    public void countInc(View v) {
        counter++;
        qty_sd_counter.setText(Integer.toString(counter));
    }
    public void countDec(View v) {
        counter--;
        if (counter <= 0) {
            counter = 1;
        }
        qty_sd_counter.setText(Integer.toString(counter));
    }
    private InfoAdapter getImage_drinks(int softdrinks_index) {
        InfoAdapter info = new InfoAdapter();
        Resources res = getResources();
        soft_drink_items = res.getStringArray(R.array.soft_drink_items);
        soft_drink_description = res.getStringArray(R.array.soft_drinks_description);
        soft_drink_price = res.getStringArray(R.array.soft_drinks_price);

        switch (softdrinks_index) {
            case 0:
                info.setName(soft_drink_items[0]);
                info.setDisp(soft_drink_description[0]);
                info.setPrice(soft_drink_price[0]);
                info.setID(R.drawable.coke);
                return info;

            case 1:
                info.setName(soft_drink_items[1]);
                info.setDisp(soft_drink_description[1]);
                info.setPrice(soft_drink_price[1]);
                info.setID(R.drawable.pepsi);
                return info;

            case 2:
                info.setName(soft_drink_items[2]);
                info.setDisp(soft_drink_description[2]);
                info.setPrice(soft_drink_price[2]);
                info.setID(R.drawable.sprite);
                return info;

            case 3:
                info.setName(soft_drink_items[3]);
                info.setDisp(soft_drink_description[3]);
                info.setPrice(soft_drink_price[3]);
                info.setID(R.drawable.fanta);
                return info;

            case 4:
                info.setName(soft_drink_items[4]);
                info.setDisp(soft_drink_description[4]);
                info.setPrice(soft_drink_price[4]);
                info.setID(R.drawable.dew);
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


        atc_sd_disc.setText(info.getDisp());
        atc_sd_name.setText(info.getName());
        atc_sd_price.setText(info.getPrice());



    }

    public void checkbutton(View v){
        int radioID = radioGroup_soft.getCheckedRadioButtonId();
        radioButton_soft = findViewById(radioID);
        String size = String.valueOf(radioButton_soft.getText());
        Toast.makeText(this, "Size: "+ radioButton_soft.getText(), Toast.LENGTH_SHORT).show();
        if (size.equalsIgnoreCase("Regular")){
            atc_sd_price.setText("30");
        } else if (size.equalsIgnoreCase("1.5 Ltr")){
            atc_sd_price.setText("75");
        } else if (size.equalsIgnoreCase("2.25 Ltr")){
            atc_sd_price.setText("95");
        }

    }
}
