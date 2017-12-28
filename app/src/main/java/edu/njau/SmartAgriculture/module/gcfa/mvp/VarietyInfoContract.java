package edu.njau.SmartAgriculture.module.gcfa.mvp;

import edu.njau.SmartAgriculture.base.mvp.presenter.BasePresenter;
import edu.njau.SmartAgriculture.base.mvp.view.BaseView;
import edu.njau.SmartAgriculture.bean.amap.weather.Weather;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.period.Period;
import edu.njau.SmartAgriculture.bean.gcfa.variety.Variety;

/**
 * Created by lenovo on 2017/12/16.
 */

public class VarietyInfoContract {

    public static int TYPE_PEST_CONTROL        = -8000;
    public static int TYPE_FARMINGOPERATION    = TYPE_PEST_CONTROL + 1;
    public static int TYPE_PERIOD              = TYPE_FARMINGOPERATION + 1;
    public static int TYPE_VARIETY             = TYPE_PERIOD + 1;

    public interface Presenter extends BasePresenter {
        void getVriety(final String zpfa, final String crop, final String area);
    }

    public interface View extends BaseView<Presenter> {
        void onGetVriety(final Variety variety);
    }
}
