package edu.njau.SmartAgriculture.module.main.mvp;

import android.content.Context;

import edu.njau.SmartAgriculture.base.mvp.presenter.BaseListPresenter;
import edu.njau.SmartAgriculture.base.mvp.view.BaseListView;
import edu.njau.SmartAgriculture.bean.amap.weather.Weather;
import edu.njau.SmartAgriculture.module.main.bean.MainItem;

/**
 *  这里定义交互的接口
 *  具体的实现见
 *  Presenter
 */
public class MainContract {

    // 这里的List用来放的是Grid
    public interface Presenter extends BaseListPresenter {
        void getWeather(String adCode); // 请求天气数据
        void getDistrict();
        void generateMainItems(); // 生成主页九宫格菜单
    }

    public interface View extends BaseListView<Presenter, MainItem> {
        // 处理天气的两个函数
        void onGetWeatherSucceed(Weather weather);
        void onGetWeatherFailed();
        // 定位
        void onGetDistrict(String name);
        /*
         * 处理首页九宫格菜单不需要单独的函数
         * 因为在BaseListView中有专门的onRefreshSuccess函数
         */
    }
}
