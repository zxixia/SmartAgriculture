package edu.njau.SmartAgriculture.module.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.adapter.BaseRecyclerAdapter;
import edu.njau.SmartAgriculture.module.main.bean.MainItem;

/**
 * Created by lenovo on 2017/12/9.
 */

public class MainAdapter extends BaseRecyclerAdapter<MainItem> {

    public MainAdapter(Context context) {
        super(context, NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MainViewHolder(mInflater.inflate(R.layout.item_grid_main_item, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, MainItem item, int position) {
        MainViewHolder h = (MainViewHolder) holder;
        h.mTextName.setText(item.getTitle());
        h.mImageView.setBackgroundResource(item.getLogo());
    }

    private static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView mTextName;
        ImageView mImageView;
        MainViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_logo);
        }
    }
}
