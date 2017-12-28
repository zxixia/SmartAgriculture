package edu.njau.SmartAgriculture.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.widget.TextView;

import edu.njau.SmartAgriculture.R;

/**
 * Created by zhaoxin5 on 2017/3/22.
 */

public class NavigationButton extends TextView {

    private Fragment mFragment = null;
    private Class<?> mClx;
    private String mTag;

    public NavigationButton(Context context) {
        super(context);
    }

    public NavigationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(int id, Class<?> cls) {
        float density = getContext().getResources().getDisplayMetrics().density;
        if (id > 0) {
            Drawable drawable = getResources().getDrawable(id);
            if (null != drawable) {
                drawable.setBounds(0, 0, (int)(20 * density), (int)(20 * density));
                this.setCompoundDrawables(null, drawable, null, null);
            }
        }
        mClx = cls;
        mTag = mClx.getName();
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public Class<?> getClx() {
        return mClx;
    }

    public String getTag() {
        return mTag;
    }
}
