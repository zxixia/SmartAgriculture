package edu.njau.SmartAgriculture.bean.gcfa.arealocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import edu.njau.SmartAgriculture.bean.gcfa.variety.*;

/**
 * Created by Lenovo on 2017/12/31.
 */

public class AreaLocation implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("areaID")
    private String areaID;
    @SerializedName("dateCreated")
    private String dateCreated;
    @SerializedName("regionID")
    private String regionID;
    @SerializedName("areaName")
    private String areaName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }



}
