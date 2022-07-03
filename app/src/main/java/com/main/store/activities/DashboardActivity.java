package com.main.store.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.R;
import com.main.store.adapter.RecyclerAdapter;
import com.main.store.models.ProductsResponse;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    //ActivityDashboardBinding binding;

    RecyclerAdapter adapter;

    TextView tvElectromic;
    TextView tvJewelery;
    TextView tvMen;
    TextView tvWomen;

    CardView cardElectronics,
            cardJewelery,
            cardMen,
            cardWomen;

    TextView tvElectronics;

    BaseApiService mApiServise;

    List<ProductsResponse> listData;

    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    Integer electroCount = 0,
            jeweleryCount = 0,
            menCount = 0,
            womenCount = 0;

    @SuppressLint("CutPasteId")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_dashboard);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        //getWindow().setStatusBarColor(ContextCompat.getColor(DashboardActivity.this,R.color.black));// set status background white
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recyclerView = findViewById(R.id.id_recyclerview);
        tvElectromic = findViewById(R.id.tv_electronics);
        tvJewelery = findViewById(R.id.tv_jewelery);
        tvMen = findViewById(R.id.tv_men);
        tvWomen = findViewById(R.id.tv_women);
        tvElectronics = findViewById(R.id.tv_electronics);

        cardElectronics = findViewById(R.id.card_electronics);
        cardJewelery = findViewById(R.id.card_jewelery);
        cardMen = findViewById(R.id.card_men);
        cardWomen = findViewById(R.id.card_women);

        mApiServise = UtilsApi.getOthersAPIService();


        getProducstList();

        initListener();
    }

    private void initListener() {
        cardElectronics.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this, ElectronicsActivity.class);
            startActivity(intent);
        });
        cardJewelery.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this, JeweleryActivity.class);
            startActivity(intent);
        });
        cardMen.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this, MenActivity.class);
            startActivity(intent);
        });
        cardWomen.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this, WomenActivity.class);
            startActivity(intent);
        });
    }

    private void getProducstList() {
        showLoader("Loading...\n", "Please wait.");
        mApiServise.getAllProducts()
                .enqueue(new Callback<List<ProductsResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ProductsResponse>> call, Response<List<ProductsResponse>> response) {

                        listData = response.body();

                        progressDialog.dismiss();

                        adapter = new RecyclerAdapter(listData, DashboardActivity.this);
                        recyclerView.setHasFixedSize(true);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                        GridLayoutManager layoutManager=new GridLayoutManager(DashboardActivity.this,2);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);

                        for (int i = 0; i < listData.size(); i++) {
                            if (listData.get(i).getCategory().equalsIgnoreCase("electronics"))
                            {
                                electroCount = electroCount + 1;
                            }else if(listData.get(i).getCategory().equalsIgnoreCase("jewelery")){
                                jeweleryCount = jeweleryCount + 1;
                            }else if(listData.get(i).getCategory().equalsIgnoreCase("men's clothing")){
                                menCount = menCount + 1;
                            }else if(listData.get(i).getCategory().equalsIgnoreCase("women's clothing")){
                                womenCount = womenCount + 1;
                            }
                        }
                        tvElectronics.setText(String.valueOf(electroCount));
                        tvJewelery.setText(String.valueOf(jeweleryCount));
                        tvMen.setText(String.valueOf(menCount));
                        tvWomen.setText(String.valueOf(womenCount));
                    }

                    @Override
                    public void onFailure(Call<List<ProductsResponse>> call, Throwable t) {
                        Toast.makeText(DashboardActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showLoader(String title, String message) {

        progressDialog = new ProgressDialog(DashboardActivity.this);
        progressDialog.setMessage(title + "" + message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}