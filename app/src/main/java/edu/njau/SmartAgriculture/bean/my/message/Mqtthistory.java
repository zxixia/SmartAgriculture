package edu.njau.SmartAgriculture.bean.my.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-30 19:54:29
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Mqtthistory implements Serializable {

    private int id;
    @SerializedName("messageID")
    private String messageid;
    private String topic;
    @SerializedName("zpfaID")
    private String zpfaid;
    @SerializedName("pushTime")
    private long pushtime;
    @SerializedName("dateCreated")
    private long datecreated;
    private String description;
    private String message;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setMessageid(String messageid) {
         this.messageid = messageid;
     }
     public String getMessageid() {
         return messageid;
     }

    public void setTopic(String topic) {
         this.topic = topic;
     }
     public String getTopic() {
         return topic;
     }

    public void setZpfaid(String zpfaid) {
         this.zpfaid = zpfaid;
     }
     public String getZpfaid() {
         return zpfaid;
     }

    public void setPushtime(long pushtime) {
         this.pushtime = pushtime;
     }
     public long getPushtime() {
         return pushtime;
     }

    public void setDatecreated(long datecreated) {
         this.datecreated = datecreated;
     }
     public long getDatecreated() {
         return datecreated;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

}