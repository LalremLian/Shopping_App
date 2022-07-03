package com.main.store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.main.store.R;

public class CompleteActivity extends AppCompatActivity {

    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        cardView = findViewById(R.id.card_continue_shopping);


        cardView.setOnClickListener(v ->
        {
            Intent intent = new Intent(CompleteActivity.this, DashboardActivity.class);
            startActivity(intent);
        });
    }
}