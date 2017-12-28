package edu.njau.SmartAgriculture.module.gcfa;

import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.adapter.BaseSpinnerAdapter;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.module.gcfa.mvp.FarmingOperationContract;
import edu.njau.SmartAgriculture.module.gcfa.mvp.FarmingOperationPresenter;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public class FarmingOprationActivity extends CommonTopInfoActivity implements FarmingOperationContract.View, View.OnClickListener {

    FarmingOperationContract.Presenter mPresenter;

    List<BaseSpinnerAdapter.interf> mMonthList = new ArrayList<BaseSpinnerAdapter.interf>();
    @Bind(R.id.sp_gcfa_fo_time)
    Spinner mSpinnerMonth;
    BaseSpinnerAdapter mSpinnerAdapter;

    @Bind(R.id.rb_gcfa_fo_up)
    RadioButton mRadioUp;
    @Bind(R.id.rb_gcfa_fo_middle)
    RadioButton mRadioMiddle;
    @Bind(R.id.rb_gcfa_fo_down)
    RadioButton mRadioDown;
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
        return R.layout.activity_gcfa_farming_operation;
    }

    @Override
    protected String getPageTitle() {
        return "农事操作";
    }

    @Override
    protected String getCurrentTypeName() {
        return "农事操作";
    }

    @Override
    public void onGetAllInfoFromMonthAndMonthPeriod(AllInfo allInfo) {
        refreshComplete();
        if (allInfo.getResponse().getHighplantlist().size() > 0) {
            mPestControl.setText(allInfo.getResponse().getHighplantlist().get(0).getPestcontrol().getPestcontroltype());
            mPestControlDescription.setText(allInfo.getResponse().getHighplantlist().get(0).getPestcontrol().getPestcontrolcontent());
            mFarmingOperation.setText(allInfo.getResponse().getHighplantlist().get(0).getFarmingoperation().getFarmingoperationtype());
            mFarmingOperationDescription.setText(allInfo.getResponse().getHighplantlist().get(0).getFarmingoperation().getFarmingoperationcontent());
            mLeafAge.setText(allInfo.getResponse().getHighplantlist().get(0).getLeafage());
            mStemGrowth.setText(allInfo.getResponse().getHighplantlist().get(0).getStemgrowth());
        } else {
            mPestControl.setText("无数据");
            mPestControlDescription.setText("无数据");
            mFarmingOperation.setText("无数据");
            mFarmingOperationDescription.setText("无数据");
            mLeafAge.setText(mMonthList.get(mSpinnerAdapter.getSelectIndex()).getShowText() + ", 无数据");
            mStemGrowth.setText(getMonthPeriod() + ", 无数据");
        }
    }

    @Override
    public void setPresenter(FarmingOperationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNetworkError(int strId) {}

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getSuitableCrop(getZpfaId(), getCropId(), getAreaId());
        mPresenter.getCommonTopInfoFromMonthAndMonthPeriod(getZpfaId(), getAreaId(), getCropId(), getCurrentMonth(), getCurrentMonthPeriod());
        requestData();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        new FarmingOperationPresenter(this);
    }

    @Override
    public void onGetCommonTopInfoFromMonthAndMonthPeriod(AllInfo allInfo) {
        super.onGetCommonTopInfoFromMonthAndMonthPeriod(allInfo);
        if (allInfo.getResponse().getHighplantlist().size() > 0) {
            setCurrentTypeValue(allInfo.getResponse().getHighplantlist().get(0).getFarmingoperation().getFarmingoperationtype());
        } else {
            setCurrentTypeValue("无数据");
        }
    }

    private void initMonthList() {
        mMonthList.add(new InfoType("一月"));
        mMonthList.add(new InfoType("二月"));
        mMonthList.add(new InfoType("三月"));
        mMonthList.add(new InfoType("四月"));
        mMonthList.add(new InfoType("五月"));
        mMonthList.add(new InfoType("六月"));
        mMonthList.add(new InfoType("七月"));
        mMonthList.add(new InfoType("八月"));
        mMonthList.add(new InfoType("九月"));
        mMonthList.add(new InfoType("十月"));
        mMonthList.add(new InfoType("十一月"));
        mMonthList.add(new InfoType("十二月"));
    }

    private String getMonthPeriod() {
        if (mRadioUp.isChecked()) return "上旬";
        if (mRadioMiddle.isChecked()) return "中旬";
        return "下旬";
    }

    @Override
    protected void initData() {
        super.initData();
        initMonthList();
        mSpinnerAdapter = new BaseSpinnerAdapter(this, mMonthList);
        mSpinnerMonth.setAdapter(mSpinnerAdapter);
        mSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinnerAdapter.setSelectIndex(position);
                mSpinnerAdapter.notifyDataSetChanged();
                requestData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @OnClick({R.id.rb_gcfa_fo_up, R.id.rb_gcfa_fo_middle, R.id.rb_gcfa_fo_down})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rb_gcfa_fo_up ||
                v.getId() == R.id.rb_gcfa_fo_middle ||
                v.getId() == R.id.rb_gcfa_fo_down) {
            requestData();
        }
    }

    private void requestData() {
        showSwipe();
        mPresenter.getAllInfoFromMonthAndMonthPeriod(getZpfaId(), getCropId(), getAreaId(),
                mMonthList.get(mSpinnerAdapter.getSelectIndex()).getShowText(), getMonthPeriod());
    }
}
