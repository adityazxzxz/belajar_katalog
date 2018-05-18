package eneverstop.adityapratama.katalogfavorite;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.getColumnInt;
import static eneverstop.adityapratama.katalogfavorite.DatabaseContract.getColumnString;

public class MovieItem implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String date;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.image);
    }

    public MovieItem() {
    }

    public MovieItem(Cursor cursor){
        this.id = getColumnInt(cursor, DatabaseContract.KatalogColumn._ID);
        this.title = getColumnString(cursor, DatabaseContract.KatalogColumn.TITLE);
        this.description = getColumnString(cursor, DatabaseContract.KatalogColumn.OVERVIEW);
        this.date = getColumnString(cursor, DatabaseContract.KatalogColumn.RELEASE_DATE);
        this.image = getColumnString(cursor, DatabaseContract.KatalogColumn.IMG_URL);
    }

    protected MovieItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }


        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}
