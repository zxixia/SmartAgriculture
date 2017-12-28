package edu.njau.SmartAgriculture.bean.gcfa.allinfo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2017-12-25 22:50:26
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Response implements Serializable {

    @SerializedName("HighplantList")
    private List<Highplantlist> highplantlist;
    public void setHighplantlist(List<Highplantlist> highplantlist) {
         this.highplantlist = highplantlist;
     }
     public List<Highplantlist> getHighplantlist() {
         return highplantlist;
     }

}