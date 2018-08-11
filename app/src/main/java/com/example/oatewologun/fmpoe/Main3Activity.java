package com.example.oatewologun.fmpoe;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Main3Activity extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        videoView = (VideoView) findViewById(R.id.videoView2);
        mediaController = new MediaController(this);
        videoPlay();
    }

    public void videoPlay(){
        String videoPath = "android.resource://com.example.oatewologun.fmpoe/" + R.raw.emergency;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
    }
}
