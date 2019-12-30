package com.example.navigationbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyCartFragment extends Fragment {
    protected Button BtnOderPlace;
    private DatabaseReference db;
    private RecyclerView rv_mycart;
    private List<Cart_itemInfo> mData;
    TextView totalPrice_tv;
    TextView noOrder;

    public static String totalPrice_string = String.valueOf(Add_to_Cart.totalPrice_int);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycart, container, false);


        db = FirebaseDatabase.getInstance().getReference("Cart");
        db.keepSynced(true);

        rv_mycart = view.findViewById(R.id.rv_item_mycart);
        rv_mycart.setHasFixedSize(true);
        noOrder = view.findViewById(R.id.noOrderText);

        totalPrice_tv = view.findViewById(R.id.total_amount);
        totalPrice_tv.setText(totalPrice_string);

        rv_mycart.setLayoutManager(new LinearLayoutManager(getActivity()));

        BtnOderPlace = (Button) view.findViewById(R.id.placeOrberBtn);

        BtnOderPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Add_to_Cart.totalPrice_int = 0;
                db.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Order Placed!", Toast.LENGTH_SHORT).show();
                            noOrder.setText("Cart is Empty!");
                            totalPrice_tv.setText("N/A");
                        } else {
                            Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }


    //This method runs every time you open this activity so the recyclerview gets refresh
    @Override
    public void onStart() {
        super.onStart();
        totalPrice_tv.setText(String.valueOf(totalPrice_string));
        FirebaseRecyclerAdapter<Cart_itemInfo, Cart_itemInfo_viewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cart_itemInfo,
                Cart_itemInfo_viewHolder>(Cart_itemInfo.class, R.layout.item_in_mycart, Cart_itemInfo_viewHolder.class, db) {
            @Override
            protected void populateViewHolder(Cart_itemInfo_viewHolder viewHolder, Cart_itemInfo model, int position) {
                viewHolder.setItemName(model.getItemName());
                viewHolder.setItemQty(model.getItemQty());
                viewHolder.setItemPrice(model.getItemPrice());

            }
        };

        //Setting the adapter of RecyclerView
        rv_mycart.setAdapter(firebaseRecyclerAdapter);
    }


    //This viewHolder is acting as an Adapter
    public static class Cart_itemInfo_viewHolder extends RecyclerView.ViewHolder {
        View mView;

        public Cart_itemInfo_viewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setItemName(String itemN){
            TextView itemName_CartList = mView.findViewById(R.id.itemname_cartList);
            itemName_CartList.setText(itemN);
        }
        public void setItemQty(String itemQ){
            TextView itemQty_CartList = mView.findViewById(R.id.itemQty_cartList);
            itemQty_CartList.setText(itemQ);
        }
        public void setItemPrice(String itemP){
            TextView itemPrice_CartList = mView.findViewById(R.id.itemPrice_cartList);
            itemPrice_CartList.setText(itemP);
        }
    }
}



