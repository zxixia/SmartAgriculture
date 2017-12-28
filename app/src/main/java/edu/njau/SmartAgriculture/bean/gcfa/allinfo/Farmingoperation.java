package edu.njau.SmartAgriculture.bean.gcfa.allinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-25 22:50:26
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Farmingoperation implements Serializable {

    @SerializedName("dateCreated")
    private long datecreated;
    @SerializedName("farmingOperationID")
    private int farmingoperationid;
    @SerializedName("farmingOperationName")
    private String farmingoperationname;
    @SerializedName("farmingOperationType")
    private String farmingoperationtype;
    @SerializedName("farmingOperationContent")
    private String farmingoperationcontent;
    private String description;
    public void setDatecreated(long datecreated) {
         this.datecreated = datecreated;
     }
    public long getDatecreated() {
         return datecreated;
     }

    public void setFarmingoperationid(int farmingoperationid) {
         this.farmingoperationid = farmingoperationid;
     }
     public int getFarmingoperationid() {
         return farmingoperationid;
     }

    public void setFarmingoperationname(String farmingoperationname) {
         this.farmingoperationname = farmingoperationname;
     }
     public String getFarmingoperationname() {
         return farmingoperationname;
     }

    public void setFarmingoperationtype(String farmingoperationtype) {
         this.farmingoperationtype = farmingoperationtype;
     }
     public String getFarmingoperationtype() {
         return farmingoperationtype;
     }

    public void setFarmingoperationcontent(String farmingoperationcontent) {
         this.farmingoperationcontent = farmingoperationcontent;
     }
     public String getFarmingoperationcontent() {
         return farmingoperationcontent;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

}