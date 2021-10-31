package com.example.mediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class FullScreenActivity extends AppCompatActivity {
    String file = "";
    int currentPosition = 0;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e) {
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen);
        videoView = findViewById(R.id.fullScreen);
        MyMediaController mediaController = new MyMediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setListener(new MyMediaController.OnMediaControllerInteractionListener() {
            @Override
            public void onRequestFullScreen() {
                onBack();
            }
        });
        mediaController.setMediaPlayer(videoView);

        Bundle intent = getIntent().getExtras();

        if(intent != null){
            file = intent.getString("file");
            currentPosition = intent.getInt("pos");
        }
        else
            return;
        videoView.setVideoURI(Uri.parse(file));
        videoView.seekTo(currentPosition);
        videoView.start();

    }

    public void onBack() {
        Intent intent = new Intent();
        intent.putExtra("pos", videoView.getCurrentPosition());
        setResult(RESULT_OK, intent);
        finish();
    }
}
