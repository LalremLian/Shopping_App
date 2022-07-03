package com.main.store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.R;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CartsActivity extends AppCompatActivity {

    BaseApiService mApiService;

    String stId;
    String stTitle;
    String stPrice;
    String stImage;

    TextView tv_title;
    TextView tv_price;
    TextView count;
    ImageView tv_minus;
    ImageView tv_pluss;
    TextView tv_total;

    ImageView img_product;

    CardView cardView;

    Integer amount = 1;
    Double aDouble;
    Double aBDouble;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tv_title = findViewById(R.id.tv_title);
        tv_price = findViewById(R.id.tv_price);
        img_product = findViewById(R.id.id_image);
        tv_minus = findViewById(R.id.tv_minus);
        tv_pluss = findViewById(R.id.tv_plus);
        tv_total = findViewById(R.id.tv_total_price);
        cardView = findViewById(R.id.card_checkout);
        count = findViewById(R.id.count);

        mApiService = UtilsApi.getOthersAPIService();

        getData();

        tv_pluss.setOnClickListener(v ->
        {
            amount = amount + 1;
            aDouble = round((aDouble + aBDouble),2);
            tv_price.setText("৳ " + aDouble);
            tv_total.setText("Total : " + "৳ "+ aDouble);
            count.setText(amount.toString());
        });
        tv_minus.setOnClickListener(v ->
        {
            amount = amount - 1;
            aDouble = round((aDouble - aBDouble),2);
            tv_price.setText("৳ " + aDouble);
            tv_total.setText("Total : " + "৳ "+ aDouble);
            count.setText(amount.toString());
        });
        cardView.setOnClickListener(v ->
        {
            Intent intent = new Intent(CartsActivity.this,CompleteActivity.class);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        Bundle bundle = getIntent().getExtras();

        stId = bundle.getString("1ID");
        stTitle = bundle.getString("1TITLE");
        stPrice = bundle.getString("1PRICE");
        stImage = bundle.getString("1IMAGE");

        aDouble = Double.parseDouble(stPrice);
        aBDouble = Double.parseDouble(stPrice);

        tv_title.setText(stTitle);
        tv_price.setText("৳ " + aDouble);
        tv_total.setText("Total : " + "৳ "+ aDouble);
        count.setText(amount.toString());

        Picasso.get()
                .load(stImage)
                .resize(255,255)
                .centerCrop()
                .into(img_product);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}