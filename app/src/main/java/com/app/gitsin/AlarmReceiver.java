package com.app.gitsin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    public final static int NOTIFICATION_ID = 0;
    final String CHANNEL_ID = "notificationChannel";

    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            createNotificationChannel();    //채널 등록
            deliverNotification(context);   //Notification 등록

//        }

    }//end of onReceive

    private void deliverNotification(Context context) {
        //Notification 등록
        Intent notificationIntent = new Intent(context, Login.class);

        PendingIntent pendingI
                = PendingIntent.getActivity(context, NOTIFICATION_ID, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);

        builder.setAutoCancel(true)
                .setSmallIcon(R.drawable.cha_personal)
                .setContentTitle("깃신 알람")
                .setContentText("오늘 깃허브 모내기 하셨나요? 아직 안했으면 고고싱!")
                .setContentIntent(pendingI)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setWhen(System.currentTimeMillis())
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        // 노티피케이션 동작시킴
        notificationManager.notify(NOTIFICATION_ID, builder.build());

//            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

    }//end of deliverNotification

    public void createNotificationChannel() {
        //Notificaion용 채널 등록 (OREO API 26 이상에서는 채널 필요)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel channel
                    = new NotificationChannel(CHANNEL_ID, "Stand up noti", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);

            notificationManager.createNotificationChannel(channel);
        }
    }


}//end of class

