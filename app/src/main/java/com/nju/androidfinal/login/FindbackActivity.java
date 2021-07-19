package com.nju.androidfinal.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nju.androidfinal.R;

public class FindbackActivity extends AppCompatActivity {

    private String TAG = "App";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_findback);

        Button cancel = findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(v -> finish());

        Button confirm = findViewById(R.id.btn_confirm);

        confirm.setOnClickListener(v -> {
            EditText username = findViewById(R.id.et_username);
            String name = username.getText().toString();
            EditText useremail = findViewById(R.id.et_useremail);
            String email = useremail.getText().toString();

            SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
            String sharedIdValue = sharedPreferences.getString(email, null);

            if (sharedIdValue == null) {
                Log.d(TAG,"1");
                Toast.makeText(this, R.string.forget_pass_no_user, Toast.LENGTH_SHORT).show();
            } else if (!sharedIdValue.split(" ")[1].equals(name)) {
                Log.d(TAG,"2");
                Toast.makeText(this, R.string.forget_pass_name_not_match, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(
                        this,
                        getString(R.string.forget_pass_result) + sharedIdValue.split(" ")[0],
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}