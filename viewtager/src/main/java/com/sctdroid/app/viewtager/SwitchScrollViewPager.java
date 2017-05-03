package com.sctdroid.app.viewtager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by weilei on 16-8-30.
 */
public class SwitchScrollViewPager extends ViewPager {
    private boolean isScrollable = true;

    public SwitchScrollViewPager(Context context) {
        super(context);
    }

    public SwitchScrollViewPager(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isScrollable == false) {
            return false;
        } else {
            return super.onTouchEvent(event);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isScrollable == false) {
            return false;
        } else {
            return super.onInterceptTouchEvent(event);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mDelegate != null) {
            int height = mDelegate.getMeasuredHeight();
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                    MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public interface ViewHeightDelegate {
        int getMeasuredHeight();
    }

    public ViewHeightDelegate mDelegate = null;

    public void setViewHeightDelegate(ViewHeightDelegate delegate) {
        mDelegate = delegate;
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }
}
