package com.example.retrofitapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.retrofitapk.Adapters.SimpleFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class PollsActivity extends AppCompatActivity {

    int item;
    public ViewPager viewPager;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);

        //intent data
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            item = extras.getInt("item");
        } else {
            item = (int) savedInstanceState.getSerializable("item");
        }

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(simpleFragmentPagerAdapter);

        tabLayout = findViewById(R.id.sliding_tab);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(item);

    }
}
