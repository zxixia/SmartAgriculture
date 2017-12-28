package edu.njau.SmartAgriculture.bean.gcfa.farmingoperation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-16 22:23:50
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Farmingoplist implements Serializable {

    @SerializedName("FarmingOperationID")
    private String farmingoperationid;
    @SerializedName("Description")
    private String description;
    @SerializedName("FarmingOperationType")
    private String farmingoperationtype;
    @SerializedName("FarmingOperationContent")
    private String farmingoperationcontent;
    @SerializedName("FarmingOperationName")
    private String farmingoperationname;
    public void setFarmingoperationid(String farmingoperationid) {
         this.farmingoperationid = farmingoperationid;
     }
     public String getFarmingoperationid() {
         return farmingoperationid;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
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

    public void setFarmingoperationname(String farmingoperationname) {
         this.farmingoperationname = farmingoperationname;
     }
     public String getFarmingoperationname() {
         return farmingoperationname;
     }

}