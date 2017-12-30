package edu.njau.SmartAgriculture.module.my;

import android.content.Intent;
import android.util.Log;
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
    private String mMessageC = "";
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
        setTitle("信息详情");
        new MessagePresenter(this);
        Intent intent = getIntent();
        mMessageId = intent.getStringExtra("MessageId");
        mMessageC = intent.getStringExtra("message_str");
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

//            String MESSAGESTR = message.getResponse().getMqtthistory().getMessage();
//            String MqMessageId = MESSAGESTR.split("#")[0];
//            String CropMonth= MESSAGESTR.split("#")[1];
//            Log.e("CropMonth.......",""+CropMonth);
//            String MonthPeriod = MESSAGESTR.split("#")[2];
//            String ZpfaID = MESSAGESTR.split("#")[3];
//            String ZpfaName = MESSAGESTR.split("#")[4];
//            String LeafAge= MESSAGESTR.split("#")[5];
//            String CropPeriod = MESSAGESTR.split("#")[6];
//            String FarmingOperationName = MESSAGESTR.split("#")[7];
//            String FarmingOperationContent = MESSAGESTR.split("#")[8];
//            String PestControlName= MESSAGESTR.split("#")[9];
//            String PestControlContent = MESSAGESTR.split("#")[10];


            mTitle.setText("收到通知");
            mPushTime.setText(new SimpleDateFormat("yyyy年MM月dd日 hh时mm分").format(
                    new Date(message.getResponse().getMqtthistory().getPushtime())));
            mContent.setText(mMessageC);
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
