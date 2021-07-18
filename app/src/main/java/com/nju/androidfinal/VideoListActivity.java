package com.nju.androidfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.LinkedList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity {

    private RecyclerView rv_video_list;
    private VideoListAdapter itemAdapter;
    private LinearLayoutManager layoutManager;
    private List<VideoInfo> videoInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        Intent intent = getIntent();
        String list = intent.getStringExtra("videoInfos");
        Gson gson = new Gson();
        List<LinkedTreeMap> maps = gson.fromJson(list, List.class);
        Log.d("videoInfos", String.valueOf(videoInfos));
        videoInfos = new LinkedList<>();
        if(maps != null) {
            for (LinkedTreeMap map: maps) {
                VideoInfo videoInfo = new VideoInfo(map);
                videoInfos.add(videoInfo);
            }
        }

        rv_video_list = findViewById(R.id.rv_video_list);
        rv_video_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_video_list.setLayoutManager(layoutManager);
        itemAdapter = new VideoListAdapter(this);
        rv_video_list.setAdapter(itemAdapter);
        itemAdapter.setVideoInfoList(videoInfos);
    }


}

