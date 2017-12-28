package edu.njau.SmartAgriculture.module.search;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import net.jiawa.debughelper.XLog;
import net.jiawa.debughelper.flag.XFlag;

import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.activities.BaseActivity;
import edu.njau.SmartAgriculture.base.activities.BaseTitleActivity;
import edu.njau.SmartAgriculture.module.navigationbar.NavigationBarFragment;

public class SearchActivity extends BaseTitleActivity {

    @Override
    protected int getChildContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected boolean isSearch()
    {
        return true;
    }
}
