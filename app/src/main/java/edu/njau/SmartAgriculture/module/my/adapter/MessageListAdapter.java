package edu.njau.SmartAgriculture.module.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        //h.author.setText(item.getTopic());
        String MESSAGESTR = item.getMessage();
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
        h.author.setText("高产栽培方案");
        String content_list = CropMonth+MonthPeriod;
        h.createdTime.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(item.getPushtime())));
        h.content.setText(content_list+ZpfaName+".......");
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
