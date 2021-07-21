package com.nju.androidfinal.upload;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.nju.androidfinal.API;
import com.nju.androidfinal.R;
import com.nju.androidfinal.video.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommitActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_commit);
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");

        String filepath = intent.getStringExtra("fileInfo");
        String[] frag = filepath.split("/");
        String originalName = frag[frag.length - 1];
        String origin = getString(R.string.your_video) + originalName;

        TextView videoOriginalName = findViewById(R.id.video);
        videoOriginalName.setText(origin);

        EditText newName = findViewById(R.id.new_name);
        EditText editText = findViewById(R.id.edit_text);

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(filepath);
        videoView.setMediaController(new MediaController(this));

        String newNameStr = newName.getText().toString();
        String description = editText.getText().toString();
        String avatarPath = "https://upload.jianshu.io/users/upload_avatars/17506620/c7e69c2d-5b9a-423f-915d-a6d41f0e26de.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String coverPath = "http://8.136.101.204/v/%E9%A5%BA%E5%AD%90%E7%9C%9F%E8%90%8C.jpg";

        LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
        map.put("_id", nickname);
        map.put("feedurl", filepath);
        map.put("nickname", newNameStr);
        map.put("description", description);
        map.put("likecount", 0);
        map.put("avatar", avatarPath);
        map.put("thumbnails", coverPath);
        Gson gson = new Gson();
        String videoInfo = gson.toJson(map);

//        UploadResponse uploadResponse = new UploadResponse();
//        uploadResponse.video = new Video(map);

        Button uploadButton = findViewById(R.id.commitButton);
        uploadButton.setOnClickListener(v -> {
            Log.d("videoInfo", videoInfo);
            //用保存在本地来模拟
            if (videoInfo != null) {
                try {
                    String filename = "/storage/emulated/0/Documents/" + originalName + ".json";
                    File file = new File(filename);
                    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
                    osw.write(videoInfo);
                    osw.flush();
                    osw.close();
                    Toast.makeText(CommitActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(CommitActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
            }


//            若有post的url，则用下面的代替
//            String urlStr = "https://bytedance.com/";
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(urlStr)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            API api = retrofit.create(API.class);
//            api.postVideoInfo(uploadResponse.video).enqueue(new Callback<UploadResponse>() {
//                @Override
//                public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
//                    if (response.body() != null && response.body().video != null) {
//                        Toast.makeText(CommitActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                    } else if (response.body() != null) {
//                        Toast.makeText(CommitActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<UploadResponse> call, Throwable t) {
//                    Toast.makeText(CommitActivity.this, "网络故障", Toast.LENGTH_SHORT).show();
//                }
        });
    }
}