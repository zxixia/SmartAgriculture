package edu.njau.SmartAgriculture.module.main.mvp;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import net.jiawa.debughelper.XLog;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.api.AMapApi;
import edu.njau.SmartAgriculture.bean.amap.weather.Weather;
import edu.njau.SmartAgriculture.module.amap.AMapHelper;
import edu.njau.SmartAgriculture.module.main.bean.MainItem;

/**
 * Created by lenovo on 2017/12/9.
 */

public class MainPresenter implements MainContract.Presenter {

    // 只生成一次主页九宫格菜单
    private boolean mHasGenerateMainItems = false;
    private ArrayList<MainItem> mMainItems;
    // 当前Presenter要面对的View
    private MainContract.View mMainView;

    public MainPresenter(MainContract.View mainView) {
        mMainView = mainView;
    }

    @Override
    public void getWeather(String adCode) {
        // 这里需要处理天气数据的请求
        AMapApi.getWeather(adCode, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                mMainView.onComplete();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Weather weather = new Gson().fromJson(responseString, Weather.class);
                XLog.d(true, 5, responseString);
                mMainView.onGetWeatherSucceed(weather);
                mMainView.onComplete();
            }
        });
    }

    @Override
    public void getDistrict() {
        AMapHelper.getInstance().addLocationListener(new AMapHelper.LocationListener() {
            @Override
            public void onGetLocation(AMapLocation aMapLocation) {
                mMainView.onGetDistrict(aMapLocation.getDistrict());
                getWeather(aMapLocation.getAdCode());
            }
        });
        AMapHelper.getInstance().startLocation();
    }

    @Override
    public void generateMainItems() {
        if (mHasGenerateMainItems) return;
        // 生产菜单
        mMainItems = new ArrayList<>();
        mMainItems.add(new MainItem("精确设计", R.mipmap.ic_main_jinquesheji));
        mMainItems.add(new MainItem("精确诊断", R.mipmap.ic_main_jinquezhengduan));
        mMainItems.add(new MainItem("精确预测", R.mipmap.ic_main_jinqueyuce));
        mMainItems.add(new MainItem("基础农情", R.mipmap.ic_main_jichunongqing));
        mMainItems.add(new MainItem("灾害应对", R.mipmap.ic_main_zaihaiyingdui));
        mMainItems.add(new MainItem("农机通信", R.mipmap.ic_main_nongjitongxin));
        mMainItems.add(new MainItem("栽培模式", R.mipmap.ic_main_zaipeimoshi));
        mMainItems.add(new MainItem("农技课堂", R.mipmap.ic_main_nongjiketang));
        mMainItems.add(new MainItem("智能问答", R.mipmap.ic_main_zhinengwenda));
        /**
         * 调用BaseListView中的函数将数据
         * 刷新到界面上
         */
        mMainView.onRefreshSuccess(mMainItems);
        mHasGenerateMainItems = true;
    }

    @Override
    public void onRefreshing() {
        /*
         * 第一次调用在BaseTitleRecyclerFragment的
         * initDatail里面
         *
         * 这里的意思是相当于用户下拉刷新控件,请求刷新
         * 这里有两件事需要做,
         *
         * 1，请求天气数据
         * 2，生成下面的九宫格菜单信息
         */
        getDistrict();
        generateMainItems();
    }

    @Override
    public void onLoadMore() {} // 这个暂时不做处理
}
