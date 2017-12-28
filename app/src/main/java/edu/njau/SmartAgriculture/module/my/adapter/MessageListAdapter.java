package edu.njau.SmartAgriculture.module.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.adapter.BaseGeneralRecyclerAdapter;
import edu.njau.SmartAgriculture.base.adapter.BaseRecyclerAdapter;
import edu.njau.SmartAgriculture.bean.my.commoninfo.Mqtthistorylist;

/**
 * Created by lenovo on 2017/12/27.
 */

public class MessageListAdapter extends BaseGeneralRecyclerAdapter<Mqtthistorylist> {

    public MessageListAdapter(Callback callback) {
        // 没有头尾的列表样式
        super(callback, BaseRecyclerAdapter.NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MessageListViewHolder(mInflater.inflate(R.layout.item_list_my_message_list, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, Mqtthistorylist item, int position) {
        MessageListAdapter.MessageListViewHolder h = (MessageListAdapter.MessageListViewHolder) holder;
        h.author.setText(item.getTopic());
        h.createdTime.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(item.getPushtime())));
        h.content.setText(item.getMessage());
    }

    private static class MessageListViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView createdTime;
        TextView content;

        public MessageListViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.tv_topic);
            createdTime = (TextView) itemView.findViewById(R.id.tv_created_time);
            content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
