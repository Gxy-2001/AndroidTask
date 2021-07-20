package com.nju.androidfinal.upload;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nju.androidfinal.MainActivity;
import com.nju.androidfinal.R;
import com.nju.androidfinal.video.Video;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.MyViewHolder>{

    private final Context context;
    private List<String> mDatas;
    private String nickname;

    public UploadAdapter(Context context) {
        this.context = context;
    }

    public void setInfoList(List<String> mDatas,String nickname) {
        this.mDatas = mDatas;
        this.nickname = nickname;
    }

    @Override
    public @NotNull MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(
                R.layout.upload_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String[] fra = mDatas.get(position).split("/");
        holder.tv.setText(fra[fra.length-1]);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CommitActivity.class);
            intent.putExtra("nickname", nickname);
            intent.putExtra("fileInfo", mDatas.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.item);
        }
    }
}
