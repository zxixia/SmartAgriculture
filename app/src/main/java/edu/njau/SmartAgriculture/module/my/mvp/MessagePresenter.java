package edu.njau.SmartAgriculture.module.my.mvp;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import edu.njau.SmartAgriculture.api.MyApi;
import edu.njau.SmartAgriculture.bean.my.message.Message;

/**
 * Created by lenovo on 2017/12/30.
 */

public class MessagePresenter implements MessageContract.Presenter {

    MessageContract.View mView;
    public MessagePresenter(MessageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMessageById(String id) {
        MyApi.getMessageById(id, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Message message = new Gson().fromJson(responseString, Message.class);
                mView.onGetMessage(message);
            }
        });
    }
}
