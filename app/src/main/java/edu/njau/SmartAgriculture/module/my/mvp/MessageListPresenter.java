package edu.njau.SmartAgriculture.module.my.mvp;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import net.jiawa.debughelper.XLog;

import cz.msebera.android.httpclient.Header;
import edu.njau.SmartAgriculture.api.MyApi;
import edu.njau.SmartAgriculture.bean.my.commoninfo.CommonInfo;

/**
 * Created by lenovo on 2017/12/27.
 */
public class MessageListPresenter implements MessageListContract.Presenter {

    MessageListContract.View mView;
    public MessageListPresenter(MessageListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCommonInfo(String zpfa) {
        MyApi.getCommontInfo(zpfa, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                XLog.d(true, 5, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    CommonInfo commonInfo = new Gson().fromJson(responseString, CommonInfo.class);
                    mView.onGetCommonInfo(commonInfo.getResponse().getMqtthistorylist());
                    XLog.d(true, 5, responseString);
                } catch (Exception e) {
                    XLog.d(true, 5, e.toString());
                }
            }
        });
    }
}
