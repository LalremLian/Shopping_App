package com.main.store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.R;
import com.main.store.adapter.ElectronicsAdapter;
import com.main.store.adapter.JeweleryAdapter;
import com.main.store.models.ProductsResponse;

import java.util.List;
import java.util.Objects;

public class JeweleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseApiService mApiService;
    JeweleryAdapter adapter;
    ProgressDialog progressDialog;

    Toolbar toolbar;
    TextView txttoolbar;

    List<ProductsResponse> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewelery);

        recyclerView = findViewById(R.id.electro_recycler);
        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txttoolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        txttoolbar.setText("Jewelery");

        mApiService = UtilsApi.getOthersAPIService();

        getData();
    }
    private void getData() {
        showLoader("Loading...\n", "Please wait.");
        mApiService.getJewelery()
                .enqueue(new Callback<List<ProductsResponse>>() {
                    @Override
                    public void onResponse(Call<List<ProductsResponse>> call, Response<List<ProductsResponse>> response) {
                        if (response.isSuccessful()) {

                            progressDialog.dismiss();
                            listData = response.body();

                            adapter = new JeweleryAdapter(listData, JeweleryActivity.this);
                            recyclerView.setHasFixedSize(true);
                            //recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                            GridLayoutManager layoutManager=new GridLayoutManager(JeweleryActivity.this,2);
                            recyclerView.setLayoutManager(layoutManager);recyclerView.setAdapter(adapter);

                        } else {
                            Toast.makeText(JeweleryActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductsResponse>> call, Throwable t) {
                        Toast.makeText(JeweleryActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showLoader(String title, String message) {

        progressDialog = new ProgressDialog(JeweleryActivity.this);
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