package eneverstop.adityapratama.katalogfavorite;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.CONTENT_URI;
import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.KatalogColumn.ID_CONTENT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    private Cursor list;
    private KatalogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Movie Favorite");
        recyclerView = (RecyclerView)findViewById(R.id.rv_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        adapter = new KatalogAdapter(this);
        adapter.setListMovie(list);
        recyclerView.setAdapter(adapter);

        new LoadMovieAsync().execute();

    }
    @Override
    public void onClick(View v) {

    }

    private class LoadMovieAsync extends AsyncTask<Void,Void,Cursor>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            String selection = "GROUP BY "+ID_CONTENT;
            return getContentResolver().query(CONTENT_URI, null,selection,null,null);
        }

        @Override
        protected void onPostExecute(Cursor movies) {
            super.onPostExecute(movies);

            list = movies;
            adapter.setListMovie(movies);
            adapter.notifyDataSetChanged();

            if (list.getCount() == 0){

            }
        }

    }


}
