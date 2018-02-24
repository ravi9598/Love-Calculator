package com.instilive.lovely;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener{

    private CircleImageView gitLogo,LinkedInRavi,LinkedInBittu,LinkedInAtul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_us);

        gitLogo=(CircleImageView)findViewById(R.id.gitLogo);
        LinkedInRavi=(CircleImageView)findViewById(R.id.ravi);
        LinkedInBittu=(CircleImageView)findViewById(R.id.bittu);
        LinkedInAtul=(CircleImageView)findViewById(R.id.atul);

        LinkedInRavi.setOnClickListener(this);
        LinkedInBittu.setOnClickListener(this);
        LinkedInAtul.setOnClickListener(this);
        gitLogo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.ravi)
        {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ravi-prakash-654b0a112"));
            startActivity(i);
        }
        if (view.getId()==R.id.bittu)
        {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/balram-vishwakarma-733a09144/"));
            startActivity(i);
        }
        if (view.getId()==R.id.atul)
        {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/atul-sagar-80004a143/"));
            startActivity(i);
        }
        if (view.getId()==R.id.gitLogo)
        {
            Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ravi9598/Lovely"));
            startActivity(i);
        }
    }
}
