package com.sctdroid.app.viewtager;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by lixindong on 12/6/16.
 */

public class AdaptableRadioGroup extends RadioGroup {
    public AdaptableRadioGroup(Context context) {
        super(context);
    }

    private MyRadioAdapter mRadioAdapter;
    private DataSetObserver mDataSetObserver;

    public AdaptableRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                removeAllViews();
                for (int i = 0; i < mRadioAdapter.getCount(); i++) {
                    RadioButton radioButton = mRadioAdapter.getRadioButton(i);
                    addView(radioButton);
                }
                setCurrentItem(0);
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        };

        mRadioAdapter = new MyRadioAdapter(null);
        mRadioAdapter.setViewPagerObserver(mDataSetObserver);
    }

    public void setCurrentItem(int position) {
        if (position >= 0 && position < mRadioAdapter.getCount()) {
            ((RadioButton) getChildAt(position)).setChecked(true);
        }
    }

    public void setAdapter(final RadioAdapter adapter) {
        mRadioAdapter.setAdapter(adapter);
        mRadioAdapter.notifyDataSetChanged();
    }

    public RadioAdapter getAdapter() {
        return mRadioAdapter;
    }

    public RadioAdapter getRawAdapter() {
        return mRadioAdapter == null ? null : mRadioAdapter.getBridgeAdapter();
    }

    class MyRadioAdapter extends RadioAdapter {
        RadioAdapter mAdapter;
        MyRadioAdapter(RadioAdapter adapter) {
            setAdapter(adapter);
        }
        @Override
        public int getCount() {
            return mAdapter == null ? 0 : mAdapter.getCount();
        }

        @Override
        public Object getItem(int position) {
            return mAdapter == null ? null : mAdapter.getItem(position);
        }

        @Override
        public RadioButton getRadioButton(int position) {
            return mAdapter == null ? null : mAdapter.getRadioButton(position);
        }

        void setAdapter(RadioAdapter adapter) {
            mAdapter = adapter;
            if (adapter != null) {
                adapter.setViewPagerObserver(mDataSetObserver);
            }
        }
        RadioAdapter getBridgeAdapter() {
            return mAdapter;
        }
    }
}
