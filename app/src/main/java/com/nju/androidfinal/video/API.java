package com.nju.androidfinal.video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/invoke/video/invoke/video")
    Call<List<Video>> getVideoInfo();
}
