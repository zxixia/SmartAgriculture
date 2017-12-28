package edu.njau.SmartAgriculture.bean.my.commoninfo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2017-12-27 20:22:23
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Response implements Serializable {

    @SerializedName("MqttHistoryList")
    private List<Mqtthistorylist> mqtthistorylist;
    public void setMqtthistorylist(List<Mqtthistorylist> mqtthistorylist) {
         this.mqtthistorylist = mqtthistorylist;
     }
     public List<Mqtthistorylist> getMqtthistorylist() {
         return mqtthistorylist;
     }

}