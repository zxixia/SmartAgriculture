package edu.njau.SmartAgriculture.module.my;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
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
    private SharedPreferences zhny;

    @Bind(R.id.tv_my_title)
    TextView mTitle;
    @Bind(R.id.tv_my_push_time)
    TextView mPushTime;
    @Bind(R.id.tv_my_time_name)
    TextView mtime_name;
    @Bind(R.id.tv_my_time_content)
    TextView mtime_content;

    @Bind(R.id.tv_my_period_name)
    TextView mperiod_name;
    @Bind(R.id.tv_my_period_content)
    TextView mperiod_content;

    @Bind(R.id.tv_my_zjyl_name)
    TextView mzjyl_name;
    @Bind(R.id.tv_my_zjyl_content)
    TextView mzjyl_content;

    @Bind(R.id.tv_my_xzdj_name)
    TextView mxzdj_name;
    @Bind(R.id.tv_my_xzdj_content)
    TextView mxzdj_content;

    @Bind(R.id.tv_my_op_name)
    TextView mop_name;
    @Bind(R.id.tv_my_op_content)
    TextView mop_content;

    @Bind(R.id.tv_my_pc_name)
    TextView mpc_name;
    @Bind(R.id.tv_my_pc_content)
    TextView mpc_content;

    @Bind(R.id.tv_my_zpfa_name)
    TextView mzpfa_name;


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
        zhny = getSharedPreferences("zhny", MODE_PRIVATE);



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
            String XZDT = "";
            String MESSAGESTR = message.getResponse().getMqtthistory().getMessage();
            String MqMessageId = MESSAGESTR.split("#")[0];
            String CropMonth= MESSAGESTR.split("#")[1];
            Log.e("CropMonth.......",""+CropMonth);
            String MonthPeriod = MESSAGESTR.split("#")[2];
            String ZpfaID = MESSAGESTR.split("#")[3];
            String ZpfaName = MESSAGESTR.split("#")[4];
            String LeafAge= MESSAGESTR.split("#")[5];
            String CropPeriod = MESSAGESTR.split("#")[6];
            String FarmingOperationName = MESSAGESTR.split("#")[7];
            String FarmingOperationContent = MESSAGESTR.split("#")[8];
            String PestControlName= MESSAGESTR.split("#")[9];
            String PestControlContent = MESSAGESTR.split("#")[10];
            try{
               if(MESSAGESTR.split("#").length>11){
                   XZDT = MESSAGESTR.split("#")[11];
               }
            }catch (Exception e){
                e.printStackTrace();
            }

            String showTxt = "";
            mzpfa_name.setText(ZpfaName);
            mtime_name.setVisibility(View.VISIBLE);
            mtime_content.setVisibility(View.VISIBLE);
            mtime_content.setText(CropMonth+MonthPeriod);

            //生育期
            if(zhny.getBoolean("mPD",false)) {
                mperiod_name.setVisibility(View.VISIBLE);
                mperiod_content.setVisibility(View.VISIBLE);
                mperiod_content.setText(CropPeriod);
            }else {
                mperiod_name.setVisibility(View.GONE);
                mperiod_content.setVisibility(View.GONE);
            }
            //主茎叶龄
            if(zhny.getBoolean("mLF",false)) {
                mzjyl_name.setVisibility(View.VISIBLE);
                mzjyl_content.setVisibility(View.VISIBLE);
                mzjyl_content.setText(LeafAge);
            }else{
                mzjyl_name.setVisibility(View.GONE);
                mzjyl_content.setVisibility(View.GONE);
            }
            //消长
            if(zhny.getBoolean("mSG",false)) {
                mxzdj_name.setVisibility(View.VISIBLE);
                mxzdj_content.setVisibility(View.VISIBLE);
                mxzdj_content.setText(XZDT);
            }else{
                mxzdj_name.setVisibility(View.GONE);
                mxzdj_content.setVisibility(View.GONE);
            }
            //农事操作
            if(zhny.getBoolean("mOP",false)) {
                mop_name.setVisibility(View.VISIBLE);
                mop_content.setVisibility(View.VISIBLE);
                mop_content.setText(FarmingOperationName+" "+FarmingOperationContent);
            }else{
                mop_name.setVisibility(View.GONE);
                mop_content.setVisibility(View.GONE);
            }
            //病虫害
            if(zhny.getBoolean("mPC",false)) {
                mpc_name.setVisibility(View.VISIBLE);
                mpc_content.setVisibility(View.VISIBLE);
                mpc_content.setText(PestControlName+" "+PestControlContent);
            }else {
                mpc_name.setVisibility(View.GONE);
                mpc_content.setVisibility(View.GONE);
            }


            mTitle.setText("收到通知");
            mPushTime.setText(new SimpleDateFormat("yyyy年MM月dd日 hh时mm分").format(
                    new Date(message.getResponse().getMqtthistory().getPushtime())));
            //mContent.setText(mMessageC);
            //mContent.setText(showTxt);
        } catch (Exception e) {e.printStackTrace();}
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
        Log.e("mMessageId......",mMessageId+"");
        mPresenter.getMessageById(mMessageId);
    }
}
