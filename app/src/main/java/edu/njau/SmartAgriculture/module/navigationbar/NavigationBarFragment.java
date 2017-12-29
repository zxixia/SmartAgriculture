package edu.njau.SmartAgriculture.module.navigationbar;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import net.jiawa.debughelper.XLog;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.fragments.BaseFragment;
import edu.njau.SmartAgriculture.helper.AnimatorListenerHelper;
import edu.njau.SmartAgriculture.module.gcfa.GCFAFragment;
import edu.njau.SmartAgriculture.module.main.MainFragment;
import edu.njau.SmartAgriculture.module.main.MainTabFragment;
import edu.njau.SmartAgriculture.interf.OnItemSelectListener;
import edu.njau.SmartAgriculture.module.my.MyFragment;
import edu.njau.SmartAgriculture.widgets.NavigationButton;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xixia on 2017/3/22.
 */

public class NavigationBarFragment extends BaseFragment implements View.OnClickListener, OnItemSelectListener {

    @Bind(R.id.tv_navigationbar_item_index)
    public NavigationButton mItemIndex;
    @Bind(R.id.tv_navigationbar_item_gcfa)
    public NavigationButton mItemGCFA;
    @Bind(R.id.tv_navigationbar_item_kstw)
    public NavigationButton mItemKSTW;
    @Bind(R.id.tv_navigationbar_item_scfw)
    public NavigationButton mItemSCFW;
    @Bind(R.id.tv_navigationbar_item_me)
    public NavigationButton mItemME;

    private NavigationButton mCurrentNavButton = null;
    private FragmentManager mFragmentManager;
    private Context mContext;
    // 这个是MainActivity的布局中居中的那个布局,
    // 用于存放MainTabFragment这样的fragment
    // private int mContainerId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigationbar;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        XLog.d(false, 1);

        mItemIndex.init(R.drawable.tab_icon_index,  // 首页
                MainFragment.class);
        //mItemIndex.setFragment(MainFragment.newInstance());
        mItemGCFA.init(R.drawable.tab_icon_gcfa, // 高产方案
                GCFAFragment.class);
        //mItemGCFA.setFragment(GCFAFragment.newInstance());
        mItemKSTW.init(R.drawable.tab_icon_kstw, // 快速提问
                MainTabFragment.class);
        //mItemKSTW.setFragment(MainTabFragment.newInstance());
        mItemSCFW.init(R.drawable.tab_icon_scfw, // 生产服务
                MyFragment.class);
        //mItemSCFW.setFragment(MainTabFragment.newInstance());
        mItemME.init(R.drawable.tab_icon_me, // 我的
                MainTabFragment.class);
    }

    public void setup(Context context, FragmentManager manager, OnItemSelectListener l) {
        mContext = context;
        mFragmentManager = manager;
        // mContainerId = containerId;
        mOnItemSelectListener = l;

        clearOldFragment();
        doSelect(mItemIndex);
    }

    /**
     * Use butterknife to bind click events
     * @param v
     */
    @OnClick({R.id.tv_navigationbar_item_index, R.id.tv_navigationbar_item_gcfa,
            R.id.tv_navigationbar_item_kstw, R.id.tv_navigationbar_item_scfw,
            R.id.tv_navigationbar_item_me})
    @Override
    public void onClick(View v) {
        if (v instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) v;
            doSelect(nav);
        }
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {

        XLog.d(true, 1);
        /*FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();*/
    }

    private void doSelect(NavigationButton newNavButton) {

        if (null != mAnimatorSet && mAnimatorSet.isRunning()) {
            XLog.d(false, 2);
            return;
        }
        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        // doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
        if (null != mOnItemSelectListener) {
            mOnItemSelectListener.onItemSelect(mCurrentNavButton.getTag());
        }
        doSelectAnimate();
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        /*FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();*/
    }

    private AnimatorSet mAnimatorSet = new AnimatorSet();
    private void doSelectAnimate() {
        if (null == mCurrentNavButton) return;
        /**
         * 每次都需要new一个AnimatorSet,否则动画会有卡顿
         */
        mAnimatorSet = new AnimatorSet();
        // mAnimatorSet.addListener(mAnimatorListenerHelper);
        mAnimatorSet.play(getNavigationItemClickAnimator(0, mCurrentNavButton, 100));
        mAnimatorSet.start();
    }

    ValueAnimator getNavigationItemClickAnimator(int distance, final View view, final float animateMove) {
        if (distance < 0) return null;
        final ValueAnimator anim = new ValueAnimator();
        // 这样能生成一条从0到1,然后在从1到0的序列
        // 然后在onAnimationUpdate函数中执行Y轴方向的
        // 变化,即可完成动画
        anim.setFloatValues(0.0f, 1.0f, 0.0f);
        anim.setDuration(500);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setStartDelay(distance * 100);

        final float oriY = view.getY();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                view.setY(oriY + value * animateMove);
            }
        });

        anim.addListener(new AnimatorListenerHelper() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                XLog.d(false, 1, "onAnimationEnd");
                view.setY(oriY);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                view.setY(oriY);
            }
        });
        return anim;
    }

    OnItemSelectListener mOnItemSelectListener = null;

    @Override
    public void onItemSelect(String clazz) {
        if (clazz.equals(mItemIndex.getTag())) {
            doSelect(mItemIndex);
        }
        if (clazz.equals(mItemGCFA.getTag())) {
            doSelect(mItemGCFA);
        }
        if (clazz.equals(mItemME.getTag())) {
            doSelect(mItemME);
        }
    }
}
