package com.neverstop_sharing.katalogmovie;


import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {
    Context context;
    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";
    CardViewAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    public PlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing,container,false);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        Log.d("INFLATER","work");
        // Inflate the layout for this fragment
        adapter = new CardViewAdapter(getActivity());
        adapter.notifyDataSetChanged();


        if(adapter.getItemCount() == 0){
            Log.d("TESTER","kosong");
        }else{
            Log.d("TESTER","ada");
        }
        RecyclerView rvMovie = (RecyclerView)view.findViewById(R.id.rv_movies);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setAdapter(adapter);




        Bundle bundle = new Bundle();

        getLoaderManager().initLoader(0,bundle,this);


        return view;


    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        Log.e("Adapter", "onCreateLoader()");
        String kumpulanMovie = "";
        /*if(args != null){
            kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }*/
        //  return null;
        return new LoaderNowPlaying(getActivity(),"playing");
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        Log.e("Adapter", String.valueOf(data));
        adapter.setListMovie(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setListMovie(null);
    }



}
