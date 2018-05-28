package com.neverstop_sharing.katalogmovie.utility;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.neverstop_sharing.katalogmovie.MovieItems;
import com.neverstop_sharing.katalogmovie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TYPE_REPEATING = "RepeatingAlarm";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_NOTIFICATION_ID = "notification_id";

    private final int NOTIF_ID_REPEATING = 101;
    List<NotificationItem>stackNotif = new ArrayList<>();
    private ArrayList<MovieItems>listMovie = new ArrayList<>();
    Context context;
    private static final String API_KEY = "93ede33b0c095238b2240c04b1e9e8ca";
    private NotificationCompat.Builder notification;
    private Handler handler = new Handler();

    public AlarmReceiver(){

    }



    @Override
    public void onReceive(final Context context, Intent intent) {
        this.context = context;
        final int notifId = intent.getIntExtra(EXTRA_NOTIFICATION_ID,0);

        Log.d("EXTRAINT", String.valueOf(notifId));

        final PendingResult pendingResult = goAsync();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {

                    if (notifId == 101){
                        Log.d("AlarmStatus","run thread");
                        SyncHttpClient client = new SyncHttpClient();
                        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en-US";
                        client.get(url, new AsyncHttpResponseHandler() {

                            @Override
                            public void onStart() {
                                super.onStart();
                                Log.d("AlarmStatus","onStart");
                            }

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                Log.d("AlarmStatus","onSuccess");
                                try {
                                    String result = new String(responseBody);
                                    JSONObject responseObject = new JSONObject(result);
                                    JSONArray list = responseObject.getJSONArray("results");
                                    for (int i=0;i<list.length();i++){
                                        final String title = list.getJSONObject(i).getString("title");
                                        final int id = list.getJSONObject(i).getInt("id");
                                        final String release = list.getJSONObject(i).getString("release_date");
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                                        Date now = new Date();
                                        String currentDate = dateFormat.format(now);
                                        //String currentDate = "2018-05-25";
                                        Log.d("AlarmStatus", String.valueOf(id));
                                        if (currentDate.equals(release)){

                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    sendNotification(id,"check this out!",title);
                                                }
                                            },2000);
                                        }else{
                                            Log.d("AlarmStatus", "movie not found for today release");
                                        }

                                    }
                                    String nowPlayingMovie = responseObject.getJSONArray("results").getJSONObject(0).getString("title");
                                    Log.d("AlarmStatus",nowPlayingMovie);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }



                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Log.d("AlarmStatus", "onfailur EROR");
                                pendingResult.finish();
                            }
                        });
                    }

                    if (notifId == 102){
                        sendNotification(notifId,", check our movie","we miss you");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }



        };

        thread.start();


        Log.d("AlarmStatus","onReceive");


    }


    public void sendNotification(int notifyId, String text,String title){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(title + text);
        Notification notification = mBuilder.build();

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(notifyId,notification);
    }



    public void setRepeatingAlarm(Context context,String type,String time,String message,final int NOTIFICATION_ID){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(EXTRA_TYPE,type);
        intent.putExtra(EXTRA_NOTIFICATION_ID,NOTIFICATION_ID);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArray[0]));;
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND,0);
        int requestCode = NOTIFICATION_ID;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,requestCode,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        Log.d("AlarmStatus","setRepeatingTime "+time);
    }





}
