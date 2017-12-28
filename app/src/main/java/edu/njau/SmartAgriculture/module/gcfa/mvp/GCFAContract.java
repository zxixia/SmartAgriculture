package edu.njau.SmartAgriculture.module.gcfa.mvp;

import edu.njau.SmartAgriculture.base.mvp.presenter.BaseListPresenter;
import edu.njau.SmartAgriculture.base.mvp.view.BaseListView;
import edu.njau.SmartAgriculture.module.main.bean.MainItem;

/**
 *  这里定义交互的接口
 *  具体的实现见
 *  GCFAPresenter
 */
public class GCFAContract {

    // 这里的List用来放的是Grid
    public interface GCFAPresenter extends BaseListPresenter {
        void generateMainItems(); // 生成主页九宫格菜单
        void getCropType(); // 请求作物类型
        void getPlantType(); // 请求栽种方式
    }

    public interface GCFAView extends BaseListView<GCFAPresenter, MainItem> {
        void onGetCropType(); // 请求作物类型回调
        void onGetPlantType(); // 请求栽种方式回调
        /*
         * 处理首页九宫格菜单不需要单独的函数
         * 因为在BaseListView中有专门的onRefreshSuccess函数
         */
    }
}
