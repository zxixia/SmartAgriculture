package edu.njau.SmartAgriculture.base.mvp.view;

import edu.njau.SmartAgriculture.base.mvp.presenter.BasePresenter;

/**
 * Created by haibin
 * on 2016/11/30.
 */

public interface BaseView<Presenter extends BasePresenter> {

    void setPresenter(Presenter presenter);

    void showNetworkError(int strId);
}
