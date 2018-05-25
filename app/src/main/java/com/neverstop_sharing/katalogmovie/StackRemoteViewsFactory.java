package com.neverstop_sharing.katalogmovie;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.neverstop_sharing.katalogmovie.db.DatabaseContract.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{
    //private List<Cursor> mWidgetItems = new ArrayList<>();
    private Cursor mWidgetItems;
    private Context mContext;
    private int mAppWidgetId;

    public StackRemoteViewsFactory(Context context, Intent intent){
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);

    }



    @Override
    public void onCreate() {
        this.mWidgetItems = mContext.getContentResolver().query(CONTENT_URI,null,null,null,null);
        Log.d("Tester","onCreate :"+mWidgetItems);
       /* mWidgetItems.add("https://cdn.pixabay.com/photo/2014/03/29/09/17/cat-300572_960_720.jpg");
        mWidgetItems.add("https://i.ytimg.com/vi/5TyDk2PAaJ4/maxresdefault.jpg");*/



    }

    @Override
    public void onDataSetChanged() {
        final long idToken = Binder.clearCallingIdentity();
        this.mWidgetItems = mContext.getContentResolver().query(CONTENT_URI,null,null,null,null);
        Binder.restoreCallingIdentity(idToken);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.d("stackremote",String.valueOf(mWidgetItems.getCount()));
        return mWidgetItems.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d("TESTER","tes");
        RemoteViews rv = new RemoteViews(mContext.getPackageName(),R.layout.widget_item);
        final MovieItem movieItem = getItem(position);
        String imageMovie = movieItem.getImage();
        Bitmap bmp = null;
        try {
            bmp = Glide.with(mContext)
                    .load(imageMovie)
                    .asBitmap()
                    .error(new ColorDrawable(mContext.getResources().getColor(R.color.colorPrimaryDark)))
                    .into(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL).get();
        }catch (InterruptedException | ExecutionException e){
            Log.d("Widget Load Error","error");
        }

        Bundle extras = new Bundle();
        extras.putInt(ImageFavoriteWidget.EXTRA_ITEM,position);
        Intent fillIntent = new Intent();
        fillIntent.putExtras(extras);

        //rv.setImageViewBitmap(R.id.imageView,mWidgetItems.get(position));
        rv.setImageViewBitmap(R.id.imageView,bmp);
        rv.setOnClickFillInIntent(R.id.imageView,fillIntent);
        return rv;
    }

    private MovieItem getItem(int position) {
        if (!mWidgetItems.moveToPosition(position)){
            throw new IllegalStateException("Position invalid");
        }
        return new MovieItem(mWidgetItems);
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
