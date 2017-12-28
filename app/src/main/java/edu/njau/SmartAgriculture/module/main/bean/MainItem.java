package edu.njau.SmartAgriculture.module.main.bean;

/**
 * Created by lenovo on 2017/12/9.
 */

public class MainItem {
    private String title;
    private int logo;
    public MainItem(String str, int l) {
        setTitle(str);
        setLogo(l);
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int l) {
        logo = l;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String str) {
        title = str;
    }


}
