package edu.njau.SmartAgriculture.base.activities;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.utils.UiUtil;
import edu.njau.SmartAgriculture.widgets.TitleBar;

/**
 * Created by zhaoxin5 on 2017/4/20.
 */

public abstract class BaseTitleNormalActivity extends BaseActivity {

    @Bind(R.id.nav_title_bar)
    TitleBar mTitleBar;

    SwipeRefreshLayout mSwipe;

    @Override
    protected int getContentView() {
        if (supportNestedScrollView()) {
            if (isSearch()) {
                return R.layout.activity_base_title_normal_scroll_search;
            } else {
                return R.layout.activity_base_title_normal_scroll;
            }
        } else {
            if (isSearch()) {
                return R.layout.activity_base_title_normal_search;
            } else {
                return R.layout.activity_base_title_normal;
            }
        }
    }

    @Override
    protected void initWindow() {
        super.initWindow();
        setFullScreen(false);
        // on before initWindow call
        ViewStub stub = (ViewStub) findViewById(R.id.lay_content);
        stub.setLayoutResource(getChildContentViewId());
        stub.inflate();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        if (mTitleBar == null) {
            mTitleBar = (TitleBar) findViewById(R.id.nav_title_bar);
        }
        // 隐藏图标
        mTitleBar.setIcon(0);
        mTitleBar.setIconOnClickListener(getIconClickListener());

        mTitleBar.setIconBack(0);
        mTitleBar.setBackOnClickListener(getIconBackClickListener());

        if (supportNestedScrollView()) {
            mSwipe = (SwipeRefreshLayout) findViewById(R.id.sw_root);
            mSwipe.setColorSchemeResources(
                    R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                    R.color.swiperefresh_color3, R.color.swiperefresh_color4);
            mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshData();
                }
            });
        }
    }

    @Override
    protected void initData() {
        super.initData();
        if (supportNestedScrollView()) {
            mSwipe.post(new Runnable() {
                @Override
                public void run() {
                    mSwipe.setRefreshing(true);
                    // 发送请求
                    refreshData();
                }
            });
        }
        updateTheMatchParentViewHeight(getTheMatchParentView());
    }

    protected
    void refreshData(){};

    protected
    void showSwipe() {
        mSwipe.setRefreshing(true);
    }

    protected
    void refreshComplete() {
        if (supportNestedScrollView()) {
            mSwipe.setRefreshing(false);
        }
    }

    protected abstract
    int getChildContentViewId();

    protected abstract
    boolean isSearch();

    protected void setTitle(String title) {
        mTitleBar.setTitleString(title);
    }

    protected void setIcon(int iconRes) {
        mTitleBar.setIcon(iconRes);
    }

    protected void setIconBack(int iconRes) {
        mTitleBar.setIconBack(iconRes);
    }

    protected View.OnClickListener getIconClickListener() {
        return null;
    }

    protected View.OnClickListener getIconBackClickListener() {
        return null;
    }

    protected abstract
    boolean supportNestedScrollView();

    protected
    View getTheMatchParentView() {
        if (supportNestedScrollView()) {
            return mSwipe;
        }
        return null;
    }

    protected
    void updateTheMatchParentViewHeight(final View view) {
        if (view != null) {
            WindowManager wm = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            float d = getResources().getDisplayMetrics().density;
            // 48dp是ActionBar的最小高度
            int extHeight = UiUtil.getStatusBarHeight(this);
            int minH = (int) (d * 48 + extHeight);
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.height = size.y - minH;
            view.setLayoutParams(lp);
        }
    }
}
