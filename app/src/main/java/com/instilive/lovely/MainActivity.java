package com.instilive.lovely;

import android.app.ProgressDialog;
import android.content.Intent;
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

    private String checkLoggedIn;
    private String isLoggedIn="NO";
    private SQLiteDatabase database;
    private EditText etUserName;
    private Button btnRegister;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMale,radioButtonFemale;
    String userName;
    String gender=null;
    private String userID;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference=FirebaseDatabase.getInstance().getReference().child("lovelyUsers");

        database=openOrCreateDatabase("MyDB",android.content.Context.MODE_PRIVATE ,null);
        database.execSQL("create table if not exists loveHistory (Name varchar, isLoggedIn varchar,UserName varchar,UserID varchar)");

        Cursor cursor=database.rawQuery("select * from loveHistory",null);

        while (cursor.moveToNext())
        {
            checkLoggedIn=cursor.getString(1);

        }

        if (checkLoggedIn.equals("YES"))
        {
            Toast.makeText(this,checkLoggedIn, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
            startActivity(intent);
            finish();
        }
 
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
                    isLoggedIn="YES";
                    database.execSQL("insert into loveHistory values('"+userName+"','"+isLoggedIn+"','"+userName+"','"+userID+"')");
                    Random rand = new Random();
                    long number = rand.nextInt(9999999)+1;
                    userID=userName+number+gender;
                    DatabaseReference userDatabaseReference=databaseReference.child(userID);
                    userDatabaseReference.child("Credentials").child("Name").setValue(userName);
                    userDatabaseReference.child("Credentials").child("Gender").setValue(gender);
                    Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
                    intent.putExtra("userID",userID);
                    intent.putExtra("userName",userName);
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
