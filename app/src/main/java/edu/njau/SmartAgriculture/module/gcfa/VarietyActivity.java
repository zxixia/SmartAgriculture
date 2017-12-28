package edu.njau.SmartAgriculture.module.gcfa;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.activities.BaseTitleNormalActivity;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;
import edu.njau.SmartAgriculture.module.gcfa.mvp.VarietyInfoContract;
import edu.njau.SmartAgriculture.module.gcfa.mvp.VarietyPresenter;

/**
 * Created by lenovo on 2017/12/16.
 */

public class VarietyActivity extends BaseTitleNormalActivity implements VarietyInfoContract.View {

    private String mCropId;
    private String mZpfaId;
    private String mAreaId;
    private String mPageTitle;
    @Bind(R.id.tv_gcfa_verity)
    TextView mVariety;

    @Override
    protected int getChildContentViewId() {
        return R.layout.activity_gcfa_variety;
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
    protected void initWindow() {
        super.initWindow();
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle(mPageTitle);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setIconBack(R.mipmap.btn_back_normal);
        // 初始化presenter
        new VarietyPresenter(this);
        Intent intent = getIntent();
        mAreaId = intent.getStringExtra("AreaID");
        mCropId = intent.getStringExtra("CropID");
        mZpfaId = intent.getStringExtra("ZpfaID");
        mPageTitle = intent.getStringExtra("TYPE");
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

    VarietyInfoContract.Presenter mPresenter;

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getVriety(mZpfaId, mCropId, mAreaId);
    }

    @Override
    public void setPresenter(VarietyInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNetworkError(int strId) {}

    @Override
    public void onGetVriety(Variety variety) {
        refreshComplete();
        mVariety.setText(variety.getResponse().getVarietyyield());
    }
}
