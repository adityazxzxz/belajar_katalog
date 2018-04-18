package com.neverstop_sharing.katalogmovie;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aditya.pratama on 3/27/2018.
 */

public class MovieAdapter extends BaseAdapter {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems>items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        Log.d("MDATA_SIZE", String.valueOf(mData));
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_items,null);
            holder.textViewJudulMovie = (TextView)convertView.findViewById(R.id.judulMovie);
            holder.textViewDeskripsi = (TextView)convertView.findViewById(R.id.deskripsi);
            holder.textViewTanggal = (TextView)convertView.findViewById(R.id.tglMovie);
            holder.poster = (ImageView)convertView.findViewById(R.id.img_poster);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("TAG",mData.get(position).getDeskripsi_full());
                Intent intent = new Intent(context,MovieDetail.class);
                intent.putExtra(MovieDetail.EXTRA_TITLE,mData.get(position).getJudul());
                intent.putExtra(MovieDetail.EXTRA_POSTER,mData.get(position).getPoster_big());
                intent.putExtra(MovieDetail.EXTRA_TANGGAL,mData.get(position).getTanggal());
                intent.putExtra(MovieDetail.EXTRA_VOTE_AVERAGE,mData.get(position).getVote_average());
                intent.putExtra(MovieDetail.EXTRA_VOTE_COUNT,mData.get(position).getVote_count());
                intent.putExtra(MovieDetail.EXTRA_DESKRIPSI,mData.get(position).getDeskripsi_full());
                context.startActivity(intent);



            }
        });
        holder.textViewJudulMovie.setText(mData.get(position).getJudul());
        holder.textViewDeskripsi.setText(mData.get(position).getDeskripsi());
        holder.textViewTanggal.setText(mData.get(position).getTanggal());
        Picasso.get().load(mData.get(position).getPoster()).into(holder.poster);
        return convertView;
    }


    private class ViewHolder {
         TextView textViewJudulMovie;
         TextView textViewDeskripsi;
         TextView textViewTanggal;
         ImageView poster;
    }
}
