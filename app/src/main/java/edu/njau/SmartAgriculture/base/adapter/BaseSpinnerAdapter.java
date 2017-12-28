package edu.njau.SmartAgriculture.base.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.jiawa.debughelper.XLog;

import java.util.List;

import edu.njau.SmartAgriculture.R;

/**
 * Created by lenovo on 2017/12/16.
 */

public class BaseSpinnerAdapter extends BaseAdapter {

    private final List<interf> items;

    private final Context context;

    private int selectIndex = 0;

    public void setSelectIndex(int index) {
        this.selectIndex = index;
    }

    public BaseSpinnerAdapter(Context context, List<interf> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.spinner_layout_head, null);
        }
        ((TextView) convertView).setText(getItem(position).getShowText());
        XLog.d(false, 5, "enter");
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.list_cell_team, null, false);
        }
        String name = getItem(position).getShowText();
        TextView tv = (TextView) convertView.findViewById(R.id.tv_name);
        if (name != null) {
            tv.setText(name);
        }
        if (selectIndex != position) {
            tv.setTextColor(Color.parseColor("#acd4b3"));
        } else {
            tv.setTextColor(Color.parseColor("#6baf77"));
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public interf getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public interface interf {
        public String getShowText();
    }
}
