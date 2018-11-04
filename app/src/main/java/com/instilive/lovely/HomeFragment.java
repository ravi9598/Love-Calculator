package com.instilive.lovely;

import android.content.Intent;
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
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeFragment extends Fragment{

    private SQLiteDatabase database;
    private EditText etLover1,etLover2;
    private ImageView btn;
    private String strLover1,strLover2;
    private SharedPrefManager sharedPrefManager;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPrefManager=new SharedPrefManager(getActivity());

        btn=(ImageView) view.findViewById(R.id.btn);


        etLover1=(EditText)view.findViewById(R.id.lover1);
        etLover2=(EditText)view.findViewById(R.id.lover2);

        etLover1.setText("");
        etLover2.setText("");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLover1=etLover1.getText().toString();
                strLover2=etLover2.getText().toString();
                String concat = String.valueOf(strLover1).concat(String.valueOf(strLover2)).toUpperCase();
                if ((strLover1.trim().length() == 0) || (strLover2.trim().length() == 0))
                {
                    Toast.makeText(getActivity(), "Please fill both the fields ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    sharedPrefManager.setIsTableCreated(true);
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
                    Intent intent=new Intent(getActivity(),ResultActivity.class);
                    intent.putExtra("result",finalResult);
                    intent.putExtra("lover_1",strLover1);
                    intent.putExtra("lover_2",strLover2);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

}
