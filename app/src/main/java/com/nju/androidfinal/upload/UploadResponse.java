package com.nju.androidfinal.upload;

import com.google.gson.annotations.SerializedName;
import com.nju.androidfinal.video.Video;

public class UploadResponse {
    @SerializedName("errorCode")
    public int errorCode;
    @SerializedName("errorMsg")
    public String errorMsg;
    @SerializedName("data")
    Video video;
}
