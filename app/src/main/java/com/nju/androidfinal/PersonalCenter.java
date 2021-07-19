package com.nju.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.nju.androidfinal.upload.FilmActivity;
import com.nju.androidfinal.upload.UploadActivity;

public class PersonalCenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_personal_center);

        Intent inputIntent = getIntent();
        String username = inputIntent.getStringExtra("username");

        Button film = findViewById(R.id.startFilming);
        film.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalCenter.this, FilmActivity.class);
            intent.putExtra("name",username);
            startActivity(intent);
        });

        Button upload = findViewById(R.id.upload);
        upload.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalCenter.this, UploadActivity.class);
            intent.putExtra("name",username);
            startActivity(intent);
        });
    }
}