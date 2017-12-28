package edu.njau.SmartAgriculture.module.gcfa;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import net.jiawa.debughelper.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.adapter.BaseSpinnerAdapter;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.period.Period;
import edu.njau.SmartAgriculture.bean.gcfa.period.Periodlist;
import edu.njau.SmartAgriculture.module.gcfa.mvp.PeriodContract;
import edu.njau.SmartAgriculture.module.gcfa.mvp.PeriodPresenter;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public class PeriodActivity extends CommonTopInfoActivity implements PeriodContract.View {

    PeriodContract.Presenter mPresenter;
    List<BaseSpinnerAdapter.interf> mPeriodList = new ArrayList<BaseSpinnerAdapter.interf>();
    @Bind(R.id.sp_gcfa_period_list)
    Spinner mSpinnerPeriodList;
    BaseSpinnerAdapter mSpinnerAdapter;
    @Bind(R.id.tv_gcfa_period_leaf_age)
    TextView mLeafAge;
    @Bind(R.id.tv_gcfa_period_stem_growth)
    TextView mStemGrowth;
    @Bind(R.id.tv_gcfa_period_farming_operation)
    TextView mFarmingOperation;
    @Bind(R.id.tv_gcfa_period_farming_operation_description)
    TextView mFarmingOperationDescription;
    @Bind(R.id.tv_gcfa_period_pest_control)
    TextView mPestControl;
    @Bind(R.id.tv_gcfa_period_pest_control_description)
    TextView mPestControlDescription;

    @Override
    protected int getSubViewId() {
        return R.layout.activity_gcfa_period;
    }

    @Override
    protected String getPageTitle() {
        return "生育时期";
    }

    @Override
    protected String getCurrentTypeName() {
        return "生育期";
    }

    @Override
    public void onGetPeriod(Period period) {
        if (mPeriodList.size() > 0) {
            mPeriodList.clear();
        }
        for (int i=0; i<period.getResponse().getPeriodlist().size(); i++) {
            Periodlist periodlist = period.getResponse().getPeriodlist().get(i);
            mPeriodList.add(new CommonTopInfoActivity.InfoType(periodlist.getPeriod()));
        }
        mSpinnerAdapter.setSelectIndex(0);
        mSpinnerAdapter.notifyDataSetChanged();
        mSpinnerPeriodList.setSelection(0);
        mPresenter.getAllInfoFromPeriod(getZpfaId(), getCropId(), getAreaId(), mPeriodList.get(0).getShowText());
        XLog.d(true, 5, "--------");
    }

    @Override
    public void onGetAllInfoFromPeriod(AllInfo allInfo) {
        // stop the swipe
        refreshComplete();
        try {
            if (allInfo.getResponse().getHighplantlist().size() > 0) {
                mFarmingOperation.setText(allInfo.getResponse().getHighplantlist().get(0).getFarmingoperation().getFarmingoperationname());
                mFarmingOperationDescription.setText(allInfo.getResponse().getHighplantlist().get(0).getFarmingoperation().getFarmingoperationcontent());
                mPestControl.setText(allInfo.getResponse().getHighplantlist().get(0).getPestcontrol().getDescription());
                mPestControlDescription.setText(allInfo.getResponse().getHighplantlist().get(0).getPestcontrol().getPestcontrolcontent());

                mLeafAge.setText(allInfo.getResponse().getHighplantlist().get(0).getLeafage());
                mStemGrowth.setText(allInfo.getResponse().getHighplantlist().get(0).getStemgrowth());
            } else {
                mPestControl.setText("无数据");
                mPestControlDescription.setText("无数据");
                mFarmingOperation.setText("无数据");
                mFarmingOperationDescription.setText("无数据");
                mLeafAge.setText("无数据");
                mStemGrowth.setText("无数据");
            }
        } catch (Exception e) {}
    }

    @Override
    public void setPresenter(PeriodContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNetworkError(int strId) {}

    @Override
    protected void initWidget() {
        super.initWidget();
        new PeriodPresenter(this);
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getSuitableCrop(getZpfaId(), getCropId(), getAreaId());
        mPresenter.getCommonTopInfoFromMonthAndMonthPeriod(getZpfaId(), getAreaId(), getCropId(), getCurrentMonth(), getCurrentMonthPeriod());
        mPresenter.getPeriod(getZpfaId(), getAreaId(), getCropId());
    }

    @Override
    protected void initData() {
        super.initData();
        mSpinnerAdapter = new BaseSpinnerAdapter(this, mPeriodList);
        mSpinnerPeriodList.setAdapter(mSpinnerAdapter);
        mSpinnerPeriodList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 需要根据当前生育期请求数据
                showSwipe();
                mPresenter.getAllInfoFromPeriod(getZpfaId(), getCropId(), getAreaId(), mPeriodList.get(position).getShowText());
                mSpinnerAdapter.setSelectIndex(position);
                mSpinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onGetCommonTopInfoFromMonthAndMonthPeriod(AllInfo allInfo) {
        super.onGetCommonTopInfoFromMonthAndMonthPeriod(allInfo);
        if (allInfo.getResponse().getHighplantlist().size() > 0) {
            setCurrentTypeValue(allInfo.getResponse().getHighplantlist().get(0).getCropperiod());
        } else {
            setCurrentTypeValue("无数据");
        }
    }
}
