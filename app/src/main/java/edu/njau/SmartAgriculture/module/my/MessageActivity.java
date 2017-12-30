package edu.njau.SmartAgriculture.module.my;

import android.content.Intent;
import android.widget.TextView;

import net.jiawa.debughelper.XLog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.activities.BaseTitleNormalActivity;
import edu.njau.SmartAgriculture.bean.my.message.Message;
import edu.njau.SmartAgriculture.module.my.mvp.MessageContract;
import edu.njau.SmartAgriculture.module.my.mvp.MessagePresenter;

/**
 * Created by lenovo on 2017/12/30.
 */

public class MessageActivity extends BaseTitleNormalActivity implements MessageContract.View {

    private String mMessageId = "1514458046937";
    private MessageContract.Presenter mPresenter;
    @Bind(R.id.tv_my_title)
    TextView mTitle;
    @Bind(R.id.tv_my_push_time)
    TextView mPushTime;
    @Bind(R.id.tv_my_content)
    TextView mContent;

    @Override
    protected int getChildContentViewId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected boolean isSearch() {
        return false;
    }

    @Override
    protected boolean supportNestedScrollView() {
        return true;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        new MessagePresenter(this);
        Intent intent = getIntent();
        mMessageId = intent.getStringExtra("MessageId");
        if (null != mMessageId) {
            XLog.d(true, 5, "mMessageId: " + mMessageId);
        }
        if (mMessageId == null) {
            mMessageId = "1514458046937";
        }
    }

    @Override
    public void onGetMessage(Message message) {
        refreshComplete();
        try {
            mTitle.setText(message.getResponse().getMqtthistory().getTopic());
            mPushTime.setText(new SimpleDateFormat("yyyy年MM月dd日 hh时mm分").format(
                    new Date(message.getResponse().getMqtthistory().getPushtime())));
            mContent.setText(message.getResponse().getMqtthistory().getMessage());
        } catch (Exception e) {}
    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNetworkError(int strId) {}

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getMessageById(mMessageId);
    }
}
