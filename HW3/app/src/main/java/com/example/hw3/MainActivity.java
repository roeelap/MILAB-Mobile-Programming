package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "notificationChannel";
    private static final long FIVE_MINUTES = 5 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerNotificationChannel();

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Button notifyButton = (Button)findViewById(R.id.notificationBtn);
        notifyButton.setOnClickListener(view -> {
            Toast.makeText(this, "notification set!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, NotificationReceiver.class);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) ?
                    PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE) :
                    PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    FIVE_MINUTES,
                    pendingIntent);
        });
    }

    protected void registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT));
        }
    }
}