package edu.njau.SmartAgriculture.module.my.mvp;

import edu.njau.SmartAgriculture.base.mvp.presenter.BasePresenter;
import edu.njau.SmartAgriculture.base.mvp.view.BaseView;
import edu.njau.SmartAgriculture.bean.my.message.Message;

/**
 * Created by lenovo on 2017/12/30.
 */

public class MessageContract {
    public interface Presenter extends BasePresenter {
        void getMessageById(String id);
    }

    public interface View extends BaseView<Presenter> {
        void onGetMessage(Message message);
    }
}
