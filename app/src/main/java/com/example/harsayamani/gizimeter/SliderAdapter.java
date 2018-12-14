package com.example.harsayamani.gizimeter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SliderAdapter extends PagerAdapter {
    private Context context;

    // list of images
    private int[] lst_images = {
            R.drawable.quest_1,
            R.drawable.quest_2,
            R.drawable.slide_3,
    };

    // list of background colors
    private int[]  lst_backgroundcolor = {
            Color.rgb(239,85,85),
            Color.rgb(239,85,85),
            Color.rgb(239,85,85),
    };


    SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view== object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.design_slide,container,false);
        LinearLayout layoutslide =  view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = view.findViewById(R.id.slideimg);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
