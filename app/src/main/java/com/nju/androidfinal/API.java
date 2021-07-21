package com.nju.androidfinal;

import com.nju.androidfinal.upload.UploadResponse;
import com.nju.androidfinal.video.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("api/invoke/video/invoke/video")
    Call<List<Video>> getVideoInfo();

    //没有url，先以如下代替
    @FormUrlEncoded
    @POST("bytedance/video")
    Call<UploadResponse> postVideoInfo(@Field("videoInfo") Video video);
}
