package com.main.store.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.databinding.ActivitySingupBinding;
import com.main.store.models.SignUpParams;
import com.main.store.models.SignUpResponse;
import com.main.store.models.signUp.Addresses;
import com.main.store.models.signUp.Geolocation;
import com.main.store.models.signUp.Name;
import com.main.store.utilities.CommonFunction;
import com.main.store.utilities.CommonKeys;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    ActivitySingupBinding binding;

    BaseApiService mApiService;
    ProgressDialog progressDialog;

    String stEmail,
            stPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mApiService = UtilsApi.getOthersAPIService();

        binding.btnSubmit.setOnClickListener(v ->
        {
            stEmail = binding.edtEmail.getText().toString();
            stPassword = binding.edtPassword.getText().toString();

            requestSubmit();
        });
    }

    private void requestSubmit() {

        Name name = new Name();
        Geolocation geolocation = new Geolocation();
        Addresses addresses = new Addresses(geolocation);
        SignUpParams params = new SignUpParams(
                stEmail,
                stPassword,
                name,
                addresses
        );
        Log.e("signUpParams", params.toString());
        showLoader("Loading...\n ", "Wait a moment.");
        mApiService.signupResponse(params)
                .enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (response.isSuccessful()) {

                            SignUpResponse resp = response.body();
                            Log.e("LoginResponse", resp.toString() + "");
                            progressDialog.dismiss();

                            //if (resp != null && resp.getStatus()) {
                            if (!resp.getId().equalsIgnoreCase("")) {
                                progressDialog.dismiss();

                                Toast.makeText(SignupActivity.this, "SignUp Successful.", Toast.LENGTH_LONG).show();

                                CommonFunction.savePreferences(SignupActivity.this, CommonKeys.ID, "2");

                                Intent intent = new Intent(SignupActivity.this,SignInActivity.class);
                                startActivity(intent);

                            } else if (resp.getId().equalsIgnoreCase("")) {
                                progressDialog.dismiss();
                                //Toast.makeText(SignupActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();
                                Log.e("ErrorResp", resp + "");
                            }

                        } else {
                            progressDialog.dismiss();
                            Log.e("Failed", response.message());
                            Toast.makeText(SignupActivity.this, "ইউজারনেম অথবা পাসওয়ার্ড ভুল, আবার চেষ্টা করুন", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(SignupActivity.this, "Failled", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showLoader(String title, String message) {

        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setMessage(title + "" + message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}