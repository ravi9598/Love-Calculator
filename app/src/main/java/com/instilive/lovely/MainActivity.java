package com.instilive.lovely;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SharedPrefManager sharedPrefManager;
    private EditText etUserName;
    private Button btnRegister;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMale,radioButtonFemale;
    private String userName;
    private String gender=null;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager=new SharedPrefManager(this);

        etUserName=(EditText)findViewById(R.id.userName);
        btnRegister=(Button)findViewById(R.id.registerBtn);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioButtonMale=(RadioButton)findViewById(R.id.male);
        radioButtonFemale=(RadioButton)findViewById(R.id.female);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.male)
                {
                    gender="male";
                }
                else if (checkedId==R.id.female)
                {
                    gender="female";
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=etUserName.getText().toString();
                if (!TextUtils.isEmpty(userName) && gender!=null)
                {
                    Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
                    SharedPreferences pref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("userName", userName);
                    editor.apply();
                    sharedPrefManager.setIsLoggedIn(true);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Credentials can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
