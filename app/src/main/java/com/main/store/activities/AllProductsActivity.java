package com.main.store.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.R;
import com.main.store.adapter.AllProductsAdapter;
import com.main.store.models.ProductsResponse;

import java.util.List;
import java.util.Objects;

public class AllProductsActivity extends AppCompatActivity {

    AllProductsAdapter adapter;

    BaseApiService mApiServise;

    List<ProductsResponse> listData;

    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    Toolbar toolbar;
    TextView txttoolbar;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        //getWindow().setStatusBarColor(ContextCompat.getColor(DashboardActivity.this,R.color.black));// set status background white
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recyclerView = findViewById(R.id.all_product_recycler);
        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txttoolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        txttoolbar.setText("All Products");

        mApiServise = UtilsApi.getOthersAPIService();

        getProductsList();
    }

    private void getProductsList() {
        showLoader("Loading...\n", "Please wait.");
        mApiServise.getAllProducts()
                .enqueue(new Callback<List<ProductsResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ProductsResponse>> call, Response<List<ProductsResponse>> response) {

                        listData = response.body();

                        progressDialog.dismiss();

                        adapter = new AllProductsAdapter(listData, AllProductsActivity.this);
                        recyclerView.setHasFixedSize(true);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                        GridLayoutManager layoutManager=new GridLayoutManager(AllProductsActivity.this,2);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ProductsResponse>> call, Throwable t) {
                        Toast.makeText(AllProductsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showLoader(String title, String message) {

        progressDialog = new ProgressDialog(AllProductsActivity.this);
        progressDialog.setMessage(title + "" + message);
        progressDialog.setCancelable(false);
        progressDialog.show();
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