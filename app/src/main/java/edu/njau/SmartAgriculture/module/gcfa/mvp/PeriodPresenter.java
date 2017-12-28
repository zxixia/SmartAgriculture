package edu.njau.SmartAgriculture.module.gcfa.mvp;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import edu.njau.SmartAgriculture.api.GCFAApi;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.period.Period;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public class PeriodPresenter implements PeriodContract.Presenter {

    PeriodContract.View mView;
    public PeriodPresenter(PeriodContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getPeriod(String zpfa, String area, String crop) {
        GCFAApi.getPeriod(zpfa, area, crop, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    Period period = new Gson().fromJson(responseString, Period.class);
                    mView.onGetPeriod(period);
                } catch (Exception e){}
            }
        });
    }

    @Override
    public void getAllInfoFromPeriod(String zpfa, String crop, String area, String period) {
        GCFAApi.getAllInfoFromPeriod(zpfa, area, crop, period, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                AllInfo allInfo = new Gson().fromJson(responseString, AllInfo.class);
                mView.onGetAllInfoFromPeriod(allInfo);
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
