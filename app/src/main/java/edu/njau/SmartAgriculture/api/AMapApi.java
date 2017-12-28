package edu.njau.SmartAgriculture.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * Created by lenovo on 2017/12/17.
 */

public class AMapApi {
    private static AsyncHttpClient mClient = new AsyncHttpClient();
    private static final String KEY = "56839a7783183776ff292a422a3d8847";

    /**
     * http://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=<用户key>
     * @return
     */
    private static final String getApi() {
        return "http://restapi.amap.com/v3/weather/weatherInfo";
    }

    private static void addKey(RequestParams params) {
        params.put("key", KEY);
    }

    private static final void getCity(RequestParams params, String value) {
        params.put("city", value);
    }

    public static void getWeather(String adCode, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        addKey(params);
        getCity(params, adCode);
        final String getStr = getApi();

        mClient.get(getStr, params, handler);
    }
}
