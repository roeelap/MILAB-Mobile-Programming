package com.example.hw3;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class NotificationService extends IntentService {
    private static final String ACTION_NOTIFICATION = "com.example.hw3.action.NOTIFICATION";
    private static final String EXTRA_PARAM_NOTIFICATION_PERIOD = "com.example.hw3.extra.EXTRA_PARAM_PERIOD";
    private static final String EXTRA_PARAM_NOTIFICATION_RECEIVER = "com.example.hw3.extra.EXTRA_PARAM_NOTIFICATION_RECEIVER";
    private static final int RESULT_CODE_SUCCESS = 0;

    public NotificationService() {
        super("NotificationService");
    }


    public static void doActionNotification(Context context, ResultReceiver receiver, long period) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_NOTIFICATION);
        intent.putExtra(EXTRA_PARAM_NOTIFICATION_PERIOD, period);
        intent.putExtra(EXTRA_PARAM_NOTIFICATION_RECEIVER, receiver);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_NOTIFICATION.equals(action)) {
                final long period = intent.getLongExtra(EXTRA_PARAM_NOTIFICATION_PERIOD, 0);
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_PARAM_NOTIFICATION_RECEIVER);
                handleActionNotification(receiver, period);
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }

    private void handleActionNotification(ResultReceiver receiver, long period) {
        createAlarmManager(period);
        final Bundle bundle = new Bundle();
        receiver.send(RESULT_CODE_SUCCESS, bundle);
    }

    private void createAlarmManager(long period) {
        Intent intent = new Intent(this, NotificationReceiver.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) ?
                PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE) :
                PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                period,
                pendingIntent);
    }
}
