package eneverstop.adityapratama.katalogfavorite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.KatalogColumn.IMG_URL;
import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.KatalogColumn.OVERVIEW;
import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.KatalogColumn.RELEASE_DATE;
import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.KatalogColumn.TITLE;
import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.getColumnString;

public class KatalogAdapter extends RecyclerView.Adapter<KatalogAdapter.KatalogViewHolder> {

    private Cursor listMovie;
    private Activity activity;

    public KatalogAdapter(Activity activity){
        this.activity = activity;
    }

    public void setListMovie(Cursor listMovie) {
        this.listMovie = listMovie;
    }

    @Override
    public KatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_card_view,parent,false);
        return new KatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KatalogViewHolder holder, int position) {
        final MovieItem movieItem = getItem(position);
        holder.tvTitle.setText(movieItem.getTitle());
        holder.tvOverview.setText(movieItem.getDescription().substring(0,25));
        holder.tvReleaseDate.setText(movieItem.getDate());
        Picasso.get().load(movieItem.getImage()).into(holder.poster);
    }

    private MovieItem getItem(int position) {
        if (!listMovie.moveToPosition(position)){
            throw new IllegalStateException("Position invalid");
        }
        return new MovieItem(listMovie);
    }

    @Override
    public int getItemCount() {
        if (listMovie == null) return 0;
        return listMovie.getCount();
    }

    class KatalogViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvOverview,tvReleaseDate;
        ImageView poster;

        public KatalogViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_item_judul);
            tvOverview = (TextView)itemView.findViewById(R.id.tv_item_deskripsi);
            tvReleaseDate = (TextView)itemView.findViewById(R.id.tv_item_tanggal);
            poster = (ImageView)itemView.findViewById(R.id.img_item_poster);
        }
    }
}
