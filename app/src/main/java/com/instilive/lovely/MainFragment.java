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
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ravi on 2/23/2018.
 */

public class MainFragment extends Fragment{

    private Bundle bundle;
    private DatabaseReference databaseReference;
    private String uID=null;
    private String userName;
    private SQLiteDatabase database;
    private Handler handler;
    private TextView res;
    private EditText etLover1,etLover2;
    private Button btn;
    private String strLover1,strLover2;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        bundle=getActivity().getIntent().getExtras();
//        database=getActivity().openOrCreateDatabase("MyDB",android.content.Context.MODE_PRIVATE ,null);
//        Cursor cursor=database.rawQuery("select * from loveHistory",null);
        userName=bundle.getString("userName");
        uID=bundle.getString("userID");

        databaseReference= FirebaseDatabase.getInstance().getReference().child("lovelyUsers").child(uID);

        res=(TextView)view.findViewById(R.id.res);
        etLover1=(EditText)view.findViewById(R.id.lover1);
        etLover2=(EditText)view.findViewById(R.id.lover2);
        btn=(Button)view.findViewById(R.id.btn);

        database=getActivity().openOrCreateDatabase("DATABASE",android.content.Context.MODE_PRIVATE ,null);
        database.execSQL("create table if not exists loveHistory (Lover1 varchar, Lover2 varchar, Percentage int)");

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
                    int initialResult=0;
                    int finalResult=sum%100;
                    DatabaseReference historyReference=databaseReference.push();
                    historyReference.child("lover1").setValue(strLover1);
                    historyReference.child("lover2").setValue(strLover2);
                    historyReference.child("result").setValue(finalResult);
                    database.execSQL("insert into loveHistory values('"+strLover1+"','"+strLover2+"','"+finalResult+"')");
                    animateTextView(initialResult,finalResult,res);

                }
            }
        });

        return view;
    }

    public void animateTextView( int initialValue, int finalValue, final TextView textview) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.5f);
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
