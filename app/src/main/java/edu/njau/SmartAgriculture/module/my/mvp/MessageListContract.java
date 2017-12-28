package edu.njau.SmartAgriculture.module.my.mvp;

import java.util.List;

import edu.njau.SmartAgriculture.base.mvp.presenter.BasePresenter;
import edu.njau.SmartAgriculture.base.mvp.view.BaseView;
import edu.njau.SmartAgriculture.bean.my.commoninfo.Mqtthistorylist;

/**
 * Created by lenovo on 2017/12/27.
 */

public class MessageListContract {
    public interface Presenter extends BasePresenter {
        void getCommonInfo(String zpfa);
    }

    public interface View extends BaseView<Presenter> {
        void onGetCommonInfo(List<Mqtthistorylist> list);
    }
}
