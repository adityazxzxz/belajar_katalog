package eneverstop.adityapratama.katalogfavorite;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_KATALOG = "katalog";

    public static final class KatalogColumn implements BaseColumns {
        public static String ID_CONTENT = "id_content";
        public static String TITLE = "title";
        public static String IMG_URL = "img_url";
        public static String OVERVIEW = "overview";
        public static String VOTE_COUNT = "vote_count";
        public static String RELEASE_DATE = "release_date";
    }

    public static final String AUTHORITY = "com.neverstop_sharing.katalogmovie";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_KATALOG)
            .build();
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }

}

