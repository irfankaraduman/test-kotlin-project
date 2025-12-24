package com.msku.example.hacininyeri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private CardView buttonRegister;
    private TextView textCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextIngredients);
        editTextPassword = findViewById(R.id.et_search);
        buttonRegister = findViewById(R.id.buttonRegister);
        textCreateAccount = findViewById(R.id.textCreateAccount);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement login functionality here
                // Retrieve email and password from editTextEmail and editTextPassword
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

            }
        });

        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration activity
            }
        });
    }
}