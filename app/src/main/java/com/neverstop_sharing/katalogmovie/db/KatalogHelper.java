package com.neverstop_sharing.katalogmovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.neverstop_sharing.katalogmovie.Katalog;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.ID_CONTENT;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.IMG_URL;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.OVERVIEW;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.RELEASE_DATE;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.TITLE;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.KatalogColumn.VOTE_COUNT;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.TABLE_KATALOG;

public class KatalogHelper {
    private static String DATABASE_TABLE = TABLE_KATALOG;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public KatalogHelper(Context context){
        this.context = context;
    }

    public KatalogHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Katalog> query(){
        ArrayList<Katalog> arrayList = new ArrayList<Katalog>();
        Cursor cursor = database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null,_ID +" DESC"
                ,null);
        cursor.moveToFirst();
        Katalog katalog;
        if (cursor.getCount()>0) {
            do {

                katalog = new Katalog();
                katalog.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                katalog.setId_content(cursor.getString(cursor.getColumnIndexOrThrow(ID_CONTENT)));
                katalog.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                katalog.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                katalog.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                katalog.setImg_url(cursor.getString(cursor.getColumnIndexOrThrow(IMG_URL)));
                katalog.setVote_count(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_COUNT)));

                arrayList.add(katalog);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Katalog katalog){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(ID_CONTENT, katalog.getId_content());
        initialValues.put(TITLE, katalog.getTitle());
        initialValues.put(IMG_URL, katalog.getImg_url());
        initialValues.put(RELEASE_DATE, katalog.getRelease_date());
        initialValues.put(VOTE_COUNT,katalog.getVote_count());
        initialValues.put(OVERVIEW,katalog.getOverview());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Katalog katalog){
        ContentValues args = new ContentValues();
        args.put(ID_CONTENT,katalog.getId_content());
        args.put(TITLE, katalog.getTitle());
        args.put(IMG_URL,katalog.getImg_url());
        args.put(OVERVIEW, katalog.getOverview());
        args.put(RELEASE_DATE, katalog.getRelease_date());
        args.put(VOTE_COUNT,katalog.getVote_count());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + katalog.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(TABLE_KATALOG, _ID + " = '"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }
    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }
    public int updateProvider(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }
    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
}
