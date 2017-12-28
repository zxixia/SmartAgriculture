package edu.njau.SmartAgriculture.bean.gcfa.pestcontrol;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-16 10:38:31
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Pestcontrollist implements Serializable {

    @SerializedName("PestControlID")
    private String pestcontrolid;
    @SerializedName("Description")
    private String description;
    @SerializedName("PestControlName")
    private String pestcontrolname;
    @SerializedName("PestControlType")
    private String pestcontroltype;
    @SerializedName("PestControlContent")
    private String pestcontrolcontent;
    public void setPestcontrolid(String pestcontrolid) {
         this.pestcontrolid = pestcontrolid;
     }
     public String getPestcontrolid() {
         return pestcontrolid;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setPestcontrolname(String pestcontrolname) {
         this.pestcontrolname = pestcontrolname;
     }
     public String getPestcontrolname() {
         return pestcontrolname;
     }

    public void setPestcontroltype(String pestcontroltype) {
         this.pestcontroltype = pestcontroltype;
     }
     public String getPestcontroltype() {
         return pestcontroltype;
     }

    public void setPestcontrolcontent(String pestcontrolcontent) {
         this.pestcontrolcontent = pestcontrolcontent;
     }
     public String getPestcontrolcontent() {
         return pestcontrolcontent;
     }

}