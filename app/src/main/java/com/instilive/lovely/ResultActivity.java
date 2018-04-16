package com.instilive.lovely;

import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private TextView res;
    private Bundle bundle;
    private ImageView randomImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bundle=getIntent().getExtras();

        int initialResult=0;
        int finalResult=bundle.getInt("result");
        res=(TextView)findViewById(R.id.loveResult);
        animateTextView(initialResult,finalResult,res);
        //randomImage=(ImageView)findViewById(R.id.random_image);
        //Random rand = new Random();
       // int  random = rand.nextInt(23) + 1;
        //setRandomImage(random);

    }

    private void setRandomImage(int random) {
        switch (random)
        {
            case 1:  randomImage.setImageResource(R.drawable.a);
                break;
            case 2:  randomImage.setImageResource(R.drawable.b);
                break;
            case 3:  randomImage.setImageResource(R.drawable.c);
                break;
            case 4:  randomImage.setImageResource(R.drawable.d);
                break;
            case 5:  randomImage.setImageResource(R.drawable.e);
                break;
            case 6:  randomImage.setImageResource(R.drawable.f);
                break;
            case 7:  randomImage.setImageResource(R.drawable.g);
                break;
            case 8:  randomImage.setImageResource(R.drawable.h);
                break;
            case 9:  randomImage.setImageResource(R.drawable.i);
                break;
            case 10:  randomImage.setImageResource(R.drawable.j);
                break;
            case 11:  randomImage.setImageResource(R.drawable.k);
                break;
            case 12:  randomImage.setImageResource(R.drawable.l);
                break;
            case 13:  randomImage.setImageResource(R.drawable.m);
                break;
            case 14:  randomImage.setImageResource(R.drawable.n);
                break;
            case 15:  randomImage.setImageResource(R.drawable.o);
                break;
            case 16:  randomImage.setImageResource(R.drawable.p);
                break;
            case 17:  randomImage.setImageResource(R.drawable.q);
                break;
            case 18:  randomImage.setImageResource(R.drawable.r);
                break;
            case 19:  randomImage.setImageResource(R.drawable.s);
                break;
            case 20:  randomImage.setImageResource(R.drawable.t);
                break;
            case 21:  randomImage.setImageResource(R.drawable.u);
                break;
            case 22:  randomImage.setImageResource(R.drawable.v);
                break;
            case 23:  randomImage.setImageResource(R.drawable.w);
                break;
        }
    }

    public void animateTextView( int initialValue, int finalValue, final TextView textview) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(1.5f);
        int start = Math.min(initialValue, finalValue);
        int end = Math.max(initialValue, finalValue);
        int difference = Math.abs(finalValue - initialValue);
        Handler handler = new Handler();
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 100) * count;
            final int finalCount = ((initialValue > finalValue) ? initialValue - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textview.setText(finalCount + "%");
                }
            }, time);
        }
    }
}
