package edu.njau.SmartAgriculture.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import net.jiawa.debughelper.XLog;

import java.net.URLEncoder;

/**
 * Created by lenovo on 2017/12/16.
 */

public class GCFAApi extends ApiBase {
    private static AsyncHttpClient mClient = new AsyncHttpClient();
    public static final String PestControl = "pestControl.do";
    public static final String Period = "period.do";
    public static final String VarietyYield = "varietyYield.do";
    public static final String FarmingOperation = "farmingOperation.do";
    public static final String FindHighplantByCropPeriod = "findHighplantByCropPeriod.do";
    public static final String FindZpfaList = "zpfaList.do";

    public static final String FindHighplantByMonthPeriod = "findHighplantByMonthPeriod.do";
    /**
     * http://47.93.227.232:8086/zhny/app/pestControl.do?ZpfaID=00000000001&AreaID=0000000001&CropID=0
     * @param handler
     */
    public static void getPestControl(String zpfa, String area, String crop, TextHttpResponseHandler handler) {

        RequestParams params = new RequestParams();
        getZpfaID(params, zpfa);
        getCropID(params, crop);
        getAreaID(params, area);

        final String getStr = getApi(PestControl);
        XLog.d(true, 5, getStr);
        mClient.get(getStr, params, handler);
    }

    /**
     * http://47.93.227.232:8086/zhny/app/period.do?ZpfaID=00000000001&AreaID=0000000001&CropID=0
     * @param zpfa
     * @param area
     * @param crop
     * @param handler
     */
    public static void getPeriod(String zpfa, String area, String crop, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        getZpfaID(params, zpfa);
        getCropID(params, crop);
        getAreaID(params, area);

        final String getStr = getApi(Period);
        XLog.d(true, 5, getStr);
        mClient.get(getStr, params, handler);
    }

    /**
     * http://47.93.227.232:8086/zhny/app/varietyYield.do?ZpfaID=00000000001&AreaID=0000000001&CropID=0
     * @param zpfa
     * @param area
     * @param crop
     * @param handler
     */
    public static void getVarietyYield(String zpfa, String area, String crop, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        getZpfaID(params, zpfa);
        getCropID(params, crop);
        getAreaID(params, area);

        final String getStr = getApi(VarietyYield);
        XLog.d(true, 5, getStr);
        mClient.get(getStr, params, handler);
    }

    public static void getFarmingOperation(String zpfa, String area, String crop, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        getZpfaID(params, zpfa);
        getCropID(params, crop);
        getAreaID(params, area);

        final String getStr = getApi(FarmingOperation);
        XLog.d(true, 5, getStr);
        mClient.get(getStr, params, handler);
    }

    public static void getAllInfoFromPeriod(String zpfa, String area, String crop, String period, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        getZpfaID(params, zpfa);
        getCropID(params, crop);
        getAreaID(params, area);
        getCropPeriod(params, period);

        final String getStr = getApi(FindHighplantByCropPeriod);
        XLog.d(true, 5, getStr);
        mClient.get(getStr, params, handler);
    }

    public static void getAllInfoFromMonthAndMonthPeriod(String zpfa, String area, String crop, String month, String monthPeriod, TextHttpResponseHandler handler) {
        try {
            final String getStr = getApi(FindHighplantByMonthPeriod);
            RequestParams params = new RequestParams();
            getZpfaID(params, zpfa);
            getCropID(params, crop);
            getAreaID(params, area);
            getCropMonth(params, month);
            getMonthPeriod(params, monthPeriod);
            mClient.get(getStr, params, handler);
        } catch (Exception e){}
    }

    /**
     * http://47.93.227.232:8086//zhny/app/zpfaList.do?AreaID=0000000001&CropID=0
     */
    public static void getZpfaList(String area,String crop,TextHttpResponseHandler handler){
        try{

            RequestParams params = new RequestParams();
            getCropID(params, crop);
            getAreaID(params, area);

            final String getStr = getApi(FindZpfaList);
            XLog.d(true, 5, getStr);
            mClient.get(getStr, params, handler);
        }catch (Exception e){e.printStackTrace();}
    }
}
