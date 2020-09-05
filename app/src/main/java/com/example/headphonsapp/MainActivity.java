package com.example.headphonsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity implements HeadAdapter.RecViewOnclickIteam {
    ArrayList<HeadModel> headList;
    HeadAdapter adapter;
    RecyclerView rv;
    ImageView imgView;
    Context cotext = MainActivity.this;
    ConstraintLayout parentLAyout;
    LinearLayout detaLayout, descLayout;

    int[] imgList = {R.drawable.red_headphone, R.drawable.black_headphone, R.drawable.dark_blue_headphone, R.drawable.yellow_headphone};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List
        headList = new ArrayList<>();
        for (int i : imgList) {
            headList.add(new HeadModel(i));
        }

        //adapter
        adapter = new HeadAdapter(headList, cotext);
        adapter.setRecViewOnclickIteam(this);

        //rv
        rv = findViewById(R.id.rv);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(cotext));

        // img color bitmap
        imgView = findViewById(R.id.img_view);
        parentLAyout = findViewById(R.id.parent_layout);
        descLayout = findViewById(R.id.linearLayout);
        detaLayout = findViewById(R.id.linearLayout3);
        creatPalette();

        //animation for rv and image
        setCustomAnimation(imgView, 200, R.anim.slider_in_lift);
        setCustomAnimation(rv, 300, R.anim.slider_in_right);
        //animation for desc and detail
        setCustomAnimation(descLayout, 500, R.anim.slider_in_top);
        setCustomAnimation(detaLayout, 400, R.anim.slider_in_down);


    }

    private void setCustomAnimation(View view, int i, int anim) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                anim);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(i);
        view.startAnimation(animation);


    }

    public void creatPalette() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        Palette palette = Palette.from(bitmap).generate();
        Palette.Swatch vibranSwatch = palette.getVibrantSwatch();
        Palette.Swatch darkSwatch = palette.getDarkMutedSwatch();
        if (vibranSwatch != null) {
            int backgrColor = vibranSwatch.getRgb();
            parentLAyout.setBackgroundColor(backgrColor);
            getWindow().setStatusBarColor(backgrColor);

        } else if (darkSwatch != null) {
            int backgrColor = darkSwatch.getRgb();
            parentLAyout.setBackgroundColor(backgrColor);
            getWindow().setStatusBarColor(backgrColor);
        } else {
            parentLAyout.setBackgroundColor(Color.BLACK);
            getWindow().setStatusBarColor(Color.BLACK);

        }
    }


    @Override
    public void onClick(int bostion) {
        imgView.setImageResource(headList.get(bostion).getImg());
        creatPalette();
    }
}
