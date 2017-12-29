package edu.njau.SmartAgriculture.bean.gcfa.zpfa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Lenovo on 2017/12/29.
 */

public class ZpfaList implements Serializable {

    @SerializedName("ZpfaName")
    private String ZpfaName;
    @SerializedName("ZpfaID")
    private String ZpfaID;

    public String getZpfaName() {
        return ZpfaName;
    }

    public void setZpfaName(String zpfaName) {
        ZpfaName = zpfaName;
    }

    public String getZpfaID() {
        return ZpfaID;
    }

    public void setZpfaID(String zpfaID) {
        ZpfaID = zpfaID;
    }
}
