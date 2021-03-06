package com.neverstop_sharing.katalogmovie.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.neverstop_sharing.katalogmovie.db.DatabaseContract;
import com.neverstop_sharing.katalogmovie.db.KatalogHelper;

import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.AUTHORITY;
import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.CONTENT_URI;

public class KatalogProvider extends ContentProvider {

    private static final int KATALOG = 1;
    private static final int KATALOG_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        // content://com.dicoding.mynotesapp/note
        sUriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_KATALOG, KATALOG);

        // content://com.dicoding.mynotesapp/note/id
        sUriMatcher.addURI(AUTHORITY,
                DatabaseContract.TABLE_KATALOG+ "/#",
                KATALOG_ID);
    }

    private KatalogHelper katalogHelper;

    @Override
    public boolean onCreate() {
        katalogHelper = new KatalogHelper(getContext());
        katalogHelper.open();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch(sUriMatcher.match(uri)){
            case KATALOG:
                cursor = katalogHelper.queryProvider();
                break;
            case KATALOG_ID:
                cursor = katalogHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {

        long added ;

        switch (sUriMatcher.match(uri)){
            case KATALOG:
                added = katalogHelper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updated ;
        switch (sUriMatcher.match(uri)) {
            case KATALOG_ID:
                updated =  katalogHelper.updateProvider(uri.getLastPathSegment(),contentValues);
                break;
            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case KATALOG_ID:
                deleted =  katalogHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

}
