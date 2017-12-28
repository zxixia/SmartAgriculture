package edu.njau.SmartAgriculture.bean.gcfa.pestcontrol;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2017-12-16 10:38:31
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Response implements Serializable {

    @SerializedName("PestControlList")
    private List<Pestcontrollist> pestcontrollist;
    public void setPestcontrollist(List<Pestcontrollist> pestcontrollist) {
         this.pestcontrollist = pestcontrollist;
     }
     public List<Pestcontrollist> getPestcontrollist() {
         return pestcontrollist;
     }

}