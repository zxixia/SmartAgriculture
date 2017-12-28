package edu.njau.SmartAgriculture.module.gcfa.mvp;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import edu.njau.SmartAgriculture.api.GCFAApi;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public class PestControlPresenter implements PestControlContract.Presenter {

    PestControlContract.View mView;
    public PestControlPresenter(PestControlContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAllInfoFromMonthAndMonthPeriod(String zpfa, String crop, String area, String month, String monthPeriod) {
        GCFAApi.getAllInfoFromMonthAndMonthPeriod(zpfa, area, crop, month, monthPeriod, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {}

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    AllInfo allInfo = new Gson().fromJson(responseString, AllInfo.class);
                    mView.onGetAllInfoFromMonthAndMonthPeriod(allInfo);
                } catch (Exception e) {}
            }
        });
    }

    @Override
    public void getCommonTopInfoFromMonthAndMonthPeriod(String zpfa, String area, String crop, String month, String monthPeriod) {
        GCFAApi.getAllInfoFromMonthAndMonthPeriod(zpfa, area, crop, month, monthPeriod, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {}

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    AllInfo allInfo = new Gson().fromJson(responseString, AllInfo.class);
                    mView.onGetCommonTopInfoFromMonthAndMonthPeriod(allInfo);
                } catch (Exception e) {}
            }
        });
    }

    @Override
    public void getSuitableCrop(String zpfa, String crop, String area) {
        GCFAApi.getVarietyYield(zpfa, area, crop, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Variety variety = new Gson().fromJson(responseString, Variety.class);
                mView.onGetSuitableCrop(variety);
            }
        });
    }
}
