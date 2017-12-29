package edu.njau.SmartAgriculture.module.gcfa.mvp;

import java.util.ArrayList;

import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.module.main.bean.MainItem;

/**
 * Created by lenovo on 2017/12/9.
 */

public class GCFAPresenter implements GCFAContract.GCFAPresenter {

    // 只生成一次主页九宫格菜单
    private boolean mHasGenerateMainItems = false;
    private ArrayList<MainItem> mMainItems;
    // 当前Presenter要面对的View
    private GCFAContract.GCFAView mView;

    public GCFAPresenter(GCFAContract.GCFAView view) {
        mView = view;
    }

    @Override
    public void generateMainItems() {
        if (mHasGenerateMainItems) return;
        // 生产菜单
        mMainItems = new ArrayList<>();
        mMainItems.add(new MainItem("模式图", R.mipmap.ic_main_jinquesheji));
        mMainItems.add(new MainItem("生育时期", R.mipmap.ic_main_jinquesheji));
        mMainItems.add(new MainItem("农事操作", R.mipmap.ic_main_jinqueyuce));
//        mMainItems.add(new MainItem("病虫草防治", R.mipmap.ic_main_jinquezhengduan));
//        mMainItems.add(new MainItem("品种类型", R.mipmap.ic_main_jichunongqing));
        /**
         * 调用BaseListView中的函数将数据
         * 刷新到界面上
         */
        mView.onRefreshSuccess(mMainItems);
        mHasGenerateMainItems = true;
    }

    @Override
    public void getCropType() {
        mView.onGetCropType();
    }

    @Override
    public void getPlantType() {
        mView.onGetPlantType();
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
         * 1，生成下面的九宫格菜单信息
         */
        generateMainItems();
        getCropType();
        getPlantType();
        mView.onComplete();
    }

    @Override
    public void onLoadMore() {} // 这个暂时不做处理
}
