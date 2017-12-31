package edu.njau.SmartAgriculture.bean.gcfa.arealocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import edu.njau.SmartAgriculture.bean.gcfa.pestcontrol.Pestcontrollist;

/**
 * Created by Lenovo on 2017/12/31.
 */

public class Response implements Serializable {


    @SerializedName("CropArea")
    private AreaLocation areaLocation;

    public AreaLocation getAreaLocation() {
        return areaLocation;
    }

    public void setAreaLocation(AreaLocation areaLocation) {
        this.areaLocation = areaLocation;
    }
}
