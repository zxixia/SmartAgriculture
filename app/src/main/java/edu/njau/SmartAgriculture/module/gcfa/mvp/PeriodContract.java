package edu.njau.SmartAgriculture.module.gcfa.mvp;

import edu.njau.SmartAgriculture.base.mvp.presenter.BasePresenter;
import edu.njau.SmartAgriculture.base.mvp.view.BaseView;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.period.Period;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public class PeriodContract {
    public interface Presenter extends BasePresenter, CommonTopInfoContract.Presenter {
        void getPeriod(String zpfa, String area, String crop);
        void getAllInfoFromPeriod(final String zpfa, final String crop, final String area, final String period);
    }

    public interface View extends BaseView<Presenter>, CommonTopInfoContract.View {
        void onGetPeriod(Period period);
        void onGetAllInfoFromPeriod(AllInfo allInfo);
    }
}
