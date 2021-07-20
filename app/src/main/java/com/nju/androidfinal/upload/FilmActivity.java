package com.nju.androidfinal.upload;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nju.androidfinal.R;

import java.io.IOException;

public class FilmActivity extends AppCompatActivity {

    private SurfaceView media;
    private MediaRecorder mediaRecorder;
    private boolean isStartAndStop = false;
    private Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_film);

        media = (SurfaceView) findViewById(R.id.mediarecorder);//获取SurfaceView
        mediaRecorder = new MediaRecorder();//实例化媒体录制器
        recordButton = (Button) findViewById(R.id.recordbutton);
        recordButton.setOnClickListener(v -> {
            startMediaRecorder();
        });
    }

    //视频录制与暂停的方法
    public void startMediaRecorder() {
        if (!isStartAndStop) {
            if (mediaRecorder == null) {
                mediaRecorder = new MediaRecorder();//实例化媒体录制器
            }
            mediaRecorder.reset();
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); //从照相机采集视频
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置麦克风
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//设置保存的格式
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H263);//设置编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();//设置保存的路径
            mediaRecorder.setOutputFile(sdPath + "/DCIM/" + System.currentTimeMillis() + ".mp4");
            Log.d("path:", sdPath + "/DCIM/" + System.currentTimeMillis() + ".mp4");

            mediaRecorder.setPreviewDisplay(media.getHolder().getSurface());//将画面展示到SurfaceView
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            recordButton.setText(R.string.end);

        } else {
            mediaRecorder.release();  //为其它应用释放摄像头
            mediaRecorder = null;
            recordButton.setText(R.string.begin);//关闭
        }
        isStartAndStop = !isStartAndStop;
    }
}
