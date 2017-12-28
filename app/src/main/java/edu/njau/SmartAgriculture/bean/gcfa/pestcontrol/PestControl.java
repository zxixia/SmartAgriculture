package edu.njau.SmartAgriculture.bean.gcfa.pestcontrol;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-16 10:38:31
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class PestControl implements Serializable {

    @SerializedName("Response")
    private edu.njau.SmartAgriculture.bean.gcfa.pestcontrol.Response response;

    @SerializedName("Result")
    private String result;
    public void setResponse(Response response) {
         this.response = response;
     }
     public Response getResponse() {
         return response;
     }

    public void setResult(String result) {
         this.result = result;
     }
     public String getResult() {
         return result;
     }

}