package edu.njau.SmartAgriculture.module.gcfa.mvp;

import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public class CommonTopInfoContract {
    public interface Presenter {
        /**
         * 根据月份数和旬数获取公共区域信息
         * @param zpfa
         * @param area
         * @param crop
         * @param month
         * @param monthPeriod
         */
        void getCommonTopInfoFromMonthAndMonthPeriod(String zpfa, String area, String crop, String month, String monthPeriod);
        void getSuitableCrop(final String zpfa, final String crop, final String area);
    }

    public interface View {
        void onGetCommonTopInfoFromMonthAndMonthPeriod(AllInfo allInfo);
        void onGetSuitableCrop(Variety variety);
    }
}
