package com.example.hw3;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "notificationChannel";
    protected int mNotificationId = 1;

    private final String[] quotes = {
            "“May the Force be with you.” -Star Wars, 1977",
            "There's no place like home.” -The Wizard of Oz, 1939",
            "“I'm the king of the world!” -Titanic, 1997",
            "“Carpe diem. Seize the day, boys. Make your lives extraordinary.” -Dead Poets Society, 1989",
            "“Elementary, my dear Watson.” -The Adventures of Sherlock Holmes, 1939",
            "“It's alive! It's alive!” -Frankenstein, 1931",
            "“My mama always said life was like a box of chocolates. You never know what you're gonna get.” -Forrest Gump, 1994",
            "“I'll be back.” -The Terminator, 1984",
            "“You're gonna need a bigger boat.” -Jaws, 1975",
            "“Here's looking at you, kid.” -Casablanca,1942",
            "“My precious.” -The Lord of the Rings: Two Towers, 2002",
            "“Houston, we have a problem.” -Apollo 13, 1995",
            "“There's no crying in baseball!” -A League of Their Own, 1992","",
            "“E.T. phone home.” -E.T. the Extra-Terrestrial, 1982",
            "“You can't handle the truth!” -A Few Good Men, 1992",
            "“A martini. Shaken, not stirred.” -Goldfinger, 1964",
            "“Life is a banquet, and most poor suckers are starving to death!” -Auntie Mame, 1958",
            "“If you build it, he will come.” -Field of Dreams, 1989",
            "“The stuff that dreams are made of.” -The Maltese Falcon, 1941",
            "“Magic Mirror on the wall, who is the fairest one of all?“ -Snow White and the Seven Dwarfs, 1937",
            "“Keep your friends close, but your enemies closer.” -The Godfather Part II, 1974",
            "“I am your father.” -Star Wars Episode V: The Empire Strikes Back, 1980",
            "“Just keep swimming.” -Finding Nemo, 2003",
            "“Today, I consider myself the luckiest man on the face of the earth.” -The Pride of the Yankees, 1942",
            "“You is kind. You is smart. You is important.” -The Help, 2011",
            "“What we've got here is failure to communicate.” -Cool Hand Luke, 1967",
            "“Hasta la vista, baby.” -Terminator 2: Judgment Day, 1991",
            "“You don't understand! I coulda had class. I coulda been a contender. I could've been somebody, instead of a bum, which is what I am.” -On the Waterfront, 1954",
            "“Bond. James Bond.“ -Dr. No, 1962",
            "“You talking to me?” -Taxi Driver, 1976",
            "“Roads? Where we're going we don't need roads.” -Back to the Future, 1985",
            "“That'll do, pig. That'll do.” -Babe, 1995",
            "“I'm walking here! I'm walking here!” -Midnight Cowboy, 1969",
            "“It was beauty killed the beast.“ -King Kong, 1933",
            "“Stella! Hey, Stella!” -A Streetcar Named Desire, 1951",
            "“As if!“ -Clueless, 1995",
            "“Here's Johnny!” -The Shining, 1980",
            "“Rosebud.” -Citizen Kane, 1941",
            "“I'll have what she's having.” -When Harry Met Sally, 1989",
            "“Inconceivable!” -The Princess Bride, 1987",
            "“All right, Mr. DeMille, I'm ready for my close-up.” -Sunset Boulevard, 1950",
            "“Fasten your seatbelts. It's going to be a bumpy night.” -All About Eve, 1950",
            "“Nobody puts Baby in a corner.” -Dirty Dancing, 1987",
            "“Well, nobody's perfect.” -Some Like it Hot, 1959",
            "“Snap out of it!” -Moonstruck, 1987",
            "“You had me at ‘hello.’“ -Jerry Maguire, 1996",
            "“They may take our lives, but they'll never take our freedom!“ -Braveheart, 1995",
            "“To infinity and beyond!” -Toy Story, 1995",
            "“You’re killin’ me, Smalls.” -The Sandlot, 1993",
            "“Toto, I've a feeling we're not in Kansas anymore.” -The Wizard of Oz, 1939"
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) ?
            PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE) :
            PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        // get random quote from list
        Random rnd = new Random();
        String rndQuote = quotes[rnd.nextInt(quotes.length)];

        // create notification with the random quote
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("QUOTE OF THE MOMENT")
                .setContentText(rndQuote)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(mNotificationId++, builder.build());
    }
}