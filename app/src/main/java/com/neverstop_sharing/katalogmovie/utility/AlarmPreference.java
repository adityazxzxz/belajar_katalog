package com.neverstop_sharing.katalogmovie.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AlarmPreference {
    private final String PREF_NAME = "AlarmPreference";
    private final String KEY_ONE_TIME_DATE = "oneTimDate";
    private final String KEY_ONE_TIME_TIME = "oneTimeTime";
    private final String KEY_ONE_TIME_MESSAGE = "oneTimeMessage";
    private final String KEY_REPEATING_TIME = "repeatingTime";
    private final String KEY_REPEATING_MESSAGE = "repeatingMessage";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public AlarmPreference(Context context){
        mSharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public void setRepeatingTime(String time){
        editor.putString(KEY_REPEATING_TIME,time);
        editor.commit();

    }

    public String getRepeatingTime(){
        return mSharedPreferences.getString(KEY_REPEATING_TIME,null);
    }

    public void setRepeatingMessage(String message){
        editor.putString(KEY_REPEATING_MESSAGE,message);
        editor.commit();
    }

    public String getRepeatingMessage(){
        return mSharedPreferences.getString(KEY_REPEATING_MESSAGE,null);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }


}