package com.nju.androidfinal.upload;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nju.androidfinal.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UploadActivity extends AppCompatActivity {

    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        initData();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.upload_video_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("name");

        UploadAdapter adapter = new UploadAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setInfoList(mDatas, nickname);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        File filePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM");
        File[] fileList = filePath.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            mDatas.add(file.getAbsolutePath());
        }
    }
}