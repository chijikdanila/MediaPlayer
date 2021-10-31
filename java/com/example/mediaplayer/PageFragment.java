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

//    public String getFileName(Uri uri) {
//        String result = null;
//        if (uri.getScheme().equals("content")) {
//            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
//            try {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//        if (result == null) {
//            result = uri.getPath();
//            int cut = result.lastIndexOf('/');
//            if (cut != -1) {
//                result = result.substring(cut + 1);
//            }
//        }
//        return result;
//    }
}
