package edu.njau.SmartAgriculture.bean.amap.weather;
import java.util.List;

/**
 * Auto-generated: 2017-12-17 21:12:17
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Weather {

    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<Lives> lives;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setCount(String count) {
         this.count = count;
     }
     public String getCount() {
         return count;
     }

    public void setInfo(String info) {
         this.info = info;
     }
     public String getInfo() {
         return info;
     }

    public void setInfocode(String infocode) {
         this.infocode = infocode;
     }
     public String getInfocode() {
         return infocode;
     }

    public void setLives(List<Lives> lives) {
         this.lives = lives;
     }
     public List<Lives> getLives() {
         return lives;
     }

}