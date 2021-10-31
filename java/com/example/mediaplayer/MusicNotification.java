package com.example.mediaplayer;


import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MusicNotification {

    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_1 = "action_1";

    public static void displayNotification(Context context, String filename) {
        if(context == null)
            return;
        Intent action1Intent = new Intent(context, NotificationActionService.class).setAction(ACTION_1);
        PendingIntent action1PendingIntent = PendingIntent.getService(context, 0, action1Intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.setBigContentTitle("Music");
        bigText.bigText("Now playing");
        bigText.setSummaryText("Now playing");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Now playing");
        mBuilder.setContentText(filename);
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);
        mBuilder.addAction(android.R.drawable.ic_delete, "Stop", action1PendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "001";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Music is playing",
                    NotificationManager.IMPORTANCE_HIGH
            );
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public static class NotificationActionService extends IntentService {

        public NotificationActionService() {
            super(NotificationActionService.class.getSimpleName());
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            stopService(new Intent(this, MediaService.class));
            NotificationManagerCompat.from(this).cancel(NOTIFICATION_ID);
        }
    }
}