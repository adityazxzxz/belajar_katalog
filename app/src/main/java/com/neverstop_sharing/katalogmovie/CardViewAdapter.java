package com.neverstop_sharing.katalogmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>{
    private Context context;
    //private LayoutInflater;
    private ArrayList<MovieItems> listMovie = new ArrayList<>();

    public CardViewAdapter(Context context){
        Log.e("Adapter", "constructor Called");
        this.context = context;
    }

    private ArrayList<MovieItems> getListMovie(){
        Log.e("Adapter", "getListMovie()");
        return listMovie;
    }
    public void setListMovie(ArrayList<MovieItems>listMovie){
        Log.e("Adapter", "setListMovie()");
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }

    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup context, int viewType) {

        View view = LayoutInflater.from(context.getContext()).inflate(R.layout.item_movie_card_view,context,false);
        Log.e("Adapter", "onCreateViewHolder");
        return new CardViewViewHolder(view);
        //R.layout.item_movie_card_view
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position) {
        Log.e("Adapter", "onBindViewHolder()");
        MovieItems a = getListMovie().get(position);
        Log.d("PESAN",a.getJudul());
        Glide.with(context)
                .load(a.getPoster_big())
                .override(350,550)
                .into(holder.imgPoster);
        holder.tvJudul.setText(a.getJudul());
        holder.tvDeskripsi.setText(a.getDeskripsi());
        //holder.tvTgl.setText(a.getTanggal());

    }

    @Override
    public int getItemCount() {
        Log.e("Adapter", "getItemCount()");
        return getListMovie().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;
        TextView tvJudul,tvDeskripsi,tvTgl;
        Button btnDetail,btnShare;

        CardViewViewHolder(View itemView) {
            super(itemView);
            Log.e("Adapter", "CardViewViewHolder");
            imgPoster = (ImageView)itemView.findViewById(R.id.img_item_poster);
            tvJudul = (TextView)itemView.findViewById(R.id.tv_item_judul);
            tvDeskripsi = (TextView)itemView.findViewById(R.id.tv_item_deskripsi);
            btnDetail = (Button)itemView.findViewById(R.id.btn_set_detail);
            btnShare = (Button)itemView.findViewById(R.id.btn_set_share);
        }
    }
}
