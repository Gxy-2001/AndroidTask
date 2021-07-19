package com.nju.androidfinal.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.androidfinal.PersonalCenter;
import com.nju.androidfinal.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.email_toggle);
        EditText password = findViewById(R.id.password_toggle);
        TextView forget = findViewById(R.id.forgetPassword);
        TextView noAccount = findViewById(R.id.noAccount);
        Button login = findViewById(R.id.loginButton);

        forget.setOnClickListener(v -> {
            Intent intent = new Intent(this, FindbackActivity.class);
            startActivity(intent);
        });


        noAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);

        login.setOnClickListener(v -> {
            String emailStr = email.getText().toString();
            String passwordStr = password.getText().toString();
            String sharedIdValue = sharedPreferences.getString(emailStr, null);

            if (sharedIdValue != null && sharedIdValue.startsWith(passwordStr)) {
                Toast.makeText(this, R.string.loginSuccess, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PersonalCenter.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, R.string.loginFailure, Toast.LENGTH_SHORT).show();
            }
        });

    }
}