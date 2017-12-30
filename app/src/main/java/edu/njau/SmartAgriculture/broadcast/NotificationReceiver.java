package edu.njau.SmartAgriculture.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import edu.njau.SmartAgriculture.helper.SystemUtils;
import edu.njau.SmartAgriculture.module.MainActivity;
import edu.njau.SmartAgriculture.module.my.MessageActivity;


public class NotificationReceiver extends BroadcastReceiver {

    private SharedPreferences zhny;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("message");
        String MqMessageId = bundle.getString("MqMessageId");
        zhny = context.getApplicationContext().getSharedPreferences("zhny", context.getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = zhny.edit();
        editor.putString("message_str",""+message);
        editor.commit();
		/*
		 * 判断app进程是否存活
		 */
        if(SystemUtils.isAppAlive(context, "edu.njau.SmartAgriculture")){
            /*
             * 如果存活的话，就直接启动DetailActivity，但要考虑一种情况，就是app的进程虽然仍然在
             * 但Task栈已经空了，比如用户点击Back键退出应用，但进程还没有被系统回收，如果直接启动
             * DetailActivity,再按Back键就不会返回MainActivity了。所以在启动
             * DetailActivity前，要先启动MainActivity。
             */
            Log.e("NotificationReceiver", "the app process is alive");
            Intent mainIntent = new Intent(context, MessageActivity.class);
            /*
             * Intent mainIntent = new Intent(context, DivSpaceDataFragment.class);
             * 将MainAtivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
             * 如果Task栈中有MainActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
             * 如果Task栈不存在MainActivity实例，则在栈顶创建
             */
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mainIntent.putExtra("message", message);
            mainIntent.putExtra("MessageId", MqMessageId);
            Intent[] intents = {mainIntent};
            context.startActivities(intents);

        }else {
        	/*
        	 * 如果app进程已经被杀死，先重新启动app，将DetailActivity的启动参数传入Intent中，参数经过
             * SplashActivity传入MainActivity，此时app的初始化已经完成，在MainActivity中就可以根据传入
        	 * 参数跳转到DetailActivity中去了
        	 */
            Log.e("NotificationReceiver", "the app process is dead");
            Intent launchIntent = context.getPackageManager().
                    getLaunchIntentForPackage("edu.njau.SmartAgriculture");
            launchIntent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            Bundle args = new Bundle();
            launchIntent.putExtra("launchBundle", args);
            launchIntent.putExtra("message", message);
            launchIntent.putExtra("MessageId", MqMessageId);
            context.startActivity(launchIntent);
        }

    }
}
