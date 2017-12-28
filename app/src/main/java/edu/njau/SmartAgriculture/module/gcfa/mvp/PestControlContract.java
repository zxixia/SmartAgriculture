package edu.njau.SmartAgriculture.module.gcfa.mvp;

import edu.njau.SmartAgriculture.base.mvp.presenter.BasePresenter;
import edu.njau.SmartAgriculture.base.mvp.view.BaseView;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;

/**
 * Created by zhaoxin5 on 2017/12/26.
 */

public class PestControlContract {
    public interface Presenter extends BasePresenter, CommonTopInfoContract.Presenter {
        void getAllInfoFromMonthAndMonthPeriod(final String zpfa, final String crop, final String area, final String month, final String monthPeriod);
    }

    public interface View extends BaseView<Presenter>, CommonTopInfoContract.View {
        void onGetAllInfoFromMonthAndMonthPeriod(AllInfo allInfo);
    }
}
