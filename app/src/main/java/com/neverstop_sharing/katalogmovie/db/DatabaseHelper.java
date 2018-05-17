package com.neverstop_sharing.katalogmovie.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbkatalogmovie";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_KATALOG,
            DatabaseContract.KatalogColumn._ID,
            DatabaseContract.KatalogColumn.TITLE,
            DatabaseContract.KatalogColumn.IMG_URL,
            DatabaseContract.KatalogColumn.RELEASE_DATE,
            DatabaseContract.KatalogColumn.OVERVIEW,
            DatabaseContract.KatalogColumn.VOTE_COUNT);


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+DatabaseContract.TABLE_KATALOG);
        onCreate(db);
    }
}

