package com.instilive.lovely;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ravi on 2/24/2018.
 */

public class SharedPrefManager {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private  String userName;
    private String userID;

    private static final String SHARED_PREF = "SharedPref";
    private static final String IS_LOGGED_IN = "isLoggedIn";


    public SharedPrefManager(Context context){
        sharedPref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public boolean isLoggedIn() {
        return sharedPref.getBoolean(IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(boolean isLoggedIn){
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn).apply();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
