package com.sctdroid.app.viewtager;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * Created by lixindong on 12/6/16.
 */

public class ViewTager extends RelativeLayout {
    public ViewTager(Context context) {
        this(context, null);
    }

    public ViewTager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewTager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* views */
    SwitchScrollViewPager mViewPager;
    AdaptableRadioGroup mRadioGroup;
    HorizontalScrollView mCateTabs;

    int mPrePosition = 0;

    private void init() {
        inflate(getContext(), R.layout.view_tager, this);
        mViewPager = vbi(R.id.view_pager);
        mRadioGroup = vbi(R.id.interest_radiogroup);
        mCateTabs = vbi(R.id.header_scrollview);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getChildCount()>0){
                    for(int i=0;i< group.getChildCount();i++){
                        ((RadioButton)group.getChildAt(i)).setTypeface(Typeface.DEFAULT);
                        if(group.getChildAt(i).getId() == checkedId){
                            mViewPager.setCurrentItem(i);
                            ((RadioButton)group.getChildAt(i)).setTypeface(Typeface.DEFAULT_BOLD);
                        }
                    }
                }
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(mRadioGroup.getChildAt(position) != null){
                    ((RadioButton)mRadioGroup.getChildAt(position)).setChecked(true);

                    int[] location = new int[2];
                    mRadioGroup.getChildAt(position).getLocationOnScreen(location);
                    int locationX = location[0] + mRadioGroup.getChildAt(position).getWidth()/2;
                    int screenWidth = getResources().getDisplayMetrics().widthPixels;
                    if(locationX >= screenWidth/2 && mPrePosition < position){
                        mCateTabs.smoothScrollBy(locationX - screenWidth/2,0);
                    }
                    if(locationX <= screenWidth/2 && mPrePosition > position){
                        mCateTabs.smoothScrollBy(locationX - screenWidth/2,0);
                    }
                    mPrePosition = position;

                    if (mOnPageChangeListener != null) {
                        mOnPageChangeListener.onPageSelected(position);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }

    public <T extends View> T vbi(int id) {
        return (T) findViewById(id);
    }

    /**
     * set ViewPager adapter
     */
    public void setViewPagerAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
    }

    /**
    * set RadioGroup adapter
    */
    public void setRadioGroupAdapter(RadioAdapter adapter) {
        mRadioGroup.setAdapter(adapter);
    }

    /**
     * get ViewPager adapter
     */
    public PagerAdapter getViewPagerAdapter() {
        return mViewPager.getAdapter();
    }

    /**
     * get RadioGroup adapter
     */
    public RadioAdapter getRadioAdapter() {
        return mRadioGroup.getAdapter();
    }

    /**
     * get raw RadioGroup adapter
     */
    public RadioAdapter getRawRadioAdapter() {
        return mRadioGroup.getRawAdapter();
    }

    /**
     * set ViewPager height delegate
     */
    public void setViewPagerHeightDelegate(SwitchScrollViewPager.ViewHeightDelegate delegate) {
        mViewPager.setViewHeightDelegate(delegate);
    }

    /**
     * getViewPager
     */
    public SwitchScrollViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * getRadioGroup
     */
    public RadioGroup getRadioGroup() {
        return mRadioGroup;
    }

    /**
     * set on ViewPager OnPageChangeListener
     */
    public void setViewPagerOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener;
}