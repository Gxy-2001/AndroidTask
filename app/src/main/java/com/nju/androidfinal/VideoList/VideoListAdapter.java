package com.nju.androidfinal.VideoList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.nju.androidfinal.R;
import com.nju.androidfinal.Video.Video;
import com.nju.androidfinal.Video.VideoPlay;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private Context context;
    private List<Video> videoInfos;

    public VideoListAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        Video videoInfo = videoInfos.get(position);

        holder.description.setText(videoInfo.description);
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().frame(0))
                .load(videoInfo.getFeedurl())
                .centerCrop()
                .into(holder.imageView1);
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().frame(3000000))
                .load(videoInfo.getFeedurl())
                .centerCrop()
                .into(holder.imageView2);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlay.class);
                Gson gson = new Gson();
                String videoinfolist = gson.toJson(videoInfos);
                intent.putExtra("videoInfos", videoinfolist);
                intent.putExtra("feedurl", videoInfo.getFeedurl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoInfos == null ? 0 : videoInfos.size();
    }

    public void setVideoInfoList(List<Video> videoInfos) {
        this.videoInfos = videoInfos;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView1;
        private ImageView imageView2;
        private TextView description;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.image1);
            imageView2 = itemView.findViewById(R.id.image2);
            description = itemView.findViewById(R.id.description);
        }
    }

}
