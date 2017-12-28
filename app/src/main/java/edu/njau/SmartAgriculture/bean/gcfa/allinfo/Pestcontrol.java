package edu.njau.SmartAgriculture.bean.gcfa.allinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-25 22:50:26
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Pestcontrol implements Serializable {

    @SerializedName("dateCreated")
    private long datecreated;
    @SerializedName("pestControlID")
    private int pestcontrolid;
    @SerializedName("pestControlType")
    private String pestcontroltype;
    @SerializedName("pestControlName")
    private String pestcontrolname;
    @SerializedName("pestControlContent")
    private String pestcontrolcontent;
    private String description;
    public void setDatecreated(long datecreated) {
         this.datecreated = datecreated;
     }
    public long getDatecreated() {
         return datecreated;
     }

    public void setPestcontrolid(int pestcontrolid) {
         this.pestcontrolid = pestcontrolid;
     }
     public int getPestcontrolid() {
         return pestcontrolid;
     }

    public void setPestcontroltype(String pestcontroltype) {
         this.pestcontroltype = pestcontroltype;
     }
     public String getPestcontroltype() {
         return pestcontroltype;
     }

    public void setPestcontrolname(String pestcontrolname) {
         this.pestcontrolname = pestcontrolname;
     }
     public String getPestcontrolname() {
         return pestcontrolname;
     }

    public void setPestcontrolcontent(String pestcontrolcontent) {
         this.pestcontrolcontent = pestcontrolcontent;
     }
     public String getPestcontrolcontent() {
         return pestcontrolcontent;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

}