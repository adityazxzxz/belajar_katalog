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
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.AsyncTaskLoader;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TYPE_REPEATING = "RepeatingAlarm";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "type";

    private final int NOTIF_ID_REPEATING = 101;
    private int idNotif = 0;
    private int maxNotif = 100;
    List<NotificationItem>stackNotif = new ArrayList<>();
    private ArrayList<MovieItems>listMovie = new ArrayList<>();
    Context context;
    private static final String API_KEY = "93ede33b0c095238b2240c04b1e9e8ca";

    public AlarmReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        String title = "Repeating Alarm";
        int notifId = NOTIF_ID_REPEATING;

        //showAlarmNotification(context,title,message,notifId);

        final PendingResult pendingResult = goAsync();



        Thread thread = new Thread(){
            @Override
            public void run() {
                try {

                    Log.d("AlarmStatus","run thread");
                    SyncHttpClient client = new SyncHttpClient();
                    String url = "https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language=en-US";
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
                                    //JSONObject movie = list.getJSONObject(i);
                                    Log.d("AlarmStatus",list.getJSONObject(i).getString("title"));
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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        thread.start();


        NotificationItem notificationItem = new NotificationItem(idNotif,title,message);
        stackNotif.add(new NotificationItem(idNotif,title,message));
        sendNotif(context);
        idNotif++;
        Log.d("AlarmStatus","onReceive");
    }

    private void sendNotif(Context context) {
        Log.d("AlarmStatus", String.valueOf(listMovie));
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        Notification notification = null;
        if (idNotif < maxNotif){
            notification = new NotificationCompat.Builder(context)
                    .setContentTitle("Movie release "+stackNotif.get(idNotif).getSender())
                    .setContentText(stackNotif.get(idNotif).getMessage())
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setAutoCancel(true)
                    .build();
        }
        manager.notify(idNotif,notification);
    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context,android.R.color.transparent))
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setSound(alarmSound);
        notificationManagerCompat.notify(notifId,builder.build());
        Log.d("AlarmStatus","showalarmnotification");
        Toast.makeText(context,"showAlarmNotification()",Toast.LENGTH_SHORT).show();
    }

    public void setRepeatingAlarm(Context context,String type,String time,String message){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(EXTRA_TYPE,type);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArray[0]));;
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND,0);
        int requestCode = NOTIF_ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,requestCode,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        Log.d("AlarmStatus","setRepeatingTime "+time);
    }





}
