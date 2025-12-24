package com.msku.example.hacininyeri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private CardView buttonLogin;
    private TextView textCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextIngredients);
        editTextPassword = findViewById(R.id.et_search);
        buttonLogin = findViewById(R.id.buttonLogin);
        textCreateAccount = findViewById(R.id.textCreateAccount);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                // Implement login functionality here
                // Retrieve email and password from editTextEmail and editTextPassword
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Add logic to authenticate user with email and password
                // For example, you can check against a hardcoded value for demo purposes
                if (email.equals("demo@example.com") && password.equals("password")) {
                    // Login successful, navigate to another activity (e.g., home screen)
                  //  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                  //  startActivity(intent);
                  //  finish(); // Close the login activity
                } else {
                    // Handle incorrect login credentials
                    // Show an error message or toast to the user
                }
            }
        });

        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration activity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}