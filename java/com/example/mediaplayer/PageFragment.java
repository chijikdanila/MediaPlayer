package com.example.mediaplayer;

import android.database.Cursor;
import android.net.Uri;
import android.content.Context;
import android.provider.OpenableColumns;

import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {
    public String type;
    public String fileName;
    public Context context;

    public PageFragment(Context context,String type, String fileName) {
        this.type = type;
        this.fileName = fileName;
        this.context = context;
    }
}
