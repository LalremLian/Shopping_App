package com.main.store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.R;
import com.main.store.adapter.ElectronicsAdapter;
import com.main.store.adapter.MenAdapter;
import com.main.store.models.ProductsResponse;

import java.util.List;

public class MenActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseApiService mApiService;
    MenAdapter adapter;
    ProgressDialog progressDialog;

    List<ProductsResponse> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);

        recyclerView = findViewById(R.id.electro_recycler);

        mApiService = UtilsApi.getOthersAPIService();

        getData();
    }
    private void getData() {
        showLoader("Loading...\n", "Please wait.");
        mApiService.getMen()
                .enqueue(new Callback<List<ProductsResponse>>() {
                    @Override
                    public void onResponse(Call<List<ProductsResponse>> call, Response<List<ProductsResponse>> response) {
                        if (response.isSuccessful()) {

                            progressDialog.dismiss();
                            listData = response.body();

                            adapter = new MenAdapter(listData, MenActivity.this);
                            recyclerView.setHasFixedSize(true);
                            //recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                            GridLayoutManager layoutManager=new GridLayoutManager(MenActivity.this,2);
                            recyclerView.setLayoutManager(layoutManager);recyclerView.setAdapter(adapter);

                        } else {
                            Toast.makeText(MenActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductsResponse>> call, Throwable t) {
                        Toast.makeText(MenActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showLoader(String title, String message) {

        progressDialog = new ProgressDialog(MenActivity.this);
        progressDialog.setMessage(title + "" + message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}