package com.instilive.lovely;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private String[] quotes={"When the angels ask what I most loved about life, I’ll say you.",
            "Love isn’t complicated, people are.",
            "Roses are red violets are blue don’t cast me away because I love",
            "I will never regret loving you, only believing you loved me too.",
            "You and me make a wonderful WE.",
            "Your cute smile is all I need to battle all struggles in my life.",
            "Some love one, some love two. I love one that is you.",
            "Great loves too must be endure.",
            "And in her smile I see something more beautiful than the stars.",
            "I want to run away with you. Where there is only you and me.",
            "I still fall for you every day.",
            "Sometimes I can’t see myself when I’m with you. I can only just see you.",
            "A heart in love with beauty never grows old.",
            "I am catastrophically in love with you.",
            "Kiss me, and you may see stars, love me and I will give them to you.",
            "Friendship is Love without his wings.",
            "Life is Messy. Love is Messier.",
            "Close your eyes and I’ll kiss you, Tomorrow I’ll miss you.",
            "Love is heat. You are sweet. When two Lips are meet. Love is completed.",
            "If I know what love is, it is because of You.",
            "Your smile make me the happiest person alive.",
            "Like the ocean finds the shore, I’ll always find you.",
            "You may not notice, but I’m totally in love with you.",
            "Please stay, and if you can’t stay, Take me!",
            "Dreams do come true, I know, because I found you.",
            "One day, I’ll marry you.",
            "I’d choose you again and again.",
            "The best feeling is when I look at you and you’re already staring.",
            "I hate the idea of anyone else having you.",
            "The truth is everyone is going to hurt you. You just got to find the ones worth suffering for."};

    private TextView result,randomQuotes,lover_1,lover_2;
    private Bundle bundle;
    private Toolbar toolbar;
    private ImageView btn;
    private SQLiteDatabase database;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bundle=getIntent().getExtras();

        int initialResult=0;
        final int finalResult=bundle.getInt("result");
        result=(TextView)findViewById(R.id.loveResult);
        toolbar=(Toolbar)findViewById(R.id.resultToolbar);
        randomQuotes=(TextView)findViewById(R.id.result_quote);
        btn=(ImageView)findViewById(R.id._image);
        lover_1=(TextView)findViewById(R.id.result_lover_1);
        lover_2=(TextView)findViewById(R.id.result_lover_2);
        btnShare=(Button)findViewById(R.id.result_share_btn);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, HomePageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        String query="INSERT INTO";

        Random random=new Random();
        int number=random.nextInt(29);
        final String text=quotes[number];
        randomQuotes.setText(text);

        lover_1.setText(bundle.getString("lover_1"));
        lover_2.setText(bundle.getString("lover_2"));

        database=this.openOrCreateDatabase("DATABASE",android.content.Context.MODE_PRIVATE ,null);
        database.execSQL("create table if not exists loveHistory (Lover1 varchar, Lover2 varchar, Percentage int, Quotes text)");
        database.execSQL("insert into loveHistory values('"+bundle.getString("lover_1")+"','"+bundle.getString("lover_2")+"','"+finalResult+"','"+text+"')");

        animateTextView(initialResult,finalResult,result);
        animateButton();

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,bundle.getString("lover_1")+"+"+bundle.getString("lover_2")+"="+finalResult+"%"+"\n"+text+"\n"+"#Love Calculator");
                startActivity(Intent.createChooser(intent,"Share your thought via..."));
            }
        });

    }

    public void animateTextView(int initialValue, int finalValue, final TextView textview) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(1.0f);
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

    private void animateButton() {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        btn.startAnimation(animation);
    }

}
