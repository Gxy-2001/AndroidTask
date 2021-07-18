package com.nju.androidfinal.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/invoke/video/invoke/video")
    Call<List<VideoInfo>> getVideoInfo();
}
