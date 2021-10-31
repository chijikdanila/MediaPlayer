package com.example.mediaplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.nio.file.Paths;


public class PageVideoFragment extends PageFragment {

    private String file;
    Context context;
    VideoView videoPlayer;
    String name;
    private int currentPosition = 0;

    public static PageVideoFragment newInstance(PageVideoFragment fragment) {
        fragment = new PageVideoFragment(fragment.context,"video", fragment.fileName);
        Bundle args = new Bundle();
        args.putString("file", fragment.fileName);
        fragment.setArguments(args);
        return fragment;
    }

    public PageVideoFragment( Context context, String type, String fileName) {
        super(context, type, fileName);
        this.file = fileName;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        file = getArguments() != null ? getArguments().getString("file") : null;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("pos", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.video_fragment_page, container, false);
        TextView pageHeader = result.findViewById(R.id.displayVideo);
        name = "test_video";
        pageHeader.setText(name);
        videoPlayer = result.findViewById(R.id.showVideo);
        MyMediaController mediaController = new MyMediaController(context);
        mediaController.setListener(new MyMediaController.OnMediaControllerInteractionListener() {
            @Override
            public void onRequestFullScreen() {
                onFullScreen();
            }
        });
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
        return result;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        videoPlayer.stopPlayback();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.resume();
        Uri myVideoUri = Uri.parse(file);
        videoPlayer.setVideoURI(myVideoUri);
        videoPlayer.seekTo(currentPosition);
        videoPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.pause();
    }

    public void onFullScreen() {
        Intent intent = new Intent(context, FullScreenActivity.class);
        intent.putExtra("file", file);
        intent.putExtra("pos", videoPlayer.getCurrentPosition());
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        this.currentPosition = data.getIntExtra("pos", 0);
        videoPlayer.seekTo(currentPosition);
        videoPlayer.start();
    }
}
