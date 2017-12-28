package edu.njau.SmartAgriculture.bean.gcfa.farmingoperation;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2017-12-16 22:23:50
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Response implements Serializable {

    @SerializedName("FarmingOpList")
    private List<Farmingoplist> farmingoplist;
    public void setFarmingoplist(List<Farmingoplist> farmingoplist) {
         this.farmingoplist = farmingoplist;
     }
     public List<Farmingoplist> getFarmingoplist() {
         return farmingoplist;
     }

}