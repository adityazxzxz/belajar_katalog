package com.neverstop_sharing.katalogmovie;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by aditya.pratama on 3/27/2018.
 */

public class MovieItems {
    private int id;
    private String judul;
    private String deskripsi;
    private String tanggal;
    private String poster;
    private String poster_big;
    private String vote_count;
    private String vote_average;
    private String deskripsi_full;


    public MovieItems(JSONObject object){
        try {
            int id = object.getInt("id");
            String judul = object.getString("title");
            String title = object.getString("title");
            String deskripsi = object.getString("overview").substring(0,20)+"...";
            String deskripsi_full = object.getString("overview");
            String tanggal = object.getString("release_date");
            String vote_count = object.getString("vote_count");
            String vote_average = object.getString("vote_average");
            String poster = "http://image.tmdb.org/t/p/w92"+object.getString("poster_path");
            String poster_big = "http://image.tmdb.org/t/p/w342"+object.getString("poster_path");
            this.id = id;
            this.judul = title;
            this.deskripsi = deskripsi;
            this.deskripsi_full = deskripsi_full;
            this.vote_average = vote_average;
            this.vote_count = vote_count;
            this.tanggal = tanggal;
            this.poster = poster;
            this.poster_big = poster_big;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getJudul(){
        return judul;
    }
    public void setJudul(String judul){
        this.judul = judul;
    }


    public String getDeskripsi(){
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster){
        this.poster = poster;
    }
    public String getPoster_big() {
        return poster_big;
    }

    public void setPoster_big(String poster_big) {
        this.poster_big = poster_big;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getDeskripsi_full() {
        return deskripsi_full;
    }

    public void setDeskripsi_full(String deskripsi_full) {
        this.deskripsi_full = deskripsi_full;
    }
}
