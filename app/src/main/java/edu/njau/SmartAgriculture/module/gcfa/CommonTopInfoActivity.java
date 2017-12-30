package edu.njau.SmartAgriculture.module.gcfa;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import net.jiawa.debughelper.XLog;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.activities.BaseTitleNormalActivity;
import edu.njau.SmartAgriculture.base.adapter.BaseSpinnerAdapter;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;
import edu.njau.SmartAgriculture.module.gcfa.mvp.CommonTopInfoContract;
import edu.njau.SmartAgriculture.module.gcfa.mvp.PeriodContract;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public abstract class CommonTopInfoActivity extends BaseTitleNormalActivity implements CommonTopInfoContract.View {

    @Bind(R.id.tv_middle_current_type_name)
    TextView mCurrentTypeName;
    @Bind(R.id.tv_middle_current_type_value)
    TextView mCurrentTypeValue;
    @Bind(R.id.tv_leaf_age)
    TextView mLeafAge;
    @Bind(R.id.tv_stem_growth)
    TextView mStemGrowth;
    @Bind(R.id.tv_gcfa_suitable_crop)
    TextView mSuitableCrop;

    /**
     * update 2017-12-29
     */
    //位置
    @Bind(R.id.tv_gcfa_current_location)
    TextView mLocation;
    //当前月份
    @Bind(R.id.tv_middle_current_month_value)
    TextView mMonthValue;
    //栽培方案名称
    @Bind(R.id.tv_gcfa_current_zpfa_name)
    TextView mZPFAName;



    private String mCropId = "0";
    private String mZpfaId = "00000000001";
    private String mAreaId = "0000000001";
    private String mBLocation = "nanjin";
    private String mPageTitle;
    private String mGZFAName = "";
    CommonTopInfoContract.Presenter mPresenter;

    @Override
    protected int getChildContentViewId() {
        return R.layout.activity_gcfa_common_top_info;
    }

    @Override
    protected boolean isSearch() {
        return false;
    }

    @Override
    protected boolean supportNestedScrollView() {
        return true;
    }

    @Override
    public void onGetCommonTopInfoFromMonthAndMonthPeriod(AllInfo allInfo) {
        mMonthValue.setText(getCurrentMonth()+getCurrentMonthPeriod());
        if (allInfo.getResponse().getHighplantlist().size() > 0) {
            mLeafAge.setText(allInfo.getResponse().getHighplantlist().get(0).getLeafage());
            mStemGrowth.setText(allInfo.getResponse().getHighplantlist().get(0).getStemgrowth());

        } else {
            mLeafAge.setText(getCurrentMonth() + ", 无数据");
            mStemGrowth.setText(getCurrentMonthPeriod() + ", 无数据");
        }
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setIconBack(R.mipmap.btn_back_normal);

        Intent intent = getIntent();
        mAreaId = intent.getStringExtra("AreaID");
        mCropId = intent.getStringExtra("CropID");
        mZpfaId = intent.getStringExtra("ZpfaID");
        mBLocation = intent.getStringExtra("BLocation");
        mPageTitle = intent.getStringExtra("TYPE");
        mGZFAName = intent.getStringExtra("mGZFAName");


    }

    @Override
    protected void initWindow() {
        super.initWindow();
        // on before initWindow call
        ViewStub stub = (ViewStub) findViewById(R.id.lay_content);
        stub.setLayoutResource(getSubViewId());
        stub.inflate();
    }

    abstract
    protected int getSubViewId();

    public String getCropId() {
        return mCropId;
    }

    public String getZpfaId() {
        return mZpfaId;
    }

    public String getAreaId() {
        return mAreaId;
    }

    protected String getCurrentMonth() {
        Calendar c = Calendar.getInstance();//
        int month = c.get(Calendar.MONTH) + 1;
        String value = "";
        switch (month) {
            case 1: value = "一"; break;
            case 2: value = "二"; break;
            case 3: value = "三"; break;
            case 4: value = "四"; break;
            case 5: value = "五"; break;
            case 6: value = "六"; break;
            case 7: value = "七"; break;
            case 8: value = "八"; break;
            case 9: value = "九"; break;
            case 10: value = "十"; break;
            case 11: value = "十一"; break;
            case 12: value = "十二"; break;
            default: value = "五";
        }
        value = value + "月";
        XLog.d(true, 5, "current month: " + value + ", " + month);
        return value;
    }

    protected String getCurrentMonthPeriod() {
        Calendar c = Calendar.getInstance();//
        int date = c.get(Calendar.DAY_OF_MONTH);
        String value = "";
        if (date <= 10) {
            value = "上";
        } else if (date <= 20) {
            value = "中";
        } else {
            value = "下";
        }
        value = value + "旬";
        XLog.d(true, 5, "current month period: " + value + ", " + date);
        return value;
    }

    class InfoType implements BaseSpinnerAdapter.interf {
        final String name;

        public InfoType(String n) {
            name = n;
        }

        @Override
        public String getShowText() {
            return name;
        }
    }

    @Override
    protected View.OnClickListener getIconBackClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    abstract
    protected String getPageTitle();

    abstract
    protected String getCurrentTypeName();

    @Override
    protected void initData() {
        super.initData();
        setTitle(getPageTitle());
//        mCurrentTypeName.setText(getCurrentTypeName() + ":");
    }

    protected void setCurrentTypeValue(String v) {
        mCurrentTypeValue.setText(v);
    }

    @Override
    public void onGetSuitableCrop(Variety variety) {
        try {
            mZPFAName.setText(""+mGZFAName);
            mLocation.setText(mBLocation);
            mSuitableCrop.setText(variety.getResponse().getVarietyyield());
        } catch (Exception e) {}
    }
}
