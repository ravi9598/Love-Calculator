package com.instilive.lovely;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainFragment extends Fragment{

    private SharedPrefManager sharedPrefManager;
    private String userName;
    private SQLiteDatabase database;
    private Handler handler;
    private EditText etLover1,etLover2;
    private ImageView btn;
    private String strLover1,strLover2;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        btn=(ImageView) view.findViewById(R.id.btn);
        animateButton();

        sharedPrefManager=new SharedPrefManager(getActivity());
        database=getActivity().openOrCreateDatabase("DATABASE",android.content.Context.MODE_PRIVATE ,null);
        database.execSQL("create table if not exists loveHistory (Lover1 varchar, Lover2 varchar, Percentage int)");

        userName=sharedPrefManager.getUserName();
        etLover1=(EditText)view.findViewById(R.id.lover1);
        etLover2=(EditText)view.findViewById(R.id.lover2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLover1=etLover1.getText().toString();
                strLover2=etLover2.getText().toString();
                String concat = String.valueOf(strLover1).concat(String.valueOf(strLover2)).toUpperCase();
                if ((strLover1.toString().trim().length() == 0) || (strLover2.toString().trim().length() == 0))
                {
                    Toast.makeText(getActivity(), "Please fill both the fields ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    int sum = 0;
                    for (int i = 0; i < concat.length(); i++)
                    {
                        char character = concat.charAt(i);
                        int ascii = (int) character;
                        sum += ascii;
                    }
                    int finalResult=sum%100;
                    if(finalResult<=30)
                    {
                        finalResult=(100-finalResult)/2;
                    }
                    database.execSQL("insert into loveHistory values('"+strLover1+"','"+strLover2+"','"+finalResult+"')");
                    Intent intent=new Intent(getActivity(),ResultActivity.class);
                    intent.putExtra("result",finalResult);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void animateButton() {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        btn.startAnimation(animation);
    }
}
