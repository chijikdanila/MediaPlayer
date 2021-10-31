package com.example.mediaplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PageAudioFragment extends PageFragment implements View.OnClickListener {

    private String file;
    public Context context;
    private int currentPosition;
    private boolean isPlaying;
    private Button buttonPlay;
    private String name;
    private long startPosition;

    public static PageAudioFragment newInstance(Context context, PageAudioFragment fragment) {
        fragment = new PageAudioFragment(context,"audio", fragment.fileName);
        Bundle args = new Bundle();
        args.putString("file", fragment.fileName);
        fragment.setArguments(args);
        return fragment;
    }

    public PageAudioFragment(Context context, String type, String fileName) {
        super(context,type, fileName);
        this.context = context;
        this.file = fileName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        file = getArguments() != null ? getArguments().getString("file") : null;
        if(savedInstanceState != null){
            this.file = savedInstanceState.getString("file");
            this.currentPosition = savedInstanceState.getInt("pos");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.audio_fragment_page, container, false);
        TextView pageHeader = result.findViewById(R.id.displayAudio);
        name = "test_music";
        pageHeader.setText(name);
        result.findViewById(R.id.buttonFastForward).setOnClickListener(this);
        result.findViewById(R.id.buttonRewind).setOnClickListener(this);
        buttonPlay = result.findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
        return result;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("pos", currentPosition);
        outState.putString("file", file);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(this.context, MediaService.class);
        intent.putExtra("file", file);
        intent.putExtra("pos", currentPosition);
        context.stopService(intent);
        context.startService(intent);
        isPlaying = true;
        buttonPlay.setText(this.getResources().getString(R.string.pause_song));
        MusicNotification.displayNotification(context, name);
        startPosition = System.currentTimeMillis();
    }

    @Override
    public void onPause() {
        isPlaying = false;
        buttonPlay.setText(this.getResources().getString(R.string.play_song));
        currentPosition += (System.currentTimeMillis() - startPosition);
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, MediaService.class);
        switch (view.getId()){
            case R.id.buttonPlay : {
                if(!isPlaying){
                    isPlaying = true;
                    buttonPlay.setText(this.getResources().getString(R.string.pause_song));
                    startPosition = System.currentTimeMillis();
                }
                else{
                    isPlaying = false;
                    buttonPlay.setText(this.getResources().getString(R.string.play_song));
                    currentPosition += (System.currentTimeMillis() - startPosition);
                    context.stopService(intent);
                    return;
                }
            }
            case R.id.buttonFastForward : {
                if(!isPlaying)
                    return;
                currentPosition += (System.currentTimeMillis() - startPosition);
                currentPosition += 10000;
                startPosition = System.currentTimeMillis();
                context.stopService(intent);
            }
            case R.id.buttonRewind : {
                if(!isPlaying)
                    return;
                currentPosition += (System.currentTimeMillis() - startPosition);
                currentPosition -= 10000;
                startPosition = System.currentTimeMillis();
                if(currentPosition < 0)
                    currentPosition = 0;
                context.stopService(intent);
            }
        }
        intent.putExtra("file", file);
        intent.putExtra("pos", currentPosition);
        context.startService(intent);
    }
}
