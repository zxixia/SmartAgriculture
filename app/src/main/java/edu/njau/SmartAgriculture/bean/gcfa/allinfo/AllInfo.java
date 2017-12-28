package edu.njau.SmartAgriculture.bean.gcfa.allinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-25 22:50:26
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class AllInfo implements Serializable {

    @SerializedName("Response")
    private Response response;
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