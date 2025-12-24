package com.msku.example.hacininyeri.LoginRegisterPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.msku.example.hacininyeri.R;

import java.util.HashMap;
import java.util.Map;

// ******  YUSUF YILDIZ  *******

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private CardView buttonRegister;
    private TextView textCreateAccount;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.et_registerMail);
        editTextPassword = findViewById(R.id.et_registerPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        textCreateAccount = findViewById(R.id.textCreateAccount);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                registerUser(username,password);

            }
        });

        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration activity
            }
        });
    }



    public void registerUser(String username, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);


        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    documentReference.update("userId",documentReference.getId());
                    Log.d("", "User registered successfully with ID: " + documentReference.getId());
                    finish();
                })
                .addOnFailureListener(e -> {
                    Log.e("", "Error registering user", e);
                });
    }
}