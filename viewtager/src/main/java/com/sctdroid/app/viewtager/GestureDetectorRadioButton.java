package com.sctdroid.app.viewtager;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RadioButton;

/**
 * Created by huangxin on 16-8-25.
 */
public class GestureDetectorRadioButton extends RadioButton {

    public interface OnTapListener {
        void onSingleTap();
        void onDoubleTap();
    }

    private OnTapListener mListener;

    private GestureDetector mGestureDetector;

    public GestureDetectorRadioButton(Context context) {
        super(context);
        initGestureDetector(context);
    }

    public GestureDetectorRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGestureDetector(context);
    }

    public GestureDetectorRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGestureDetector(context);
    }

    @TargetApi(21)
    public GestureDetectorRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initGestureDetector(context);
    }

    boolean firstTapToToggle;//switch tab, do not trigger double tap listener if it is true

    private void initGestureDetector(Context context) {
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        if (mListener != null) {
                            mListener.onSingleTap();
                        }
                        firstTapToToggle = !GestureDetectorRadioButton.this.isChecked();
                        return super.onSingleTapUp(e);
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        if (!firstTapToToggle) {
                            if (mListener != null) {
                                mListener.onDoubleTap();
                            }
                        }
                        return super.onDoubleTap(e);
                    }
                });
    }

    public void setOnDoubleTapListener(OnTapListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
