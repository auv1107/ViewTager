package com.sctdroid.app.viewtager;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.widget.RadioButton;

/**
 * Created by lixindong on 12/6/16.
 */

public abstract class RadioAdapter {
    private DataSetObserver mRadioGroupObserver;
    private final DataSetObservable mObservable = new DataSetObservable();

    public abstract int getCount();
    public abstract Object getItem(int position);
    public abstract RadioButton getRadioButton(int position);

    /**
     * This method should be called by the application if the data backing this adapter has changed
     * and associated views should update.
     */
    public void notifyDataSetChanged() {
        synchronized (this) {
            if (mRadioGroupObserver != null) {
                mRadioGroupObserver.onChanged();
            }
        }
        mObservable.notifyChanged();
    }

    /**
     * Register an observer to receive callbacks related to the adapter's data changing.
     *
     * @param observer The {@link DataSetObserver} which will receive callbacks.
     */
    public void registerDataSetObserver(DataSetObserver observer) {
        mObservable.registerObserver(observer);
    }

    /**
     * Unregister an observer from callbacks related to the adapter's data changing.
     *
     * @param observer The {@link DataSetObserver} which will be unregistered.
     */
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mObservable.unregisterObserver(observer);
    }

    void setViewPagerObserver(DataSetObserver observer) {
        synchronized (this) {
            mRadioGroupObserver = observer;
        }
    }
}
