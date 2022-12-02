package com.example.hw3;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "notificationChannel";
    protected int mNotificationId = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) ?
            PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE) :
            PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification Title")
                .setContentText("Notification Text")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(mNotificationId++, builder.build());
    }
}