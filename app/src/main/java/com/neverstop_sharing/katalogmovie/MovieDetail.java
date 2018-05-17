package com.neverstop_sharing.katalogmovie;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.CONTENT_URI;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.ID_CONTENT;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.IMG_URL;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.OVERVIEW;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.RELEASE_DATE;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.TITLE;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.VOTE_COUNT;

public class MovieDetail extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_ID_CONTENT = "extra_id_content";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_POSTER = "extra_poster";
    public static final String EXTRA_TANGGAL = "extra_tanggal";
    public static final String EXTRA_VOTE_AVERAGE = "extra_vote_average";
    public static final String EXTRA_VOTE_COUNT = "extra_vote_count";
    public static final String EXTRA_DESKRIPSI = "extra_deskripsi";
    private TextView release_date,vote_average,vote_count,deskripsi_detail,judul_detail;
    private ImageView poster;
    private Button btnFav;

    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE = 301;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        release_date = (TextView)findViewById(R.id.release_date_detail);
        vote_average = (TextView)findViewById(R.id.vote_average);
        vote_count = (TextView)findViewById(R.id.vote_count);
        judul_detail = (TextView)findViewById(R.id.title_detail);
        deskripsi_detail = (TextView)findViewById(R.id.deskripsi_detail);
        poster = (ImageView)findViewById(R.id.poster_detail);
        btnFav = (Button)findViewById(R.id.btn_fav_detail);
        btnFav.setOnClickListener(this);


        Picasso.get().load(getIntent().getStringExtra(EXTRA_POSTER)).into(poster);
        release_date.setText(getIntent().getStringExtra(EXTRA_TANGGAL));
        vote_count.setText(getIntent().getStringExtra(EXTRA_VOTE_COUNT));
        vote_average.setText(getIntent().getStringExtra(EXTRA_VOTE_AVERAGE));
        deskripsi_detail.setText(getIntent().getStringExtra(EXTRA_DESKRIPSI));
        judul_detail.setText(getIntent().getStringExtra(EXTRA_TITLE));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_fav_detail){
            String id_content = getIntent().getStringExtra(EXTRA_ID_CONTENT);
            String title = getIntent().getStringExtra(EXTRA_TITLE);
            String img = getIntent().getStringExtra(EXTRA_POSTER);
            String release_date = getIntent().getStringExtra(EXTRA_TANGGAL);
            String vote_count = getIntent().getStringExtra(EXTRA_VOTE_COUNT);
            String overview = getIntent().getStringExtra(EXTRA_DESKRIPSI);

            ContentValues values = new ContentValues();
            values.put(ID_CONTENT,id_content);
            values.put(TITLE,title);
            values.put(IMG_URL,img);
            values.put(RELEASE_DATE,release_date);
            values.put(VOTE_COUNT,vote_count);
            values.put(OVERVIEW,overview);

            getContentResolver().insert(CONTENT_URI,values);
            setResult(RESULT_ADD);
        }
    }
}
