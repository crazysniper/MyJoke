package application.eventdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.myjoke.baselibray.util.LogUtil;

import application.eventdemo.R;
import application.eventdemo.util.EventUtil;

/**
 * Created by Gao on 2018/12/14.
 */

public class TextViewX extends View {
    public TextViewX(Context context) {
        this(context, null);
    }

    public TextViewX(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewX);


        int attrsCount = attrs.getAttributeCount();

        String id = attrs.getIdAttribute();
        String classAttribute = attrs.getClassAttribute();
        LogUtil.e("TextViewX attrsCount=" + attrsCount + "    id=" + id + "   classAttribute=" + classAttribute);

        for (int index = 0; index < attrsCount; index++) {
            String attName = attrs.getAttributeName(index);
            int namespace = attrs.getAttributeNameResource(index);
            String value = attrs.getAttributeValue(index);
            LogUtil.e("TextViewX index=" + index + "  attName=" + attName + "  value=" + value + "    namespace=" + namespace);
        }

        int TextViewX_bgColor = typedArray.getColor(R.styleable.TextViewX_bgColor2, -1);
        LogUtil.e("TextViewX getResourceId TextViewX_bgColor=" + TextViewX_bgColor);

        setBackgroundColor(TextViewX_bgColor);

        TypedValue value = new TypedValue();
        boolean has = typedArray.getValue(R.styleable.TextViewX_bgColor2, value);
        if (has) {
            LogUtil.e("TextViewX value.type=" + value.type);
            if (value.type == TypedValue.TYPE_INT_COLOR_RGB8) {
                LogUtil.e("TextViewX 是颜色");
            }
            if (value.type == TypedValue.TYPE_REFERENCE) {
                LogUtil.e("TextViewX 是引用");
            }
        } else {
            LogUtil.e("TextViewX 没有");
        }

        typedArray.recycle();

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
