package edu.njau.SmartAgriculture.module.amap;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import net.jiawa.debughelper.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/17.
 */

public class AMapHelper {
    private static AMapHelper mInstance = null;
    private Context mContext;
    private AMapLocation mLastLocation;
    private AMapHelper(Context context) {
        mContext = context;
    }
    public static AMapHelper newInstance(Context context) {
        if (null == mInstance) {
            mInstance = new AMapHelper(context);
        }
        return mInstance;
    }

    public static AMapHelper getInstance() {
        return mInstance;
    }

    /*
    * 高德地图相关
    * */
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            XLog.d(true, 5, aMapLocation.toString());
            mLastLocation = aMapLocation;
            if (null != mListeners && mListeners.size() > 0) {
                for (int i=0; i<mListeners.size(); i++) {
                    mListeners.get(i).onGetLocation(aMapLocation);
                }
            }
        }
    };
    public void setupAMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(10000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    public void startLocation() {
        mLocationClient.startLocation();
    }

    public interface LocationListener {
        void onGetLocation(AMapLocation aMapLocation);
    }

    List<LocationListener> mListeners = new ArrayList<LocationListener>();
    public void addLocationListener(LocationListener l) {
        mListeners.add(l);
    }

    public void removeLocationListener(LocationListener l) {
        mListeners.remove(l);
    }

    public AMapLocation getLastLocation() {
        return mLastLocation;
    }
}
