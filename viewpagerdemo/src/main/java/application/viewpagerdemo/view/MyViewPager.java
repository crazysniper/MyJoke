package application.viewpagerdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent arg0) {
//        return true; // 实验下来，这边返回true,false都一样，只要onInterceptTouchEvent返回false就行了。
//    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return false;
    }
}
