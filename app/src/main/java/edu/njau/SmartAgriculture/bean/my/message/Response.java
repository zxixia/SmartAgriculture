package edu.njau.SmartAgriculture.bean.my.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-30 19:54:29
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Response implements Serializable {

    @SerializedName("MqttHistory")
    private Mqtthistory mqtthistory;
    public void setMqtthistory(Mqtthistory mqtthistory) {
         this.mqtthistory = mqtthistory;
     }
     public Mqtthistory getMqtthistory() {
         return mqtthistory;
     }

}