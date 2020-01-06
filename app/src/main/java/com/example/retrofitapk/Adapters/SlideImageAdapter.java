package com.example.retrofitapk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.R;

import java.util.ArrayList;
import java.util.List;

public class SlideImageAdapter extends PagerAdapter {

    private List<Categories.CatData> catData;
    private LayoutInflater inflater;
    private Context context;

    public SlideImageAdapter(Context context, List<Categories.CatData> catData) {
        this.context = context;
        this.catData=catData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return catData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide_cat, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        TextView catName = myImageLayout.findViewById(R.id.name);
        //myImage.setImageResource(images.get(position));
        Glide.with(context).load(catData.get(position).getPhoto()).into(myImage);
        catName.setText(catData.get(position).getCategory_en());
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
