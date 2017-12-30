package edu.njau.SmartAgriculture.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.njau.SmartAgriculture.broadcast.ShowNotificationReceiver;


public class MessageReceiveService extends Service {

    private String host = "";
   // private String host = "tcp://10.0.2.2:1883";
    private String userName = "test";
    private String passWord = "test";
    private String client_id= "android"+(int)(Math.random()*100000);
    private Handler handler;
    private MqttClient client;
    private MqttConnectOptions options;
    private ScheduledExecutorService scheduler;
    private SharedPreferences zhny;//save as SharedPreferences
    Context context;
//    private String[] topics_array = {
//            "period1","period2","period3",
//            "period4","period5","period6",
//            "period7","period8","period9"
//    };
    private String[] topics_array = {
            "GCFA_TOPIC1","GCFA_TOPIC12","GCFA_TOPIC13"
            ,"GCFA_TOPIC4"
    };

    public static final String ACTION = "edu.njau.SmartAgriculture.service.MyService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Service:","onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.e("Service:","onCreate");
        super.onCreate();
        context = getApplicationContext();
        zhny = context.getSharedPreferences("zhny", MODE_PRIVATE);
        String ip_str = "47.93.227.232";
        host = "tcp://"+ip_str+":1883";
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1) {
                    MqttMessage message = new MqttMessage();
                    message.setPayload("收到".getBytes());
                    Intent intent = new Intent(context, ShowNotificationReceiver.class);
                    Log.e("MESSAGEOBJ", msg.obj.toString());
                    intent.putExtra("message", msg.obj.toString());
                    context.sendBroadcast(intent);

                } else if(msg.what == 2) {
                    try {
                        String apptopics = zhny.getString("apptopics", "GCFA_TOPIC1");

                        //Log.e("apptopics",""+apptopics);
//                        String apptopics = "GCFA_TOPIC1";
                        try{
                            client.subscribe(apptopics);
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if(msg.what == 3) {
                	/*
                	 * 保留
                	 */
                }
            }
        };

        init();
        startReconnect();
    }

    private void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                if(!client.isConnected()) {
                    connect();
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }

    private void init() {
        try {
	        /*
	         * host为主机名，test为clientid即连接MQTT的客户端ID，
	         * 一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，
	         * 默认为以内存保存
	         */
           // client_id= agstar.getString("driverid", "gis_push");
            Log.e("client_id", ""+client_id);
            client = new MqttClient(host, client_id,
                    new MemoryPersistence());
            //MQTT的连接设置
            options = new MqttConnectOptions();
	        /*
	         * 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
	         * 这里设置为true表示每次连接到服务器都以新的身份连接
	         */
            options.setCleanSession(true);
            //设置连接的用户名
            options.setUserName(userName);
            //设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
	        /*
	         *  设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，
	         *  但这个方法并没有重连的机制
	         */
            options.setKeepAliveInterval(20);
            //设置回调
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost----------");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish后会执行到这里
                    System.out.println("deliveryComplete---------"
                            + token.isComplete());
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    System.out.println("messageArrived----------");
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = message.toString();
                    handler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    client.connect(options);
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }



    @Override
    public void onDestroy() {

        Log.e("Service:","onDestroy");
        try {
            scheduler.shutdown();
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try{
            if(client.isConnected()){
                client.unsubscribe(topics_array);
                String apptopics = zhny.getString("apptopics", "GCFA_TOPIC1");
                Log.e("apptopics",""+apptopics);
                client.subscribe(apptopics);

                Log.e("topics",""+client.getTopic(apptopics));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        Log.e("Service:","onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }
}
