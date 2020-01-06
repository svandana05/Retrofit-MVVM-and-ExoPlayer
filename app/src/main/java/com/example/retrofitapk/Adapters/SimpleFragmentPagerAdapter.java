package com.example.retrofitapk.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.retrofitapk.ActivePollFragment;
import com.example.retrofitapk.ExpiredPollFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ActivePollFragment();
        }else {
            return new ExpiredPollFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Active Poll";
            case 1:
                return "Expired Poll";
                default:
                    return null;
        }
    }
}
