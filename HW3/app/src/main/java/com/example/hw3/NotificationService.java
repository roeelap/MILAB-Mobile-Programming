package com.example.hw3;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class NotificationService extends IntentService {
    private static final String ACTION_NOTIFICATION = "com.example.hw3.action.NOTIFICATION";
    private static final String EXTRA_PARAM_NOTIFICATION_RECEIVER = "com.example.hw3.extra.EXTRA_PARAM_NOTIFICATION_RECEIVER";
    private static final int RESULT_CODE_SUCCESS = 0;

    public NotificationService() {
        super("NotificationService");
    }

    public static void doActionNotification(Context context, ResultReceiver receiver) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_NOTIFICATION);
        intent.putExtra(EXTRA_PARAM_NOTIFICATION_RECEIVER, receiver);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_NOTIFICATION.equals(action)) {
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_PARAM_NOTIFICATION_RECEIVER);
                handleActionNotification(receiver);
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }

    private void handleActionNotification(ResultReceiver receiver) {
        final Bundle bundle = new Bundle();
        receiver.send(RESULT_CODE_SUCCESS, bundle);
    }
}
