package edu.njau.SmartAgriculture.bean.gcfa.farmingoperation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-16 22:23:50
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class FarmingOperation implements Serializable {

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