package com.example.alptekin.bilconnectt;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class NotificationHelper extends ContextWrapper {
    //Properties
    private static final String BILCONNECT_CHANNEL_ID   = "bilconnect_anroidNotificationChannelId";
    private static final String BILCONNECT_CHANNEL_NAME = "bilconnect_Channel";
    private NotificationManager manager;


    //Constrcutors
    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHelper(Context base){
        super( base);
        createChannels();
    }


    //Methods
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {
        NotificationChannel bilconnectChannel = new NotificationChannel( BILCONNECT_CHANNEL_ID,
                BILCONNECT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        bilconnectChannel.enableLights( true);
        bilconnectChannel.enableVibration( true);
        bilconnectChannel.setLightColor(Color.BLUE);
        bilconnectChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel( bilconnectChannel);
    }

    public NotificationManager getManager() {
        if( manager == null){

            manager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE);

        }
        return manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getbilconnect_ChannelNotification(String title, String body){
        return new Notification.Builder( getApplicationContext(), BILCONNECT_CHANNEL_ID)
                .setContentText( body)
                .setContentTitle( title)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel( true);
    }
}
