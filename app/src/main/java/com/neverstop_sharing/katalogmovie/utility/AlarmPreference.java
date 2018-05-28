package com.neverstop_sharing.katalogmovie.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AlarmPreference {
    private final String PREF_NAME = "AlarmPreference";
    private final String KEY_REPEATING_TIME_RELEASE = "repeatingTime";
    private final String KEY_REPEATING_MESSAGE_RELEASE = "repeatingMessage";

    private final String KEY_REPEATING_TIME_REMINDER = "repeatingTimeReminder";
    private final String KEY_REPEATING_MESSAGE_REMINDER = "repeatingMessageReminder";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public AlarmPreference(Context context){
        mSharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public String getKEY_REPEATING_TIME_REMINDER() {
        return KEY_REPEATING_TIME_REMINDER;
    }

    public String getKEY_REPEATING_MESSAGE_REMINDER() {
        return KEY_REPEATING_MESSAGE_REMINDER;
    }

    public void setRepeatingTimeRelease(String time){
        editor.putString(KEY_REPEATING_TIME_RELEASE,time);
        editor.commit();

    }

    public String getRepeatingTimeRelease(){
        return mSharedPreferences.getString(KEY_REPEATING_TIME_RELEASE,null);
    }

    public void setRepeatingTimeReminder(String time){
        editor.putString(KEY_REPEATING_TIME_REMINDER,time);
        editor.commit();

    }

    public String getRepeatingTimeReminder(){
        return mSharedPreferences.getString(KEY_REPEATING_TIME_REMINDER,null);
    }

    public void setRepeatingMessageRelease(String message){
        editor.putString(KEY_REPEATING_MESSAGE_RELEASE,message);
        editor.commit();
    }

    public String getRepeatingMessage(){
        return mSharedPreferences.getString(KEY_REPEATING_MESSAGE_RELEASE,null);
    }

    public void setRepeatingMessageReminder(String message){
        editor.putString(KEY_REPEATING_MESSAGE_REMINDER,message);
        editor.commit();
    }

    public String getRepeatingMessageReminder(){
        return mSharedPreferences.getString(KEY_REPEATING_MESSAGE_REMINDER,null);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }


}
