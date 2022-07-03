package com.main.store.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.store.R;
import com.main.store.activities.DetailsActivity;
import com.main.store.models.ProductsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<ProductsResponse> listData;
    Context context;

    public RecyclerAdapter(List<ProductsResponse> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductsResponse productsResponse = listData.get(position);
        holder.tvTitle.setText(productsResponse.getTitle());
        holder.tvPrice.setText(productsResponse.getPrice());

        Picasso.get()
                .load(productsResponse.getImage())
                .resize(255,255)
                .centerCrop()
                .into(holder.imageView);

        holder.cardview_layout.setOnClickListener(v ->
        {
            Intent intent = new Intent(context, DetailsActivity.class);

            String stId = productsResponse.getId();
            String stTitle = productsResponse.getTitle();
            String stPrice = productsResponse.getPrice();
            String stDescription = productsResponse.getDescription();
            String stCategory = productsResponse.getCategory();
            String stImage = productsResponse.getImage();

            Bundle push = new Bundle();
            push.putString("ID",stId);
            push.putString("TITLE",stTitle);
            push.putString("PRICE",stPrice);
            push.putString("DESCRIPTION",stDescription);
            push.putString("CATEGORY",stCategory);
            push.putString("IMAGE",stImage);
            intent.putExtras(push);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvTitle;
        TextView tvPrice;
        CardView cardview_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.id_image);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            cardview_layout = itemView.findViewById(R.id.cardview_layout);
        }
    }
}
