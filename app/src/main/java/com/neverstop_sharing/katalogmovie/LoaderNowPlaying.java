package com.neverstop_sharing.katalogmovie;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class LoaderNowPlaying extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems>listMovie;
    private String kumpulanFilm;
    private boolean mHasResult = false;

    public LoaderNowPlaying(Context context, String kumpulanFilm){
        super(context);
        onContentChanged();
        this.kumpulanFilm = kumpulanFilm;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()){
            forceLoad();
        }else if (mHasResult){
            deliverResult(listMovie);
        }
    }

    @Override
    public void deliverResult(ArrayList<MovieItems> data) {
        listMovie = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResource(listMovie);
            listMovie = null;
            mHasResult = false;
        }
    }

    public static final String API_KEY = "93ede33b0c095238b2240c04b1e9e8ca";


    private void onReleaseResource(ArrayList<MovieItems> listMovie) {
    }

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = "";
        if (kumpulanFilm == "upcoming"){
             url = "https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language=en-US";
        }else{
             url += "https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en-US";
        }
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    Log.d("TAGRESPONSE",responseObject.toString());
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i=0;i<list.length();i++){
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItemses.add(movieItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }
}
