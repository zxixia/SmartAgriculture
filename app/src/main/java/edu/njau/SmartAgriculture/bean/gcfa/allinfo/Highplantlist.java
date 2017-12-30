package edu.njau.SmartAgriculture.bean.gcfa.allinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-25 22:50:26
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Highplantlist implements Serializable {

    private int id;
    @SerializedName("cropPeriodDay")
    private String cropPeriodDay;
    @SerializedName("zpfaID")
    private String zpfaid;
    @SerializedName("zpfaName")
    private String zpfaname;
    @SerializedName("dateCreated")
    private long datecreated;
    @SerializedName("varietyYield")
    private String varietyyield;
    @SerializedName("cropPeriod")
    private String cropperiod;
    @SerializedName("farmingOperation")
    private Farmingoperation farmingoperation;
    @SerializedName("pestControl")
    private Pestcontrol pestcontrol;
    @SerializedName("cropID")
    private String cropid;
    @SerializedName("zpfaTypeID")
    private String zpfatypeid;
    @SerializedName("zpfaTypeName")
    private String zpfatypename;
    @SerializedName("areaID")
    private String areaid;
    @SerializedName("suitableCrop")
    private String suitablecrop;
    @SerializedName("leafAge")
    private String leafage;
    @SerializedName("stemGrowth")
    private String stemgrowth;
    @SerializedName("monthPeriod")
    private String monthperiod;
    @SerializedName("cropMonth")
    private String cropmonth;
    @SerializedName("solarTerms")
    private String solarterms;
    private String description;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setZpfaid(String zpfaid) {
         this.zpfaid = zpfaid;
     }
     public String getZpfaid() {
         return zpfaid;
     }

    public void setZpfaname(String zpfaname) {
         this.zpfaname = zpfaname;
     }
     public String getZpfaname() {
         return zpfaname;
     }

    public void setDatecreated(long datecreated) {
         this.datecreated = datecreated;
     }
     public long getDatecreated() {
         return datecreated;
     }

    public void setVarietyyield(String varietyyield) {
         this.varietyyield = varietyyield;
     }
     public String getVarietyyield() {
         return varietyyield;
     }

    public void setCropperiod(String cropperiod) {
         this.cropperiod = cropperiod;
     }
     public String getCropperiod() {
         return cropperiod;
     }

    public void setFarmingoperation(Farmingoperation farmingoperation) {
         this.farmingoperation = farmingoperation;
     }
     public Farmingoperation getFarmingoperation() {
         return farmingoperation;
     }

    public void setPestcontrol(Pestcontrol pestcontrol) {
         this.pestcontrol = pestcontrol;
     }
     public Pestcontrol getPestcontrol() {
         return pestcontrol;
     }

    public void setCropid(String cropid) {
         this.cropid = cropid;
     }
     public String getCropid() {
         return cropid;
     }

    public void setZpfatypeid(String zpfatypeid) {
         this.zpfatypeid = zpfatypeid;
     }
     public String getZpfatypeid() {
         return zpfatypeid;
     }

    public void setZpfatypename(String zpfatypename) {
         this.zpfatypename = zpfatypename;
     }
     public String getZpfatypename() {
         return zpfatypename;
     }

    public void setAreaid(String areaid) {
         this.areaid = areaid;
     }
     public String getAreaid() {
         return areaid;
     }

    public void setSuitablecrop(String suitablecrop) {
         this.suitablecrop = suitablecrop;
     }
     public String getSuitablecrop() {
         return suitablecrop;
     }

    public void setLeafage(String leafage) {
         this.leafage = leafage;
     }
     public String getLeafage() {
         return leafage;
     }

    public void setStemgrowth(String stemgrowth) {
         this.stemgrowth = stemgrowth;
     }
     public String getStemgrowth() {
         return stemgrowth;
     }

    public void setMonthperiod(String monthperiod) {
         this.monthperiod = monthperiod;
     }
     public String getMonthperiod() {
         return monthperiod;
     }

    public void setCropmonth(String cropmonth) {
         this.cropmonth = cropmonth;
     }
     public String getCropmonth() {
         return cropmonth;
     }

    public void setSolarterms(String solarterms) {
         this.solarterms = solarterms;
     }
     public String getSolarterms() {
         return solarterms;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public String getCropPeriodDay() {
        return cropPeriodDay;
    }

    public void setCropPeriodDay(String cropPeriodDay) {
        this.cropPeriodDay = cropPeriodDay;
    }
}