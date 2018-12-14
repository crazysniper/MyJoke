package application.eventdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import application.eventdemo.util.EventUtil;

/**
 * Created by Gao on 2018/12/14.
 */

public class TextViewX extends View {
    public TextViewX(Context context) {
        super(context);
    }

    public TextViewX(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        getParent().requestDisallowInterceptTouchEvent(true); // 阻止ViewGroup对其MOVE或UP事件进行拦截。
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        EventUtil.showEvent(event, "View   dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EventUtil.showEvent(event, "View   onTouchEvent");
        return super.onTouchEvent(event);
    }
}
