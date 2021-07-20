package com.nju.androidfinal.video;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Video {
    @SerializedName("_id")
    public String id;
    @SerializedName("feedurl")
    public String feedurl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int likecount;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("thumbnails")
    public String cover;

    public Video(LinkedTreeMap map) {
        this.id = (String) map.get("id");
        this.feedurl = (String) map.get("feedurl");
        this.nickname = (String) map.get("nickname");
        this.description = (String) map.get("description");
        this.likecount = ((Double) map.get("likecount")).intValue();
        this.avatar = (String) map.get("avatar");
        this.cover = (String) map.get("thumbnails");
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", feedurl='" + feedurl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", likecount=" + likecount +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getFeedurl() {
        return feedurl;
    }

    public String getId() {
        return id;
    }

}
