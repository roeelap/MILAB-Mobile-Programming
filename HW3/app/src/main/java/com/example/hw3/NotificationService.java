package com.example.hw3;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class NotificationService extends IntentService {
    private static final String TAG = "NotificationService";
    private static final String ACTION_NOTIFICATION = "com.example.hw3.action.NOTIFICATION";
    private static final long FIVE_MINUTES = 5 * 60 * 1000;
    private static PendingIntent pendingIntent = null;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "Notification service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Notification service destroyed");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_NOTIFICATION.equals(action)) {
                activateNotifications();
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private void activateNotifications() {
        if (pendingIntent == null) {
            Intent intent = new Intent(this, NotificationReceiver.class);
            pendingIntent = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) ?
                    PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE) :
                    PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    FIVE_MINUTES,
                    pendingIntent);
            Log.e(TAG, "Alarm manager created");
        }
    }
}
