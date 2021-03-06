package com.nju.androidfinal.video;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nju.androidfinal.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final Context context;
    private List<Video> videoInfoList;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, viewGroup, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        return videoViewHolder;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        final Video videoInfo = videoInfoList.get(i);

        //设置各个组件
        videoViewHolder.videoView.setVisibility(View.GONE);
        videoViewHolder.previewImage.setVisibility(View.VISIBLE);
        videoViewHolder.loadingBar.setVisibility(View.GONE);
        videoViewHolder.description.setText(videoInfo.description);
        videoViewHolder.nickname.setText(videoInfo.nickname);

        //加载创作者头像
        Glide.with(videoViewHolder.videoItem)
                .load(videoInfo.avatar)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(videoViewHolder.avatar);
        //截取视频的一帧作为封面图
        Glide.with(videoViewHolder.videoItem)
                .setDefaultRequestOptions(new RequestOptions().frame(3000000).centerCrop())
                .load(videoInfo.feedurl)
                .into(videoViewHolder.previewImage);
        videoViewHolder.videoView.setVideoPath(videoInfo.feedurl);

        //如果点赞数大于一万以XX.Xw的形式展示
        float temp = (float) videoInfo.likecount / 10000;
        if (temp >= 1) {
            videoViewHolder.likeCount.setText(temp + "w");
        } else {
            videoViewHolder.likeCount.setText(String.valueOf(videoInfo.likecount));
        }
    }

    @Override
    public int getItemCount() {
        return videoInfoList == null ? 0 : videoInfoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView nickname;
        TextView description;
        TextView likeCount;
        TextView heart;
        ImageView avatar;
        ImageView previewImage;
        VideoView videoView;
        RelativeLayout videoItem;
        //heart videoItem;
        View loadingBar;
        boolean like;

        private heart myheart;

        @SuppressLint("ClickableViewAccessibility")
        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            like = false;
            videoView = itemView.findViewById(R.id.my_video_view);
            nickname = itemView.findViewById(R.id.video_id);
            description = itemView.findViewById(R.id.video_description);
            avatar = itemView.findViewById(R.id.avatar_img);
            likeCount = itemView.findViewById(R.id.like_count);
            heart = itemView.findViewById(R.id.heart);
            previewImage = itemView.findViewById(R.id.preview_image);
            videoItem = itemView.findViewById(R.id.video_item);
            loadingBar = itemView.findViewById(R.id.loading_bar);

            myheart = itemView.findViewById(R.id.myheart);

            GestureDetector gestureDetector;
            //添加单双击检测
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                //双击点赞
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (like) {
                        heartClickAnimation();
                    } else {
                        like = true;
                        heart.setBackground(context.getResources().getDrawable(R.drawable.ic_red_heart));
                        String likeCountStr = likeCount.getText().toString();
                        if (likeCountStr.charAt(likeCountStr.length() - 1) != 'w') {
                            likeCount.setText(String.valueOf(Integer.parseInt(likeCountStr) + 1));
                        }
                        heartClickAnimation();
                    }
                    myheart.start();
                    return super.onDoubleTap(e);
                }

                //单击播放/暂停
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                    } else {
                        videoView.start();
                    }
                    return super.onSingleTapConfirmed(e);
                }
            });

            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    gestureDetector.onTouchEvent(motionEvent);
                    return true;
                }
            });

            //点击心形会触发点赞与取消点赞事件
            heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String likeCountStr = likeCount.getText().toString();
                    if (like) {
                        like = false;
                        heart.setBackground(context.getResources().getDrawable(R.drawable.ic_white_heart));
                        if (likeCountStr.charAt(likeCountStr.length() - 1) != 'w') {
                            likeCount.setText(String.valueOf(Integer.parseInt(likeCountStr) - 1));
                        }
                    } else {
                        like = true;
                        heart.setBackground(context.getResources().getDrawable(R.drawable.ic_red_heart));
                        if (likeCountStr.charAt(likeCountStr.length() - 1) != 'w') {
                            likeCount.setText(String.valueOf(Integer.parseInt(likeCountStr) + 1));
                        }
                        heartClickAnimation();
                    }
                }
            });

            //点击预览图开始播放视频
            previewImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    previewImage.setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);
                    loadingBar.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "正在加载", Toast.LENGTH_SHORT).show();
                }
            });

            //当视频加载好之后
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    loadingBar.setVisibility(View.GONE);
                    videoView.requestFocus();
                    videoView.start();
                }
            });
            //设置循环播放
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                    mp.setLooping(true);
                }
            });
        }

        //点击心形时出发的动画
        void heartClickAnimation() {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(heart, "rotation", 0f, 360f);//旋转360度
            animator1.setRepeatCount(1);//无限循环
            animator1.setDuration(500);//设置持续时间
            animator1.start();//动画开始
        }

    }

    public void setVideoInfoList(List<Video> videoInfoList) {
        this.videoInfoList = videoInfoList;
    }

}

