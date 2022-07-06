package com.main.store.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.R;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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

    Toolbar toolbar;
    TextView txttoolbar;

    Integer amount = 1;
    Double aDouble;
    Double aBDouble;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tv_title = findViewById(R.id.tv_title);
        tv_price = findViewById(R.id.tv_price);
        img_product = findViewById(R.id.id_image);
        tv_minus = findViewById(R.id.tv_minus);
        tv_pluss = findViewById(R.id.tv_plus);
        tv_total = findViewById(R.id.tv_total_price);
        cardView = findViewById(R.id.card_checkout);
        count = findViewById(R.id.count);

        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txttoolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        txttoolbar.setText("Electronics");

        mApiService = UtilsApi.getOthersAPIService();

        getData();

        tv_pluss.setOnClickListener(v ->
        {
            amount = amount + 1;
            aDouble = round((aDouble + aBDouble),2);
            tv_price.setText("৳ " + aDouble);
            tv_total.setText("৳ "+ aDouble);
            count.setText(amount.toString());
        });
        tv_minus.setOnClickListener(v ->
        {
            amount = amount - 1;
            aDouble = round((aDouble - aBDouble),2);
            tv_price.setText("৳ " + aDouble);
            tv_total.setText("৳ "+ aDouble);
            count.setText(amount.toString());
        });
        cardView.setOnClickListener(v ->
        {
            Intent intent = new Intent(CartsActivity.this,CompleteActivity.class);
            startActivity(intent);
            finish();
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
        tv_total.setText("৳ "+ aDouble);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}