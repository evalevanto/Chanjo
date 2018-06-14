package eva.example.com.chanjo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


/**
 * Created by eva on 10/24/16.
 */
public class NotePublish extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "MiD";
    public static String NOTIFICATION = "Notif";

    @Override

    public void onReceive(Context context, Intent intent) {


        long when = System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent nInt = new Intent(context,kids.class);
        nInt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pndInt = PendingIntent.getActivity(context, 0 ,nInt,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.chnj)
                        .setContentTitle("Daily Reminder...Vitamin Intake...")
                        .setContentText("Kumbuka kumpea mtoto wako tembe za Vitamini leo!!")
                        .setAutoCancel(true)
                        .setWhen(when)
                        .setContentIntent(pndInt)
                        .setVibrate(new long[]{1000,1000,1000,1000,1000});
//        mBuilder.setAutoCancel(true);
        int mid=2;

        notificationManager.notify(mid,mBuilder.build());
        mid++;
//        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
    }
}




