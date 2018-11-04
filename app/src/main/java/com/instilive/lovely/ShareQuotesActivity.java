package com.instilive.lovely;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class ShareQuotesActivity extends AppCompatActivity {

    private Bundle bundle;
    private EditText etQuotes;
    private Button btnShare;
    private ShareActionProvider shareActionProvider;
    private ImageView randomImage;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_quotes);

        toolbar=(Toolbar)findViewById(R.id.shareQuoteToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Share your quote");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareQuotesActivity.this, HomePageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


        randomImage=(ImageView)findViewById(R.id.shareQuoteImage);
        etQuotes=(EditText)findViewById(R.id.quotes);
        btnShare=(Button)findViewById(R.id.shareQuoteBtn);

        bundle=getIntent().getExtras();
        etQuotes.setText(bundle.getString("Quote"));
        etQuotes.setSelection(etQuotes.getText().length());

        Random rand = new Random();
        int  random = rand.nextInt(23) + 1;
        setRandomImage(random);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote=etQuotes.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,quote);
                startActivity(Intent.createChooser(intent,"Share your quote via..."));
            }
        });

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
}
