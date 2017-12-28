package edu.njau.SmartAgriculture.module.main;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import net.jiawa.debughelper.XLog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.adapter.BaseRecyclerAdapter;
import edu.njau.SmartAgriculture.bean.amap.weather.Weather;
import edu.njau.SmartAgriculture.module.SARecyclerFragment;
import edu.njau.SmartAgriculture.module.main.adapter.MainAdapter;
import edu.njau.SmartAgriculture.module.main.bean.MainItem;
import edu.njau.SmartAgriculture.module.main.mvp.MainContract;
import edu.njau.SmartAgriculture.module.main.mvp.MainPresenter;
import edu.njau.SmartAgriculture.module.search.SearchActivity;

/**
 * Created by zhaoxin5 on 2017/4/11.
 */

public class MainFragment extends SARecyclerFragment<MainContract.Presenter, MainItem>
        implements android.view.View.OnClickListener,
        MainContract.View /* MVP中的View */ {

    @Bind(R.id.tv_location_city)
    TextView mLocationCity;
    @Bind(R.id.tv_main_wind)
    TextView mWind;
    @Bind(R.id.tv_main_temperature)
    TextView mTemperature;
    @Bind(R.id.tv_main_date)
    TextView mDate;

    @Override
    protected void initData() {
        super.initData();
        mDate.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
    }

    @Override
    protected void initWidget(android.view.View root) {
        super.initWidget(root);
    }

    @Override
    protected BaseRecyclerAdapter<MainItem> getAdapter() {
        return new MainAdapter(getContext());
    }

    @Override
    protected void onItemClick(MainItem mainItem, int position) {
        XLog.d(true, 5);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onClick(android.view.View v) {
        if (v.getId() == R.id.iv_icon) {
            // 主页右上角的Search
            Intent intent = new Intent(this.getContext(), SearchActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }

    @Override
    public void onGetWeatherSucceed(Weather weather) {
        try {
            mWind.setText(weather.getLives().get(0).getWinddirection() + "风" +
                    weather.getLives().get(0).getWindpower() + "级");
            mTemperature.setText(weather.getLives().get(0).getTemperature() + "℃");
        } catch (Exception e) {}
    }

    @Override
    public void onGetWeatherFailed() {

    }

    @Override
    public void onGetDistrict(String name) {
        XLog.d(true, 5, name);
        mLocationCity.setText(name);
    }

    @Override
    protected void beforeInitData() {
        super.beforeInitData();
        MainPresenter mainPresenter = new MainPresenter(this);
        setPresenter(mainPresenter);
    }
}
