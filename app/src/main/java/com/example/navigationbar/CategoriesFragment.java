package com.example.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CategoriesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_categories, container, false);

        Button fastfoodBtn = (Button) view.findViewById(R.id.BtnFastFood);
        Button desifoodBtn = (Button) view.findViewById(R.id.BtnDesi);
        Button italianfoodBtn = (Button) view.findViewById(R.id.BtnItalian);
        Button chinesefoodBtn = (Button) view.findViewById(R.id.BtnChinese);
        Button icecreamBtn = (Button) view.findViewById(R.id.BtnIceCreams);
        Button drinksBtn = (Button) view.findViewById(R.id.BtnDrinks);


        fastfoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fastfoodIntent = new Intent(getActivity(),
                        FastFood.class);
                startActivity(fastfoodIntent);
            }
        });

        desifoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent desiIntent = new Intent(getActivity(),
                        Desi.class);
                startActivity(desiIntent);
            }
        });

        italianfoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent italianIntent = new Intent(getActivity(),
                        ItalianFood.class);
                startActivity(italianIntent);
            }
        });

        chinesefoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chineseIntent = new Intent(getActivity(),
                        ChineseFood.class);
                startActivity(chineseIntent);
            }
        });

        icecreamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icecreamIntent = new Intent(getActivity(),
                        IceCream.class);
                startActivity(icecreamIntent);
            }
        });

        drinksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drinksIntent = new Intent(getActivity(),
                        Drinks.class);
                startActivity(drinksIntent);
            }
        });



        return view;
    }

}
