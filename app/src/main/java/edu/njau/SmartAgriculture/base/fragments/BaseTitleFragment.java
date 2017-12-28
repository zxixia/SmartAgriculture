package edu.njau.SmartAgriculture.base.fragments;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewStub;

import net.jiawa.debughelper.XLog;

import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.widgets.TitleBar;

/**
 * Created by JuQiu
 * on 16/9/5.
 */
public abstract class BaseTitleFragment extends BaseFragment {

    TitleBar mTitleBar;

    @Override
    protected int getLayoutId() {
        if (isSearch())
            return R.layout.fragment_base_title_search;
        return R.layout.fragment_base_title;
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);
        XLog.d(true, 1);
        // on before onBindViewBefore call
        ViewStub stub = (ViewStub) root.findViewById(R.id.lay_content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        // not null
        mTitleBar = (TitleBar) root.findViewById(R.id.nav_title_bar);
        mTitleBar.setTitle(getTitleRes());
        mTitleBar.setIcon(getIconRes());
        mTitleBar.setIconOnClickListener(getIconClickListener());
    }

    protected abstract
    @LayoutRes
    int getContentLayoutId();

    protected abstract
    boolean isSearch();

    protected abstract
    @StringRes
    int getTitleRes();

    protected
    @DrawableRes
    int getIconRes() {
        return 0;
    }

    protected View.OnClickListener getIconClickListener() {
        return null;
    }
}
