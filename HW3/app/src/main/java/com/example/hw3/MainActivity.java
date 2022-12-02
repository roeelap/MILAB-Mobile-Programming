package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "notificationChannel";
    private static final long FIVE_MINUTES = 5 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up images to main activity
        ImageView meme1 = (ImageView)findViewById(R.id.meme1);
        meme1.setImageResource(R.drawable.meme1);
        ImageView meme2 = (ImageView)findViewById(R.id.meme2);
        meme2.setImageResource(R.drawable.meme2);

        registerNotificationChannel();

        // create service that creates a notification every five minutes
        NotificationService.doActionNotification(MainActivity.this, new ResultReceiver(new Handler()), FIVE_MINUTES);
    }

    protected void registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT));
        }
    }
}