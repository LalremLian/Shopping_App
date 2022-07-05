package com.main.store.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.main.store.databinding.ActivityVerifyNumberBinding;

public class VerifyNumberActivity extends AppCompatActivity {

    ActivityVerifyNumberBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardContinue.setOnClickListener(v ->
        {
            Intent intent = new Intent(VerifyNumberActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }
}