package com.nju.androidfinal.Video;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.nju.androidfinal.R;

import java.util.LinkedList;
import java.util.List;

public class VideoPlay extends AppCompatActivity {

    private List<Video> videoInfos;
    private ViewPager2 videoPager;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String list = intent.getStringExtra("videoInfos");
        String feedurl = intent.getStringExtra("feedurl");
        Gson gson = new Gson();
        List<LinkedTreeMap> maps = gson.fromJson(list, List.class);
        videoInfos = new LinkedList<>();
        int flag = 0;
        if (maps != null) {
            for (LinkedTreeMap map : maps) {
                if (feedurl.equals(map.get("feedurl"))) {
                    flag = 1;
                }
                if (flag == 1) {
                    Video videoInfo = new Video(map);
                    videoInfos.add(videoInfo);
                }
            }
        }
        setContentView(R.layout.video_play);
        videoPager = findViewById(R.id.video_pager);
        videoAdapter = new VideoAdapter(this);
        videoPager.setAdapter(videoAdapter);
        videoAdapter.setVideoInfoList(videoInfos);
    }
}
