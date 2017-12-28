package edu.njau.SmartAgriculture.bean.gcfa.period;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-16 23:26:44
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Periodlist implements Serializable {

    @SerializedName("Period")
    private String period;
    public void setPeriod(String period) {
         this.period = period;
     }
     public String getPeriod() {
         return period;
     }

}