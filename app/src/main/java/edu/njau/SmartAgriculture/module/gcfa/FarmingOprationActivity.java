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
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;
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

    @Bind(R.id.tv_gcfa_op_month_time)
    TextView mPeriodMonthTime;
    @Bind(R.id.tv_gcfa_op_cropperiod)
    TextView mCropPeriod;

    @Bind(R.id.tv_gcfa_op_date_time)
    TextView mPeriodDateTime;



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

        try{
            if (allInfo.getResponse().getHighplantlist().size() > 0) {

                String mPeriodDateTime_Str = "";
                String mFarmingOperation_Str = "";
                String mFarmingOperationDescription_Str = "";
                String mPestControl_Str = "";
                String mPestControlDescription_Str = "";
                String mLeafAge_Str = "";
                String mStemGrowth_Str = "";
                String mPeriodMonthTime_Str = "";
                String mCropPeriod_Str = "";

                List<String> mPeriodDateTime_List = new ArrayList<String>();
                List<String> mCropPeriod_List = new ArrayList<String>();
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
                    String mCropPeriod_Txt =
                            allInfo.getResponse().getHighplantlist().
                                    get(i).getCropperiod();

                    String mFarmingOperation_Txt =
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
                    if(!mCropPeriod_List.contains(mCropPeriod_Txt)){
                        mCropPeriod_List.add(mCropPeriod_Txt);
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

                for(int i=0;i<mCropPeriod_List.size();i++){
                    mCropPeriod_Str = mCropPeriod_Str
                            +mCropPeriod_List.get(i)+"  ";
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
                mCropPeriod.setText(mCropPeriod_Str);
                mFarmingOperation.setText(mFarmingOperation_Str);
                mFarmingOperationDescription.setText(mFarmingOperationDescription_Str);
                mPestControl.setText(mPestControl_Str);
                mPestControlDescription.setText(mPestControlDescription_Str);

                mLeafAge.setText(mLeafAge_Str);
                mStemGrowth.setText(mStemGrowth_Str);
                mPeriodMonthTime.setText(mPeriodMonthTime_Str);

            } else {
                mPeriodDateTime.setText("无数据");
                mCropPeriod.setText("无数据");
                mPestControl.setText("无数据");
                mPestControlDescription.setText("无数据");
                mFarmingOperation.setText("无数据");
                mFarmingOperationDescription.setText("无数据");
                mLeafAge.setText("无数据");
                mStemGrowth.setText("无数据");
                mPeriodMonthTime.setText("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        if (allInfo.getResponse().getHighplantlist().size() > 0) {
//            mPestControl.setText(allInfo.getResponse().getHighplantlist().get(0).getPestcontrol().getPestcontroltype());
//            mPestControlDescription.setText(allInfo.getResponse().getHighplantlist().get(0).getPestcontrol().getPestcontrolcontent());
//            mFarmingOperation.setText(allInfo.getResponse().getHighplantlist().get(0).getFarmingoperation().getFarmingoperationtype());
//            mFarmingOperationDescription.setText(allInfo.getResponse().getHighplantlist().get(0).getFarmingoperation().getFarmingoperationcontent());
//            mLeafAge.setText(allInfo.getResponse().getHighplantlist().get(0).getLeafage());
//            mStemGrowth.setText(allInfo.getResponse().getHighplantlist().get(0).getStemgrowth());
//
//        } else {
//            mPestControl.setText("无数据");
//            mPestControlDescription.setText("无数据");
//            mFarmingOperation.setText("无数据");
//            mFarmingOperationDescription.setText("无数据");
//            mLeafAge.setText(mMonthList.get(mSpinnerAdapter.getSelectIndex()).getShowText() + ", 无数据");
//            mStemGrowth.setText(getMonthPeriod() + ", 无数据");
//        }
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
            setCurrentTypeValue(allInfo.getResponse().getHighplantlist().get(0).getCropperiod());
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

    @Override
    public void onGetSuitableCrop(Variety variety) {
        super.onGetSuitableCrop(variety);
    }
}
