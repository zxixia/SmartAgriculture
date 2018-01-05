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
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;
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
    //时间
    @Bind(R.id.tv_gcfa_period_month_time)
    TextView mPeriodMonthTime;
    //日期
    @Bind(R.id.tv_gcfa_period_date_time)
    TextView mPeriodDateTime;



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

                String mPeriodDateTime_Str = "";
                String mFarmingOperation_Str = "";
                String mFarmingOperationDescription_Str = "";
                String mPestControl_Str = "";
                String mPestControlDescription_Str = "";
                String mLeafAge_Str = "";
                String mStemGrowth_Str = "";
                String mPeriodMonthTime_Str = "";

                List<String> mPeriodDateTime_List = new ArrayList<String>();
                List<String> mFarmingOperation_List = new ArrayList<String>();
                List<String> mFarmingOperationDescription_List = new ArrayList<String>();
                List<String> mPestControl_List = new ArrayList<String>();
                List<String> mPestControlDescription_List = new ArrayList<String>();
                List<String> mLeafAge_List = new ArrayList<String>();
                List<String> mStemGrowth_List = new ArrayList<String>();
                List<String> mPeriodMonthTime_List = new ArrayList<String>();

                for(int i=0;i<allInfo.getResponse().getHighplantlist().size();i++){


                    String mPeriodDateTime_Txt =
                            allInfo.getResponse().getHighplantlist().
                                    get(i).getCropPeriodDay();
                    String mFarmingOperation_Txt =allInfo.getResponse().getHighplantlist().
                            get(i).getFarmingoperation()
                            .getFarmingoperationtype()+":"+
                            allInfo.getResponse().getHighplantlist().
                            get(i).getFarmingoperation()
                            .getFarmingoperationname();
                    String mFarmingOperationDescription_Txt =
                            allInfo.getResponse().getHighplantlist()
                            .get(i).getFarmingoperation()
                            .getFarmingoperationcontent();
                    String mPestControl_Txt =
                            allInfo.getResponse().getHighplantlist()
                            .get(i).getPestcontrol()
                            .getDescription();
                    String mPestControlDescription_Txt =
                            allInfo.getResponse().getHighplantlist()
                            .get(i).getPestcontrol()
                            .getPestcontrolcontent();
                    String mLeafAge_Txt =
                            allInfo.getResponse().getHighplantlist()
                            .get(i)
                            .getLeafage();
                    String mStemGrowth_Txt =
                            allInfo.getResponse().getHighplantlist()
                            .get(i).getStemgrowth();
                    String mPeriodMonthTime_Txt =
                            allInfo.getResponse().getHighplantlist()
                            .get(i).getCropmonth()
                            +allInfo.getResponse().getHighplantlist()
                            .get(i).getMonthperiod();

                    if(!mPeriodDateTime_List.contains(mPeriodDateTime_Txt)){
                        mPeriodDateTime_List.add(mPeriodDateTime_Txt);
                    }

                    if(!mFarmingOperation_List.contains(mFarmingOperation_Txt)){
                        mFarmingOperation_List.add(mFarmingOperation_Txt);
                    }

                    if(!mFarmingOperationDescription_List.contains(mFarmingOperationDescription_Txt)){
                        mFarmingOperationDescription_List.add(mFarmingOperationDescription_Txt);
                    }

                    if(!mPestControl_List.contains(mPestControl_Txt)){
                        mPestControl_List.add(mPestControl_Txt);
                    }

                    if(!mPestControlDescription_List.contains(mPestControlDescription_Txt)){
                        mPestControlDescription_List.add(mPestControlDescription_Txt);
                    }

                    if(!mLeafAge_List.contains(mLeafAge_Txt)){
                        mLeafAge_List.add(mLeafAge_Txt);
                    }

                    if(!mStemGrowth_List.contains(mStemGrowth_Txt)){
                        mStemGrowth_List.add(mStemGrowth_Txt);
                    }

                    if(!mPeriodMonthTime_List.contains(mPeriodMonthTime_Txt)){
                        mPeriodMonthTime_List.add(mPeriodMonthTime_Txt);
                    }


//                    mFarmingOperation_Txt = mFarmingOperation_Txt
//                            +allInfo.getResponse().getHighplantlist().
//                            get(i).getFarmingoperation()
//                            .getFarmingoperationname();
//                    mFarmingOperationDescription_Txt = mFarmingOperationDescription_Txt
//                            +allInfo.getResponse().getHighplantlist()
//                            .get(i).getFarmingoperation()
//                            .getFarmingoperationcontent();
//                    mPestControl_Txt = mPestControl_Txt
//                            +allInfo.getResponse().getHighplantlist()
//                            .get(i).getPestcontrol()
//                            .getDescription();
//                    mPestControlDescription_Txt = mPestControlDescription_Txt
//                            +allInfo.getResponse().getHighplantlist()
//                            .get(i).getPestcontrol()
//                            .getPestcontrolcontent();
//                    mLeafAge_Txt = mLeafAge_Txt
//                            +allInfo.getResponse().getHighplantlist()
//                            .get(i)
//                            .getLeafage()+"  ";
//                    mStemGrowth_Txt = mStemGrowth_Txt
//                            +allInfo.getResponse().getHighplantlist()
//                            .get(i).getStemgrowth()+"  ";
//                    mPeriodMonthTime_Txt = mPeriodMonthTime_Txt
//                            +allInfo.getResponse().getHighplantlist()
//                            .get(i).getCropmonth()
//                            +allInfo.getResponse().getHighplantlist()
//                            .get(i).getMonthperiod()+"  ";

                }

                for(int i=0;i<mPeriodDateTime_List.size();i++){
                    mPeriodDateTime_Str = mPeriodDateTime_Str
                            +mPeriodDateTime_List.get(i)+"  ";
                }

                for(int i=0;i<mFarmingOperation_List.size();i++){
                    mFarmingOperation_Str = mFarmingOperation_Str
                            +mFarmingOperation_List.get(i)+"  ";
                }

                for(int i=0;i<mFarmingOperationDescription_List.size();i++){
                    mFarmingOperationDescription_Str = mFarmingOperationDescription_Str
                            +mFarmingOperationDescription_List.get(i)+"  ";
                }

                for(int i=0;i<mPestControl_List.size();i++){
                    mPestControl_Str = mPestControl_Str
                            +mPestControl_List.get(i)+"  ";
                }

                for(int i=0;i<mPestControlDescription_List.size();i++){
                    mPestControlDescription_Str = mPestControlDescription_Str
                            +mPestControlDescription_List.get(i)+"  ";
                }

                for(int i=0;i<mLeafAge_List.size();i++){
                    mLeafAge_Str = mLeafAge_Str
                            +mLeafAge_List.get(i)+"  ";
                }

                for(int i=0;i<mStemGrowth_List.size();i++){
                    mStemGrowth_Str = mStemGrowth_Str
                            +mStemGrowth_List.get(i)+"  ";
                }

                for(int i=0;i<mPeriodMonthTime_List.size();i++){
                    mPeriodMonthTime_Str = mPeriodMonthTime_Str
                            +mPeriodMonthTime_List.get(i)+"  ";
                }


                mPeriodDateTime.setText(mPeriodDateTime_Str);
                mFarmingOperation.setText(mFarmingOperation_Str);
                mFarmingOperationDescription.setText(mFarmingOperationDescription_Str);
                mPestControl.setText(mPestControl_Str);
                mPestControlDescription.setText(mPestControlDescription_Str);

                mLeafAge.setText(mLeafAge_Str);
                mStemGrowth.setText(mStemGrowth_Str);
                mPeriodMonthTime.setText(mPeriodMonthTime_Str);

            } else {
                mPeriodDateTime.setText("无数据");
                mPestControl.setText("无数据");
                mPestControlDescription.setText("无数据");
                mFarmingOperation.setText("无数据");
                mFarmingOperationDescription.setText("无数据");
                mLeafAge.setText("无数据");
                mStemGrowth.setText("无数据");
                mPeriodMonthTime.setText("无数据");
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

    @Override
    public void onGetSuitableCrop(Variety variety) {
        super.onGetSuitableCrop(variety);
    }
}
