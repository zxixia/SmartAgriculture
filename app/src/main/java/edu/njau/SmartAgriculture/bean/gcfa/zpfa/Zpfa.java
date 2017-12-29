package edu.njau.SmartAgriculture.bean.gcfa.zpfa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import edu.njau.SmartAgriculture.bean.gcfa.pestcontrol.*;

/**
 * Created by Lenovo on 2017/12/29.
 */

public class Zpfa implements Serializable {

    @SerializedName("Response")
    private edu.njau.SmartAgriculture.bean.gcfa.zpfa.Response response;

    @SerializedName("Result")
    private String result;
    public void setResponse(edu.njau.SmartAgriculture.bean.gcfa.zpfa.Response response) {
        this.response = response;
    }
    public edu.njau.SmartAgriculture.bean.gcfa.zpfa.Response getResponse() {
        return response;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public String getResult() {
        return result;
    }


}
