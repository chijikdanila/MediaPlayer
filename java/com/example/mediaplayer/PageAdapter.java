package com.example.mediaplayer;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStateAdapter {

    private ArrayList<PageFragment> pages;
    public Context context;

    public PageAdapter(Context context,FragmentActivity fragmentActivity, ArrayList<PageFragment> pages) {
        super(fragmentActivity);
        this.pages = pages;
        this.context = context;
    }

    @Override
    public Fragment createFragment(int position) {
        if (pages.get(position).type.equals("audio"))
            return(PageAudioFragment.newInstance(context,(PageAudioFragment) pages.get(position)));
        if (pages.get(position).type.equals("video"))
            return(PageVideoFragment.newInstance((PageVideoFragment) pages.get(position)));
        return null;
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }
}
