package com.neverstop_sharing.katalogmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_POSTER = "extra_poster";
    public static final String EXTRA_TANGGAL = "extra_tanggal";
    public static final String EXTRA_VOTE_AVERAGE = "extra_vote_average";
    public static final String EXTRA_VOTE_COUNT = "extra_vote_count";
    public static final String EXTRA_DESKRIPSI = "extra_deskripsi";
    private TextView release_date,vote_average,vote_count,deskripsi_detail,judul_detail;
    private ImageView poster;

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

        Picasso.get().load(getIntent().getStringExtra(EXTRA_POSTER)).into(poster);
        release_date.setText(getIntent().getStringExtra(EXTRA_TANGGAL));
        vote_count.setText(getIntent().getStringExtra(EXTRA_VOTE_COUNT));
        vote_average.setText(getIntent().getStringExtra(EXTRA_VOTE_AVERAGE));
        deskripsi_detail.setText(getIntent().getStringExtra(EXTRA_DESKRIPSI));
        judul_detail.setText(getIntent().getStringExtra(EXTRA_TITLE));





    }
}
