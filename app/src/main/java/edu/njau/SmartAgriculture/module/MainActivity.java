package edu.njau.SmartAgriculture.module;

import android.Manifest;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import net.jiawa.debughelper.XLog;
import net.jiawa.debughelper.flag.XFlag;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.activities.BaseTitleNormalActivity;
import edu.njau.SmartAgriculture.module.amap.AMapHelper;
import edu.njau.SmartAgriculture.module.gcfa.GCFAFragment;
import edu.njau.SmartAgriculture.module.main.MainFragment;
import edu.njau.SmartAgriculture.module.my.MyFragment;
import edu.njau.SmartAgriculture.module.navigationbar.NavigationBarFragment;
import edu.njau.SmartAgriculture.module.search.SearchActivity;
import edu.njau.SmartAgriculture.interf.OnItemSelectListener;
import edu.njau.SmartAgriculture.widgets.FragmentPagerAdapter;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseTitleNormalActivity implements View.OnClickListener,
        OnItemSelectListener, EasyPermissions.PermissionCallbacks {

    NavigationBarFragment mNavigationBarFragment;
    OnItemSelectListener mOnItemSelectListener;
    public static final int LOCATION_PERMISSION = 0x0100;//定位权限

    @Bind(R.id.activity_main)
    RelativeLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestLocationPermission();
        AMapHelper.newInstance(getApplicationContext()).setupAMap();
        XLog.setup(1, XLog.Flag(
                new XFlag(1, true, "DogFood"),
                new XFlag(2, false, "Animate"),
                new XFlag(3, false,  "Http"),
                new XFlag(5, true,  "Main")
        ));
    }

    // 定义在BaseActivity中的方法
    @Override
    protected void initData() {
        super.initData();
        setTitle("智慧农业");
        setIcon(R.mipmap.actionbar_search_icon);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        FragmentManager manager = getSupportFragmentManager();
        mNavigationBarFragment = (NavigationBarFragment) manager.findFragmentById(R.id.fag_navigationbar);
        mNavigationBarFragment.setup(this, manager, this);
        mOnItemSelectListener = mNavigationBarFragment;
        initViewPager();
    }

    @Override
    protected int getChildContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isSearch() {
        return false;
    }

    @Override
    protected View.OnClickListener getIconClickListener() {
        return this;
    }

    @Override
    protected boolean supportNestedScrollView() {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_icon) {
            startActivity(new Intent(this, SearchActivity.class));
        }
    }

    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private Fragment mCurFragment;
    private List<TabInfo> mTabs = new ArrayList<>();

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        AMapHelper.newInstance(getApplicationContext()).startLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        finish();
    }

    class TabInfo {
        Fragment fragment;
        String clazz;
        String name;
        TabInfo(Fragment f, String s, String n) {
            fragment = f;
            clazz = s;
            name = n;
        }
    }

    private void initViewPager() {

        mTabs.add(new TabInfo(
                Fragment.instantiate(this, MainFragment.class.getName(), null),
                MainFragment.class.getName(),
                getResources().getString(R.string.main_index_title)));
        mTabs.add(new TabInfo(
                Fragment.instantiate(this, GCFAFragment.class.getName(), null),
                GCFAFragment.class.getName(),
                getResources().getString(R.string.gcfn_index_title)));
        mTabs.add(new TabInfo(
                Fragment.instantiate(this, MyFragment.class.getName(), null),
                MyFragment.class.getName(),
                getResources().getString(R.string.my_index_title)));
        /*mTabs.add(new TabInfo(
                Fragment.instantiate(this, MainTabFragment.class.getName(), null),
                MainTabFragment.class.getName(),
                getResources().getString(R.string.kstw_index_title)));*/

        mViewPager.setAdapter(mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position).fragment; //SubFragment.newInstance(getContext(), tabs.get(position));
            }

            @Override
            public int getCount() {
                return mTabs.size(); /* 只有四个页面:首页,高产方案,快速提问,生产服务 */
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);
                if (mCurFragment == null) {
                    commitUpdate();
                }
                mCurFragment = (Fragment) object;
            }

            //this is called when notifyDataSetChanged() is called
            @Override
            public int getItemPosition(Object object) {
                return PagerAdapter.POSITION_NONE;
            }

        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mAdapter.commitUpdate();
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (null != mOnItemSelectListener) {
                    mOnItemSelectListener.onItemSelect(mTabs.get(position).clazz);
                }
                setTitle(mTabs.get(position).name);
            }
        });
    }

    @Override
    public void onItemSelect(String clazz) {
        for (int i=0; i<mTabs.size(); i++) {
            if (clazz.equals(mTabs.get(i).clazz)) {
                mViewPager.setCurrentItem(i, true);
                return;
            }
        }
    }

    /**
     * proxy request permission
     */
    @AfterPermissionGranted(LOCATION_PERMISSION)
    private void requestLocationPermission() {
        if (!EasyPermissions.hasPermissions(this,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE)) {
            EasyPermissions.requestPermissions(this, getString(R.string.need_lbs_permission_hint),
                    LOCATION_PERMISSION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE);
        }
    }

    @Override
    protected View getTheMatchParentView() {
        return mRootView;
    }
}
