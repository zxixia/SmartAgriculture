package edu.njau.SmartAgriculture.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;



public class ShowNotificationReceiver extends BroadcastReceiver {

    public static int message_id = 0;
    private static final String TAG = "RepeatReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "ShowNotificationReceiver onReceive");
        //设置点击通知栏的动作为启动另外一个广播
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("message");
        String contentf = "";
        String machinetitle = "";
        String time = "";
        try{
            if(message.length()>16){
                contentf = message.substring(0,15)+"...";
            }else{
                contentf = message;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        message_id ++ ;
        Log.e("message_id=",message_id+"");

        if(message_id > 10){
            message_id = 0;
        }
        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
        broadcastIntent.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.
                getBroadcast(context, message_id, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(android.R.mipmap.sym_def_app_icon)
                .setContentTitle(machinetitle+"您收到一条消息")
                .setContentText(""+contentf)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);//跳转事件
        mBuilder.setTicker("消息"+machinetitle+"");//第一次提示消息的时候显示在通知栏上
        mBuilder.setAutoCancel(true);//自己维护通知的消失

        Log.i("repeat", "showNotification");
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(message_id, mBuilder.build());
    }

}
