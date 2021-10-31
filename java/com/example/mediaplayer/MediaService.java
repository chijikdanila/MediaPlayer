package com.example.mediaplayer;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import androidx.annotation.Nullable;
import java.io.FileNotFoundException;


public class MediaService extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String file = intent.getStringExtra("file");
        int currentPosition = intent.getIntExtra("pos", 0);
        if(file != null)
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        mediaPlayer = MediaPlayer.create(this, Uri.parse(file));
        mediaPlayer.setLooping(true);
        if(currentPosition < mediaPlayer.getDuration() && currentPosition > 0)
            mediaPlayer.seekTo(currentPosition);
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}
