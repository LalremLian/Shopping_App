package com.main.store.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.main.store.ApiUtilites.BaseApiService;
import com.main.store.ApiUtilites.UtilsApi;
import com.main.store.databinding.ActivitySignInBinding;
import com.main.store.models.signIn.SignInParams;
import com.main.store.models.signIn.SignInResponse;
import com.main.store.utilities.CommonFunction;
import com.main.store.utilities.CommonKeys;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    BaseApiService mApiService;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mApiService = UtilsApi.getOthersAPIService();

        binding.edtEmail.setText("mor_2314");
        binding.edtPassword.setText("83r5^_");

        Toast.makeText(this, "Because of API I had to use static Username and Password.", Toast.LENGTH_LONG).show();
        binding.btnLogin.setOnClickListener(v ->
        {
            login();
        });
    }

    private void login() {
        String stUserName = "mor_2314";
        String stPassword = "83r5^_";

        SignInParams params = new SignInParams(
                stUserName,
                stPassword
        );
        mApiService.signinResponse(params)
                .enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        if (response.isSuccessful()) {
                            SignInResponse resp = response.body();
                            Log.e("TAGG", response.message());
                            Toast.makeText(SignInActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();

                            CommonFunction.savePreferences(SignInActivity.this, CommonKeys.AUTH_TOKEN, resp.getToken());

                            Intent intent = new Intent(SignInActivity.this,DashboardActivity.class);
                            startActivity(intent);

                            if (!resp.getToken().equalsIgnoreCase("")) {
                            } else if (resp.getToken().equalsIgnoreCase("")) {
                                Log.e("ErrorResp", resp + "");
                            }

                        } else {
                            Log.e("Failed", response.message());
                            Toast.makeText(SignInActivity.this, "ইউজারনেম অথবা পাসওয়ার্ড ভুল, আবার চেষ্টা করুন", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        Toast.makeText(SignInActivity.this, "Failed to sign in", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}