package com.neverstop_sharing.katalogmovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>{
    CardViewAdapter adapter;

    final static String EXTRAS_MOVIE = "extras_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        adapter = new CardViewAdapter(this);
        adapter.notifyDataSetChanged();
        RecyclerView rvSearch = (RecyclerView)findViewById(R.id.rv_search_movies);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        rvSearch.setAdapter(adapter);
        String movie = "";
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE,movie);
        getLoaderManager().initLoader(0,bundle,SearchActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        SearchView searchView = (SearchView)MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String movie = query;
                Bundle bundle = new Bundle();
                bundle.putString(EXTRAS_MOVIE,movie);
                getLoaderManager().restartLoader(0,bundle, SearchActivity.this);
                //Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        String kumpulanMovie = "";
        if (args != null){
            kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }
        //return new MyAsyncTaskLoader(this,kumpulanMovie);
        return new MyAsyncTaskLoader(this,kumpulanMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setListMovie(data);
    }



    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setListMovie(null);
    }
}
