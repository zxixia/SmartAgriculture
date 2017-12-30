package edu.njau.SmartAgriculture.api;

import com.loopj.android.http.RequestParams;

/**
 * Created by lenovo on 2017/12/27.
 */

public class ApiBase {
    protected static final String IP_ADDRESS = "47.93.227.232:8086";
    protected static final String HEADER = "zhny//app";
    protected static final String getApi(String name) {
        return "http://" + IP_ADDRESS + "//" + HEADER + "//" + name;
    }

    protected static final String formatParam(String name, String value) {
        return String.format((name + "=%s"), value);
    }

    protected static final String formatApi(String api, String... params) {
        String str = api + "?";
        for (int i=0; i<params.length; i++) {
            if (i != 0) {
                str = str + "&";
            }
            str = str + params[i];
        }
        return str;
    }

    protected static final String getZpfaID(String value) {
        return formatParam("ZpfaID", value);
    }

    protected static final String getAreaID(String value) {
        return formatParam("AreaID", value);
    }

    protected static final String getCropID(String value) {
        return formatParam("CropID", value);
    }

    protected static final String getCropMonth(String value) {
        return formatParam("CropMonth", value);
    }

    protected static final String getMonthPeriod(String value) {
        return formatParam("MonthPeriod", value);
    }

    protected static final void getZpfaID(RequestParams params, String value) {
        params.put("ZpfaID", value);
    }

    protected static final void getAreaID(RequestParams params, String value) {
        params.put("AreaID", value);
    }

    protected static final void getCropID(RequestParams params, String value) {
        params.put("CropID", value);
    }

    protected static final void getCropPeriod(RequestParams params, String value) {
        params.put("CropPeriod", value);
    }

    protected static final void getCropMonth(RequestParams params, String value) {
        params.put("CropMonth", value);
    }

    protected static final void getMonthPeriod(RequestParams params, String value) {
        params.put("MonthPeriod", value);
    }

    protected static final void getTopic(RequestParams params, String value) {
        params.put("Topic", value);
    }

    protected static final void getMessageID(RequestParams params, String value) {
        params.put("MessageID", value);
    }
}
