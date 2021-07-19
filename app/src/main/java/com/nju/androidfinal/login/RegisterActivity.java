package com.nju.androidfinal.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.androidfinal.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        final String[] name = {""};
        EditText username = findViewById(R.id.et_username);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                name[0] = s.toString();
            }
        });

        final String[] email = {""};
        EditText useremail = findViewById(R.id.et_useremail);
        useremail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                email[0] = s.toString();
            }
        });

        final String[] password = {""};
        EditText userpassword = findViewById(R.id.et_userpassword);
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                password[0] = s.toString();
            }
        });

        Button cancel = findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(v -> finish());

        TextView license = findViewById(R.id.tv_licence);
        license.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.bytedance.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(v -> {
            CheckBox ok = findViewById(R.id.cb_licence);
            if (!ok.isChecked()) {
                Toast.makeText(this, R.string.signup_fail_not_agree_licence, Toast.LENGTH_SHORT).show();
            } else {
                if (name[0].isEmpty() || email[0].isEmpty() || password[0].isEmpty()) {
                    Toast.makeText(this, R.string.signup_fail_data_invalid, Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(email[0], password[0] + " " + name[0]);
                    editor.apply();
                    editor.commit();

                    Toast.makeText(this, R.string.signup_success, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}