package com.neverstop_sharing.katalogmovie;

import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.neverstop_sharing.katalogmovie.utility.AlarmPreference;
import com.neverstop_sharing.katalogmovie.utility.AlarmReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    MovieAdapter adapter;
    Button btnPlaying,btnUpcoming,btnFavorite;
    private AlarmPreference alarmPreference;
    private AlarmReceiver alarmReceiver;
    private Calendar calOneTimeDate, calOneTimeTime,calRepeatTimeTime;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmReceiver = new AlarmReceiver();
        alarmPreference = new AlarmPreference(this);
        btnPlaying = (Button)findViewById(R.id.btn_playing);
        btnPlaying.setText(String.format(getResources().getString(R.string.now_playing)));
        btnPlaying.setOnClickListener(this);
        btnUpcoming = (Button)findViewById(R.id.btn_upcoming);
        btnUpcoming.setText(String.format(getResources().getString(R.string.upcoming)));
        btnUpcoming.setOnClickListener(this);
        btnFavorite = (Button)findViewById(R.id.btn_fav);
        btnFavorite.setOnClickListener(this);
        firstFragment();
        calRepeatTimeTime = Calendar.getInstance();

        setAlarm();
    }

    private void setAlarm() {
        //not yet for validation release date
        String timerRelease = "11:40";
        String timerReminder = "11:41";
        sp = getSharedPreferences("AlarmPreference",MODE_PRIVATE);
        if(!sp.contains("repeatingTime")){
            alarmPreference.setRepeatingTimeRelease(timerRelease);
            alarmPreference.setRepeatingMessageReminder("test release");
            alarmReceiver.setRepeatingAlarm(this,AlarmReceiver.TYPE_REPEATING,alarmPreference.getRepeatingTimeRelease(),alarmPreference.getRepeatingMessage(),101);
            Log.d("SharedPrefStats","timereleaseAdd");
        }

        if(!sp.contains("repeatingTimeReminder")){
            alarmPreference.setRepeatingTimeReminder(timerReminder);
            alarmPreference.setRepeatingMessageReminder("Test reminder");
            alarmReceiver.setRepeatingAlarm(this,AlarmReceiver.TYPE_REPEATING,alarmPreference.getRepeatingTimeReminder(),alarmPreference.getRepeatingMessageReminder(),102);
            Log.d("SharedPrefStats","timereminderadd");
        }







}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_bahasa:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                return true;
            case R.id.menu_search:
                Intent searchIntent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(searchIntent);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_playing){
            nowPlayingFragment();
        }
        if(v.getId()==R.id.btn_upcoming){
            upComingFragment();
        }
        if (v.getId()==R.id.btn_fav){
            Intent searchIntent = new Intent(MainActivity.this,MovieFavorite.class);
            startActivity(searchIntent);
            //
        }
    }

    private void firstFragment() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        PlayingFragment playingFragment = new PlayingFragment();
        mFragmentTransaction.add(R.id.frame_container,playingFragment,PlayingFragment.class.getSimpleName());
        mFragmentTransaction.commit();
    }

    private void nowPlayingFragment() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        PlayingFragment playingFragment = new PlayingFragment();
        mFragmentTransaction.replace(R.id.frame_container,playingFragment,PlayingFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    private void upComingFragment(){
        FragmentManager mFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        UpcomingFragment upcomingFragment = new UpcomingFragment();
        mFragmentTransaction.replace(R.id.frame_container,upcomingFragment,UpcomingFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }



}
