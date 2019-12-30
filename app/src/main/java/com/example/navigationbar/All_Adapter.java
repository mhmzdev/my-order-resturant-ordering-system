package com.example.navigationbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class All_Adapter extends RecyclerView.Adapter<All_Adapter.All_itemViewHolder> {

    Context mContext;
    List<MenuItem> mData;


    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public All_Adapter(Context c, List<MenuItem> mData) {
        this.mContext = c;
        this.mData = mData;
    }

    @NonNull
    @Override
    public All_itemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.fastfood_menu_layout, viewGroup, false);
        return new All_itemViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull All_itemViewHolder all_itemViewHolder, int i) {
        all_itemViewHolder.tv_itemName.setText(mData.get(i).getName());
        all_itemViewHolder.tv_itemDescription.setText(mData.get(i).getDescription());
        all_itemViewHolder.tv_itemPrice.setText(mData.get(i).Price);
        all_itemViewHolder.itemImage.setImageResource(mData.get(i).getImage());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class All_itemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_itemName, tv_itemDescription, tv_itemPrice;
        ImageView itemImage;


        public All_itemViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_itemName = itemView.findViewById(R.id.ff_itemnameTextView);
            tv_itemDescription = itemView.findViewById(R.id.ff_descriptionTextView);
            tv_itemPrice = itemView.findViewById(R.id.ff_pricetextview);
            itemImage = itemView.findViewById(R.id.itemPicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    mListener.onItemClick(pos);
                }
            });
        }
    }
}