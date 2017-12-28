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
}
