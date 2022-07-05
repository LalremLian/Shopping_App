package com.main.store.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.main.store.R;
import com.main.store.models.user.UserResponse;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

    ImageView circleImageView;
    ImageView imgCart;

    TextView name;
    TextView txttoolbar;
    TextView email, phone;
    ImageView imageView;

    Toolbar toolbar;

    List<UserResponse> listData;

    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        circleImageView = findViewById(R.id.image);
        name = findViewById(R.id.tv_name);
        email = findViewById(R.id.tv_email);
        phone = findViewById(R.id.tv_phone);
        imageView = findViewById(R.id.img_pick);
        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txttoolbar);
        imgCart = findViewById(R.id.img_cart);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        txttoolbar.setText("Profile");

        imgCart.setOnClickListener(v -> Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show());

        getUserData();
    }

    private void getUserData() {
        imageView.setOnClickListener(v ->
        {
            SelectImage();
        });
    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select Type"), SELECT_FILE);
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requenstCode, int resultCode, Intent data) {
        super.onActivityResult(requenstCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requenstCode == REQUEST_CAMERA) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                //saveTempBitmap(bitmap);
                circleImageView.setImageBitmap(bitmap);
            } else if (requenstCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                circleImageView.setImageURI(selectedImageUri);
            }
        }
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