package com.neverstop_sharing.katalogmovie;

import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    MovieAdapter adapter;
    Button btnPlaying,btnUpcoming;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlaying = (Button)findViewById(R.id.btn_playing);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        PlayingFragment playingFragment = new PlayingFragment();
        mFragmentTransaction.add(R.id.frame_container,playingFragment,PlayingFragment.class.getSimpleName());
        mFragmentTransaction.commit();
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_playing){
            Toast.makeText(MainActivity.this,"PLAYING LIST",Toast.LENGTH_SHORT).show();
        }
    }
}
