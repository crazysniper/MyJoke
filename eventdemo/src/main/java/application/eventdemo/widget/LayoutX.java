package application.eventdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import application.eventdemo.util.EventUtil;

/**
 * Created by Gao on 2018/12/14.
 */

public class LayoutX extends LinearLayout {
    public LayoutX(Context context) {
        super(context);
    }

    public LayoutX(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventUtil.showEvent(ev, "ViewGroup   dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        EventUtil.showEvent(ev, "ViewGroup   onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return  true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EventUtil.showEvent(event, "ViewGroup   onTouchEvent");
        return super.onTouchEvent(event);
    }
}
