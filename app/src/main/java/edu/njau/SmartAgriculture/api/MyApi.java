package edu.njau.SmartAgriculture.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import net.jiawa.debughelper.XLog;

/**
 * Created by lenovo on 2017/12/27.
 */

public class MyApi extends ApiBase {
    private static AsyncHttpClient mClient = new AsyncHttpClient();
    private static final String FindMqttHistoryByZpfaID = "findMqttHistoryByZpfaID.do";
    private static final String FindMqttHistoryByTopic = "findMqttHistoryByTopic.do";
    private static final String FindMqttHistoryByMessageID = "findMqttHistoryByMessageID.do";

    /**
     * http://47.93.227.232:8086/zhny/app/findMqttHistoryByZpfaID.do?ZpfaID=00000000001
     * @param zpfa
     * @param handler
     */
    public static void getCommontInfo(String zpfa, TextHttpResponseHandler handler) {
        try {
            final String getStr = getApi(FindMqttHistoryByZpfaID);
            XLog.d(true, 5, getStr);
            RequestParams params = new RequestParams();
            getZpfaID(params, zpfa);
            mClient.get(getStr, params, handler);
        } catch (Exception e){
            XLog.d(true, 5, e.toString());
        }
    }

    /**
     * http://47.93.227.232:8086//zhny/app/findMqttHistoryByTopic.do?Topic=GCFA_TOPIC1
     * @param handler
     */
    public static void getCommonInfoByTopic(String topic, TextHttpResponseHandler handler) {
        try {
            final String getStr = getApi(FindMqttHistoryByTopic);
            XLog.d(true, 5, getStr);
            RequestParams params = new RequestParams();
            getTopic(params, topic);
            mClient.get(getStr, params, handler);
        } catch (Exception e){
            XLog.d(true, 5, e.toString());
        }
    }

    /**
     * http://47.93.227.232:8086/zhny/app/findMqttHistoryByMessageID.do?MessageID=1514458046937
     * @param id
     * @param handler
     */
    public static void getMessageById(String id, TextHttpResponseHandler handler) {
        try {
            final String getStr = getApi(FindMqttHistoryByMessageID);
            RequestParams params = new RequestParams();
            getMessageID(params, id);
            mClient.get(getStr, params, handler);
        } catch (Exception e) {
            XLog.d(true, 5, e.toString());
        }
    }
}
