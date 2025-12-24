package com.msku.example.hacininyeri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.msku.example.hacininyeri.LoginRegisterPage.LoginActivity;

public class CheckAuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_auth);
        startActivity(new Intent(this, LoginActivity.class));
    }
}