package com.main.store.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.main.store.R;
import com.main.store.utilities.CommonFunction;
import com.main.store.utilities.CommonKeys;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class DetailsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    String stId;
    String stTitle;
    String stPrice;
    String stDescription;
    String stCategory;
    String stImage;

    TextView tv_title;
    TextView tv_price;
    TextView tv_category;
    TextView tv_description;
    TextView tv_price_strike;

    ImageView img_product;
    CardView cardView;
    BottomNavigationView bottomNavigationView;

    double price;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark


        tv_title = findViewById(R.id.tv_details_title);
        tv_price = findViewById(R.id.tv_details_price);
        tv_price_strike = findViewById(R.id.tv_details_price_strike);
        tv_category = findViewById(R.id.tv_details_category);
        tv_description = findViewById(R.id.tv_description);
        img_product = findViewById(R.id.img_details);
        cardView = findViewById(R.id.card_buy);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Bundle bundle = getIntent().getExtras();

        stId = bundle.getString("ID");
        stTitle = bundle.getString("TITLE");
        stPrice = bundle.getString("PRICE");
        stDescription = bundle.getString("DESCRIPTION");
        stCategory = bundle.getString("CATEGORY");
        stImage = bundle.getString("IMAGE");

        Log.e("TAGGGG", stTitle);

        tv_price_strike.setPaintFlags(tv_price_strike.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        CharacterIterator it = new StringCharacterIterator(stDescription);
        for(char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
            if(ch == '/') {
                stDescription = stDescription.replaceAll("/", "\n\n");
            }
        }
        cardView.setOnClickListener(v ->
        {
            Intent intent2 = new Intent(DetailsActivity.this,CartsActivity.class);
            String stId1 =  stId;
            String stTitle1 =  stTitle;
            String stPrice1 =  stPrice;
            String stDescription1 =  stDescription;
            String stCategory1 =  stCategory;
            String stImage1 =  stImage;

            Bundle push = new Bundle();
            push.putString("1ID",stId1);
            push.putString("1TITLE",stTitle1);
            push.putString("1PRICE",stPrice1);
            push.putString("1DESCRIPTION",stDescription1);
            push.putString("1CATEGORY",stCategory1);
            push.putString("1IMAGE",stImage1);
            intent2.putExtras(push);

            startActivity(intent2);
        });

        setData();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {

        price = Double.parseDouble(stPrice);

        price = round(price + 20,2);
        tv_title.setText(stTitle);
        tv_price.setText("৳ "+stPrice);
        tv_price_strike.setText("৳ "+ price);
        tv_category.setText(stCategory);
        tv_description.setText(stDescription);

        Picasso.get()
                .load(stImage)
                .fit()
                .centerCrop()
                .into(img_product);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(DetailsActivity.this,ProfileActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                Intent intent1 = new Intent(DetailsActivity.this,DashboardActivity.class);
                startActivity(intent1);
                return true;

            case R.id.carts:
                Toast.makeText(this, "Empty.", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}