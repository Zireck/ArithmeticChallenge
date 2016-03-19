package com.zireck.arithmetic.arithmeticchallenge.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.viewpagerindicator.CirclePageIndicator;

public class NonSwipeableCirclePageIndicator extends CirclePageIndicator {

    private boolean mIsEnabledSwipe = true;

    public NonSwipeableCirclePageIndicator(Context context) {
        super(context);
    }

    public NonSwipeableCirclePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonSwipeableCirclePageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mIsEnabledSwipe) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    public void setEnabledSwipe(boolean enabled) {
        mIsEnabledSwipe = enabled;
    }
}
