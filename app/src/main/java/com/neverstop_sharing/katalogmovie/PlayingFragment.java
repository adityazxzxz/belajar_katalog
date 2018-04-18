package com.neverstop_sharing.katalogmovie;


import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>{
    Context context;
    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";
    MovieAdapter adapter;

    public PlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter = new MovieAdapter(context);
        adapter.notifyDataSetChanged();
        View view = inflater.inflate(R.layout.fragment_playing,container,false);
        EditText editMovie = (EditText)view.findViewById(R.id.txt_film);
        Button btnCari = (Button)view.findViewById(R.id.btn_playing);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movie = editMovie.getText().toString();
                if (TextUtils.isEmpty(movie))return;
                Bundle bundle = new Bundle();
                bundle.putString(EXTRAS_MOVIE,movie);
                getLoaderManager().restartLoader(0,bundle,MainActivity.this);
            }
        });

        String movie = editMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE,movie);

        getLoaderManager().initLoader(0,bundle,this);


        return inflater.inflate(R.layout.fragment_playing, container, false);


    }


    @Override
    public android.content.Loader<ArrayList<MovieItems>> onCreateLoader(int i, Bundle args) {
        String kumpulanMovie = "";
        if (args != null){
            kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(context,kumpulanMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }



}
