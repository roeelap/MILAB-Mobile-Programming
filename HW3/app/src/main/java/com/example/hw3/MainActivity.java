package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "notificationChannel";
    private static final String ACTION_NOTIFICATION = "com.example.hw3.action.NOTIFICATION";
    boolean isNotificationsActive = false;
    Button notificationBtn;
    ImageView meme1;
    ImageView meme2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerNotificationChannel();
        setUpViews();
    }

    protected void registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT));
        }
    }

    private void setUpViews() {
        // set up images to main activity
        meme1 = findViewById(R.id.meme1);
        meme1.setImageResource(R.drawable.meme1);
        meme2 = findViewById(R.id.meme2);
        meme2.setImageResource(R.drawable.meme2);

        notificationBtn = findViewById(R.id.notificationBtn);

        notificationBtn.setOnClickListener(view -> {
            if (!isNotificationsActive) {
                Intent intent = new Intent(MainActivity.this, NotificationService.class);
                intent.setAction(ACTION_NOTIFICATION);
                startService(intent);
                Toast.makeText(MainActivity.this, "Activated Notifications", Toast.LENGTH_SHORT).show();
                isNotificationsActive = true;
            }
        });
    }
}