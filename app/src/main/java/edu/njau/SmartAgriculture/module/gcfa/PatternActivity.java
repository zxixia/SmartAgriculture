package edu.njau.SmartAgriculture.module.gcfa;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.activities.BaseTitleNormalActivity;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;
import edu.njau.SmartAgriculture.module.gcfa.mvp.VarietyInfoContract;
import edu.njau.SmartAgriculture.module.gcfa.mvp.VarietyPresenter;

/**
 * Created by Lenovo on 2017/12/29.
 */

public class PatternActivity extends BaseTitleNormalActivity {

    private String mCropId;
    private String mZpfaId;
    private String mAreaId;
    private String mPageTitle;
    @Bind(R.id.web_gcfa_pattern)
    WebView mPattern;

    @Override
    protected int getChildContentViewId() {
        return R.layout.activity_gcfa_pattern;
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

        Intent intent = getIntent();
        mAreaId = intent.getStringExtra("AreaID");
        mCropId = intent.getStringExtra("CropID");
        mZpfaId = intent.getStringExtra("ZpfaID");
        mPageTitle = intent.getStringExtra("TYPE");

        String URL = "http://47.93.227.232:8086/zpfa/" +
                mZpfaId + ".html";

        Log.e("URL",""+URL);
        mPattern.loadUrl(URL);
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

    }


}

