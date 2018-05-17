package com.neverstop_sharing.katalogmovie;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.neverstop_sharing.katalogmovie.db.DatabaseContract;

import static android.provider.BaseColumns._ID;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.getColumnInt;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.getColumnString;

public class Katalog implements Parcelable {
    private int id;
    private String title;
    private String img_url;
    private String overview;
    private String release_date;
    private String vote_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.img_url);
        dest.writeString(this.vote_count);
    }

    public Katalog() {

    }

    public Katalog(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseContract.KatalogColumn.TITLE);
        this.overview = getColumnString(cursor, DatabaseContract.KatalogColumn.OVERVIEW);
        this.release_date = getColumnString(cursor, DatabaseContract.KatalogColumn.RELEASE_DATE);
        this.img_url = getColumnString(cursor,DatabaseContract.KatalogColumn.IMG_URL);
        this.vote_count = getColumnString(cursor, DatabaseContract.KatalogColumn.VOTE_COUNT);
    }

    protected Katalog(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.img_url = in.readString();
        this.vote_count = in.readString();
    }

    public static final Parcelable.Creator<Katalog> CREATOR = new Parcelable.Creator<Katalog>() {
        @Override
        public Katalog createFromParcel(Parcel source) {
            return new Katalog(source);
        }

        @Override
        public Katalog[] newArray(int size) {
            return new Katalog[size];
        }
    };
}
