package application.eventdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.myjoke.baselibray.util.LogUtil;

import application.eventdemo.R;
import application.eventdemo.util.EventUtil;

/**
 * Created by Gao on 2018/12/14.
 */

public class LayoutX extends LinearLayout {
    public LayoutX(Context context) {
        this(context, null);
    }

    public LayoutX(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LayoutX);

        int attrsCount = attrs.getAttributeCount();

        for (int index = 0; index < attrsCount; index++) {
            String attName = attrs.getAttributeName(index);
            String value = attrs.getAttributeValue(index);
            LogUtil.e("LayoutX index=" + index + "  attName=" + attName + "  value=" + value);
        }
        LogUtil.e("________________________________________________________");

        int LayoutX_bgColor = typedArray.getResourceId(R.styleable.LayoutX_bgColor, -1);
        LogUtil.e("LayoutX getColor LayoutX_bgColor=" + LayoutX_bgColor); // -1
        LogUtil.e("LayoutX_bg=" + getResources().getColor(R.color.LayoutX_bg));

        setBackgroundResource(LayoutX_bgColor);

        int type = typedArray.getType(R.styleable.LayoutX_bgColor);
        LogUtil.e("type=" + type);


        TypedValue value = new TypedValue();
        boolean has = typedArray.getValue(R.styleable.LayoutX_bgColor, value);
        if (has) {
            LogUtil.e("LayoutX value.type=" + value.type);
            if (value.type == TypedValue.TYPE_REFERENCE) {
                LogUtil.e("LayoutX 是引用");
            }
            if (value.type == TypedValue.TYPE_INT_COLOR_RGB8) {
                LogUtil.e("LayoutX 是颜色");
            }

        } else {
            LogUtil.e("LayoutX 没有");
        }

        typedArray.recycle();

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
