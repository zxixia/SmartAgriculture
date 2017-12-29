package edu.njau.SmartAgriculture.bean.gcfa.zpfa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lenovo on 2017/12/29.
 */

public class Response implements Serializable {

    @SerializedName("ZpfaList")
    private List<ZpfaList> ZpfaList;

    public List<edu.njau.SmartAgriculture.bean.gcfa.zpfa.ZpfaList> getZpfaList() {
        return ZpfaList;
    }

    public void setZpfaList(List<edu.njau.SmartAgriculture.bean.gcfa.zpfa.ZpfaList> zpfaList) {
        ZpfaList = zpfaList;
    }
}
