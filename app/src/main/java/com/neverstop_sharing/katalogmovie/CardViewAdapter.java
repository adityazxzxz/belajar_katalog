package com.neverstop_sharing.katalogmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    private ArrayList<MovieItems> mData = new ArrayList<>();

    public CardViewAdapter(Context context){
        this.context = context;
        //LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void setData(ArrayList<MovieItems>items){
        mData = items;
        notifyDataSetChanged();
    }

    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_card_view,parent,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position) {
        MovieItems a = mData.get(position);
        Glide.with(context)
                .load(a.getPoster())
                .override(350,550)
                .into(holder.imgPoster);
        holder.tvJudul.setText(a.getJudul());
        holder.tvDeskripsi.setText(a.getDeskripsi());
        holder.tvTgl.setText(a.getTanggal());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;
        TextView tvJudul,tvDeskripsi,tvTgl;
        Button btnDetail,btnShare;

        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgPoster = (ImageView)itemView.findViewById(R.id.img_item_poster);
            tvJudul = (TextView)itemView.findViewById(R.id.tv_item_judul);
            tvDeskripsi = (TextView)itemView.findViewById(R.id.tv_item_deskripsi);
            btnDetail = (Button)itemView.findViewById(R.id.btn_set_detail);
            btnShare = (Button)itemView.findViewById(R.id.btn_set_share);
        }
    }
}
