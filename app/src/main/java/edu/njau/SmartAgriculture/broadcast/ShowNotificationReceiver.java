package edu.njau.SmartAgriculture.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;



public class ShowNotificationReceiver extends BroadcastReceiver {

    private SharedPreferences zhny;
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

        /**
         * 1514640173294
         * #五月
         * #下旬
         * #00000000001
         * #江苏沿江太湖亚区单季稻机插亩产
         * 700公斤高产创建技术规范模式图
         * #0
         * #播种
         * #机械栽插
         * #机械栽插，行株距9寸×3.5寸，每亩1.7~1.9万穴，每穴2~3苗，每亩5~6万基本苗
         * #秧田期
         * #亩用25%吡蚜酮可湿性粉剂25克或40%
         * 乐果100克对水40公斤喷雾，防治秧田灰飞虱、稻蓟马、螟虫及水稻条纹叶枯病
         */

        zhny = context.getApplicationContext().getSharedPreferences("zhny", context.getApplicationContext().MODE_PRIVATE);
        Log.e("MessageShow............",message);


//        messageId+"#"+
//                hpInfor.getCropMonth()+"#"+
//                hpInfor.getMonthPeriod()+"#"+
//                hpInfor.getZpfaID()+"#"+
//                hpInfor.getZpfaName()+"#"+
//                hpInfor.getLeafAge()+"#"+
//                hpInfor.getCropPeriod()+"#"+
//                hpInfor.getFarmingOperation().getFarmingOperationName()+"#"+
//                hpInfor.getFarmingOperation().getFarmingOperationContent()+"#"+
//                hpInfor.getPestControl().getPestControlName()+"#"+
//                hpInfor.getPestControl().getPestControlContent();
        /**
         * 收到的消息推送解析
         */
        String MqMessageId = message.split("#")[0];
        String CropMonth= message.split("#")[1];
        Log.e("CropMonth.......",""+CropMonth);
        String MonthPeriod = message.split("#")[2];
        String ZpfaID = message.split("#")[3];
        String ZpfaName = message.split("#")[4];
        String LeafAge= message.split("#")[5];
        String CropPeriod = message.split("#")[6];
        String FarmingOperationName = message.split("#")[7];
        String FarmingOperationContent = message.split("#")[8];
        String PestControlName= message.split("#")[9];
        String PestControlContent = message.split("#")[10];

        String showTxt = ""+CropMonth+MonthPeriod+
                "";

        if(zhny.getBoolean("mPD",false)) {
            showTxt = showTxt +CropPeriod ;
        }

        if(zhny.getBoolean("mLF",false)) {
            showTxt = showTxt +LeafAge ;
        }
        if(zhny.getBoolean("mSG",false)) {
            showTxt = showTxt ;
        }

        if(zhny.getBoolean("mOP",false)) {
            showTxt = showTxt + FarmingOperationName+FarmingOperationContent;
        }
        if(zhny.getBoolean("mPC",false)) {
            showTxt = showTxt + PestControlName+PestControlContent;
        }

        try{
            if(showTxt.length()>16){
                contentf = showTxt.substring(0,15)+"...";
            }else{
                contentf = showTxt;
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
        broadcastIntent.putExtra("MqMessageId", MqMessageId);

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
