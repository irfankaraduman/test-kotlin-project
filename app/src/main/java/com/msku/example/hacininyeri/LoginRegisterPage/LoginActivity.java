package com.msku.example.hacininyeri.LoginRegisterPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.msku.example.hacininyeri.HomeActivity;
import com.msku.example.hacininyeri.R;

public class LoginActivity extends AppCompatActivity implements  LoginView {

    private EditText editTextEmail, editTextPassword;
    private CardView buttonLogin;
    private TextView textCreateAccount;
    TextView tv_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.et_LoginMail);
        editTextPassword = findViewById(R.id.et_LoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textCreateAccount = findViewById(R.id.textCreateAccount);
        tv_state = findViewById(R.id.tv_state);

        Presenter presenter = new Presenter(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
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

    @Override
    public void showLoginSuccesssText() {
        tv_state.setText("Giriş Başarılı");
        tv_state.setTextColor(Color.GREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        }, 1000);
    }

    @Override
    public void showUserNotFoundText() {
        tv_state.setText("Kullanıcı Bulunamadı");
        tv_state.setTextColor(Color.YELLOW);
    }

    @Override
    public void showErrorText() {
        tv_state.setText("Hata");
        tv_state.setTextColor(Color.RED);
    }

    @Override
    public void showProcessText() {
        tv_state.setTextColor(Color.BLACK);
        tv_state.setText("Giriş Yapılıyor...");
    }
}