package edu.njau.SmartAgriculture.module.my;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.RequestManager;

import net.jiawa.debughelper.XLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.activities.BaseTitleNormalActivity;
import edu.njau.SmartAgriculture.base.adapter.BaseGeneralRecyclerAdapter;
import edu.njau.SmartAgriculture.base.adapter.BaseRecyclerAdapter;
import edu.njau.SmartAgriculture.bean.my.commoninfo.Mqtthistorylist;
import edu.njau.SmartAgriculture.module.gcfa.PeriodActivity;
import edu.njau.SmartAgriculture.module.my.adapter.MessageListAdapter;
import edu.njau.SmartAgriculture.module.my.mvp.MessageListContract;
import edu.njau.SmartAgriculture.module.my.mvp.MessageListPresenter;

/**
 * Created by zhaoxin5 on 2017/12/25.
 */

public class MessageListActivity extends BaseTitleNormalActivity implements MessageListContract.View,
        BaseGeneralRecyclerAdapter.Callback, BaseRecyclerAdapter.OnItemClickListener {

    @Bind(R.id.rl_root)
    LinearLayout mRoot;
    @Bind(R.id.rv_my_message_list)
    RecyclerView mMessageListView;

    BaseRecyclerAdapter<Mqtthistorylist> mMessageListAdapter;
    MessageListContract.Presenter mPresenter;

    String mTopic;

    @Override
    protected int getChildContentViewId() {
        return R.layout.activity_messagelist;
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
    protected View getTheMatchParentView() {
        return mRoot;
    }

    protected RecyclerView.LayoutManager getLayoutManager(int orientation) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(orientation);
        return linearLayoutManager;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        new MessageListPresenter(this);
        mMessageListView.setLayoutManager(getLayoutManager(LinearLayoutManager.VERTICAL));

        Intent intent = getIntent();
        mTopic = intent.getStringExtra("Topic");
        XLog.d(true, 5, "mTopic: " + mTopic);

        setTitle("消息管理");
        setIconBack(R.mipmap.btn_back_normal);
    }

    @Override
    public void onGetCommonInfo(List<Mqtthistorylist> list) {
        refreshComplete();
        mMessageListAdapter.resetItem(list);
    }

    @Override
    public void setPresenter(MessageListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNetworkError(int strId) {}

    @Override
    protected void initData() {
        super.initData();
        mMessageListAdapter = new MessageListAdapter(this);
        mMessageListAdapter.setOnItemClickListener(this);
        mMessageListView.setAdapter(mMessageListAdapter);
    }

    @Override
    public RequestManager getImgLoader() {
        return getImageLoader();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Date getSystemTime() {
        return new Date();
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getCommonInfoByTopic(mTopic);
    }

    @Override
    protected View.OnClickListener getIconBackClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    @Override
    public void onItemClick(int position, long itemId) {
        Mqtthistorylist mqtthistorylist = mMessageListAdapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra("MessageId", mqtthistorylist.getMessageid());
        intent.setClass(this, MessageActivity.class);
        startActivity(intent);
    }
}
