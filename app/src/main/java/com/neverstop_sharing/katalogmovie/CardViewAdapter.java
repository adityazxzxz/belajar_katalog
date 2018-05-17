package com.neverstop_sharing.katalogmovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.tvTgl.setText(a.getTanggal());
        holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Log.d("PESAN",getListMovie().get(position).getJudul());
                Intent intent = new Intent(context,MovieDetail.class);
                intent.putExtra(MovieDetail.EXTRA_ID_CONTENT,getListMovie().get(position).getId_content());
                intent.putExtra(MovieDetail.EXTRA_TITLE,getListMovie().get(position).getJudul());
                intent.putExtra(MovieDetail.EXTRA_POSTER,getListMovie().get(position).getPoster_big());
                intent.putExtra(MovieDetail.EXTRA_TANGGAL,getListMovie().get(position).getTanggal());
                intent.putExtra(MovieDetail.EXTRA_VOTE_AVERAGE,getListMovie().get(position).getVote_average());
                intent.putExtra(MovieDetail.EXTRA_VOTE_COUNT,getListMovie().get(position).getVote_count());
                intent.putExtra(MovieDetail.EXTRA_DESKRIPSI,getListMovie().get(position).getDeskripsi_full());
                context.startActivity(intent);
                //Toast.makeText(context,"Detail "+getListMovie().get(position).getJudul(),Toast.LENGTH_SHORT).show();
            }
        }));

        holder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context,"Share "+getListMovie().get(position).getJudul(),Toast.LENGTH_SHORT).show();
            }
        }));

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
            tvTgl = (TextView)itemView.findViewById(R.id.tv_item_tanggal);
            btnDetail = (Button)itemView.findViewById(R.id.btn_set_detail);
            btnShare = (Button)itemView.findViewById(R.id.btn_set_share);
        }
    }
}
