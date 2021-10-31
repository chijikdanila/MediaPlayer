package com.example.mediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ArrayList<Uri> paths = new ArrayList<>();
    ArrayList<PageFragment> pages = new ArrayList<>();
    int requestCode = 1;
    ViewPager2 viewPager;
    Button chooseAudioButton;
    Button chooseVideoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        chooseAudioButton = findViewById(R.id.chooseAudioButton);
        chooseVideoButton = findViewById(R.id.chooseVideoButton);

        MediaPlayer mediaPlayer = new MediaPlayer();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Toast.makeText(MainActivity.this, "Page" + (position + 1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

//    public void openFileChooser(View view) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        switch (view.getId()) {
//            case R.id.chooseAudioButton : {
//                intent.setType("*/*");
//                startActivityForResult(intent, 1);
//            }
//            case R.id.chooseVideoButton: {
//                intent.setType("*/*");
//                startActivityForResult(intent, 2);
//            }
//        }
//    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Uri uri = data.getData();
//        String path = uri.getPath();
//        if (resultCode != Activity.RESULT_OK)
//        {
//            return;
//        }
//        switch (requestCode) {
//            case 1 : {
//
//            }
//            case 2 : {
//
//            }
//        }
//    }

    public void updatePages(){
        int position = viewPager.getCurrentItem();
        viewPager = findViewById(R.id.pager);
        FragmentStateAdapter pageAdapter = new com.example.mediaplayer.PageAdapter(this,this, pages);
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(position, false);
    }

    public void chooseAudio(View view){
        pages.add(new PageAudioFragment(this ,"audio", "android.resource://" + getPackageName() + "/" + R.raw.test_music));
        updatePages();
    }

    public void chooseVideo(View view){
        pages.add(new PageVideoFragment(this,"video", "android.resource://" + getPackageName() + "/" + R.raw.test_video));
        updatePages();
    }
}