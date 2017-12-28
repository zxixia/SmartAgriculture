package edu.njau.SmartAgriculture.module.my;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.base.fragments.BaseFragment;
import edu.njau.SmartAgriculture.module.gcfa.PeriodActivity;

/**
 * Created by lenovo on 2017/12/24.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.rl_my_common_info)
    RelativeLayout mCommonInfo;/* 通用信息 */

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @OnClick({R.id.rl_my_common_info})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_my_common_info) {
            Intent intent = new Intent();
            intent.putExtra("Topic", "GCFA_TOPIC1");
            intent.setClass(getActivity(), MessageListActivity.class);
            startActivity(intent);
        }
    }
}
