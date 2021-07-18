package com.nju.androidfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.nju.androidfinal.video.API;
import com.nju.androidfinal.video.Video;
import com.nju.androidfinal.video.VideoAdapter;
import com.nju.androidfinal.videoList.VideoListActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 videoPager;
    private VideoAdapter videoAdapter;
    private TextView listButton;
    private TextView me;
    private List<Video> videoInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        videoPager = findViewById(R.id.video_pager);
        videoAdapter = new VideoAdapter(this);
        setData();
        videoPager.setAdapter(videoAdapter);

        me = findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "我的页面：可以上传视频和查看自己上传的视频，登录注册收藏", Toast.LENGTH_SHORT).show();
            }
        });
        listButton = findViewById(R.id.listrecycler);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoListActivity.class);
                Gson gson = new Gson();
                String videoinfolist = gson.toJson(videoInfoList);
                intent.putExtra("videoInfos", videoinfolist);
                startActivity(intent);
            }
        });


    }


    private void setData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //绑定retrofit与tiktokAPI，设置请求成功与请求失败两种情况下的行为
        API tiktokAPI = retrofit.create(API.class);
        tiktokAPI.getVideoInfo().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.body() != null) {
                    List<Video> videoInfos = response.body();
                    if (videoInfos.size() != 0) {
                        videoAdapter.setVideoInfoList(videoInfos);
                        videoAdapter.notifyDataSetChanged();
                        videoInfoList = videoInfos;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "setDate()请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}