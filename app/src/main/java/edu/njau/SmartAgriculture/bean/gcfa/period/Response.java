package edu.njau.SmartAgriculture.bean.gcfa.period;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2017-12-16 23:26:44
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Response implements Serializable {

    @SerializedName("PeriodList")
    private List<Periodlist> periodlist;
    public void setPeriodlist(List<Periodlist> periodlist) {
         this.periodlist = periodlist;
     }
     public List<Periodlist> getPeriodlist() {
         return periodlist;
     }

}