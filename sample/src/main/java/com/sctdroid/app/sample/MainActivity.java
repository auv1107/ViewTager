package com.sctdroid.app.sample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sctdroid.app.viewtager.RadioAdapter;
import com.sctdroid.app.viewtager.ViewTager;

public class MainActivity extends AppCompatActivity {

    ViewTager mViewTager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewTager = (ViewTager) findViewById(R.id.view_tager);

        final int pageCount = 14;

        mViewTager.setRadioGroupAdapter(new RadioAdapter() {
            @Override
            public int getCount() {
                return pageCount;
            }

            @Override
            public String getItem(int position) {
                return "No. " + (position + 1);
            }

            @Override
            public RadioButton getRadioButton(int position) {
                RadioButton radioButton = new RadioButton(getApplicationContext());
                radioButton.setText(getItem(position));
                radioButton.setTextColor(Color.BLACK);
                radioButton.setBackgroundResource(R.drawable.selector_radio_background);
                radioButton.setPadding(10,48,10,48);
                radioButton.setMinWidth(dp2px(getApplicationContext(), 56));
                radioButton.setButtonDrawable(null);

                return radioButton;
            }
        });

        mViewTager.setViewPagerAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pageCount;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if (container == ((View) object).getParent()) {
                    container.removeView((View) object);
                }
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText(String.format("This is the %s page.", position + 1));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

                container.addView(textView);
                return textView;
            }
        });
    }
    public static int dp2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
}
