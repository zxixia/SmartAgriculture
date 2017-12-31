package edu.njau.SmartAgriculture.bean.gcfa.arealocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Lenovo on 2017/12/31.
 */

public class CropArea implements Serializable {
    @SerializedName("Response")
    private edu.njau.SmartAgriculture.bean.gcfa.arealocation.Response response;
    @SerializedName("Result")
    private String result;
    public void setResponse(edu.njau.SmartAgriculture.bean.gcfa.arealocation.Response response) {
        this.response = response;
    }
    public edu.njau.SmartAgriculture.bean.gcfa.arealocation.Response getResponse() {
        return response;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public String getResult() {
        return result;
    }
}
