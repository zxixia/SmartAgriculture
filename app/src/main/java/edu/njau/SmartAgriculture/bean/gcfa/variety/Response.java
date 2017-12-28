package edu.njau.SmartAgriculture.bean.gcfa.variety;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-27 21:2:21
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Response implements Serializable {

    @SerializedName("VarietyYield")
    private String varietyyield;
    @SerializedName("SuitableCrop")
    private String suitablecrop;
    public void setVarietyyield(String varietyyield) {
         this.varietyyield = varietyyield;
     }
     public String getVarietyyield() {
         return varietyyield;
     }

    public void setSuitablecrop(String suitablecrop) {
         this.suitablecrop = suitablecrop;
     }
     public String getSuitablecrop() {
         return suitablecrop;
     }

}