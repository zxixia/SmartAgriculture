package edu.njau.SmartAgriculture.module.gcfa.mvp;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import net.jiawa.debughelper.XLog;

import cz.msebera.android.httpclient.Header;
import edu.njau.SmartAgriculture.api.AMapApi;
import edu.njau.SmartAgriculture.api.GCFAApi;
import edu.njau.SmartAgriculture.bean.amap.weather.Weather;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.farmingoperation.FarmingOperation;
import edu.njau.SmartAgriculture.bean.gcfa.period.Period;
import edu.njau.SmartAgriculture.bean.gcfa.pestcontrol.PestControl;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;
import edu.njau.SmartAgriculture.module.amap.AMapHelper;

/**
 * Created by lenovo on 2017/12/16.
 */

public class VarietyPresenter implements VarietyInfoContract.Presenter {

    private VarietyInfoContract.View mView;
    public VarietyPresenter(VarietyInfoContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getVriety(String zpfa, String crop, String area) {
        GCFAApi.getVarietyYield(zpfa, area, crop, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Variety variety = new Gson().fromJson(responseString, Variety.class);
                mView.onGetVriety(variety);
            }
        });
    }
}
