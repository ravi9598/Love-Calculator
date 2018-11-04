package com.instilive.lovely;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private  String userName;
    private String userID;

    private static final String SHARED_PREF = "SharedPref";
    private static final String IS_TABLE_CREATED="isTableCreated";


    public SharedPrefManager(Context context){
        sharedPref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void setIsTableCreated(boolean isTableCreated){
        editor.putBoolean(IS_TABLE_CREATED,isTableCreated).apply();
    }

    public boolean isTableCreated(){
        return sharedPref.getBoolean(IS_TABLE_CREATED,false);
    }
}
