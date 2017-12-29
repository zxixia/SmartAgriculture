package edu.njau.SmartAgriculture.module.my;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.fragments.BaseFragment;
import edu.njau.SmartAgriculture.module.gcfa.PeriodActivity;
import edu.njau.SmartAgriculture.service.MessageReceiveService;

/**
 * Created by lenovo on 2017/12/24.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {


    private MessageReceiver myreceiver;
    private Intent myintent;
    private SharedPreferences zhny;
    private SharedPreferences.Editor editor;
    private String mTOPIC = "NJTest";

    @Bind(R.id.rl_my_common_info)
    RelativeLayout mCommonInfo;/* 通用信息 */


    @Bind(R.id.sc_zpfa_sub)
    SwitchCompat mZPFASub;/* 栽培方案订阅 */

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {
        super.initData();
        myintent = new Intent(getActivity().getApplicationContext(),MessageReceiveService.class);
        zhny = getActivity().getApplicationContext().getSharedPreferences("zhny", getActivity().getApplicationContext().MODE_PRIVATE);
        editor = zhny.edit();
        boolean sub_checked = zhny.getBoolean("sub_check",false);
        mZPFASub.setChecked(sub_checked);
        registerMessageReceiver();
    }

    @OnClick({R.id.rl_my_common_info})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_my_common_info) {
            Intent intent = new Intent();
            intent.putExtra("Topic", "GCFA_TOPIC1");
            intent.setClass(getActivity(), MessageListActivity.class);
            startActivity(intent);
        }
    }
    @OnCheckedChanged({R.id.sc_zpfa_sub})
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            if(!isServiceRunning()){
                getActivity().getApplicationContext().startService(myintent);
                Log.e("service_1", ServiceRunning());
                //	Toast.makeText(getActivity().getApplicationContext(), "启动消息推送"+ServiceRunning(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity().getApplicationContext(), "启动消息推送", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("service",",,,,,,,,,,,,,,stop");
            if(isServiceRunning()){
                getActivity().getApplicationContext().stopService(myintent);
                Toast.makeText(getActivity().getApplicationContext(), "关闭消息推送", Toast.LENGTH_SHORT).show();
                Log.e("service_2", ServiceRunning());
            }
        }
        editor.putString("apptopics",""+mTOPIC);
        editor.putBoolean("sub_check", isChecked);
        editor.commit();
    }




    /** 订阅 **/
    public boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getActivity().getApplicationContext().getSystemService(getActivity().ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.receivedata.service.MyService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public String ServiceRunning() {
        String name = "";
        ActivityManager manager = (ActivityManager) getActivity().getApplicationContext().getSystemService(getActivity().ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            name = name+"\n"+service.service.getClassName();
        }
        return name;
    }

    public void registerMessageReceiver() {
        myreceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.receivedata.broadcast.NotificationReceiver");
        getActivity().getApplicationContext().registerReceiver(myreceiver , filter);
    }

    public void unregisterMessageReceiver(){
        try{
            getActivity().unregisterReceiver(myreceiver);
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String actionstr = intent.getAction();
            Log.e("收到消息","ddd");
            if (actionstr.equals("com.receivedata.broadcast.NotificationReceiver")) {
                Log.e("收到消息1","dddss");

            }
        }

    }
    /** 订阅 **/
}
