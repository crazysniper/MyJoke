package application.baseview.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.MeasureSpecUtil;

/**
 * Created by Gao on 2018/11/28.
 */

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.e("________________________________________________________");

        int attrsCount = attrs.getAttributeCount();

        String classAttribute = attrs.getClassAttribute();
        LogUtil.e("MyViewGroup classAttribute==" + classAttribute);

        for (int index = 0; index < attrsCount; index++) {
            String attName = attrs.getAttributeName(index);
            String value = attrs.getAttributeValue(index);
            LogUtil.e("index=" + index + "  attName=" + attName + "  value=" + value);
        }
        LogUtil.e("________________________________________________________");

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if (layoutParams != null) { // layoutParams为空，不执行
            int width = layoutParams.width;
            int height = layoutParams.height;
            LogUtil.e("MyViewGroup width==" + width);
            LogUtil.e("MyViewGroup height==" + height);
            LogUtil.e("MyViewGroup layoutParams.leftMargin==" + layoutParams.leftMargin);
            LogUtil.e("MyViewGroup layoutParams.topMargin==" + layoutParams.topMargin);
            LogUtil.e("MyViewGroup layoutParams.rightMargin==" + layoutParams.rightMargin);
            LogUtil.e("MyViewGroup layoutParams.bottomMargin==" + layoutParams.bottomMargin);
        }
        int measureWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        LogUtil.e("MyViewGroup measureWidth==" + measureWidth); // 0
        LogUtil.e("MyViewGroup measuredHeight==" + measuredHeight); // 0

        LogUtil.e("MyViewGroup getWidth()==" + getWidth()); // 0
        LogUtil.e("MyViewGroup getHeight()==" + getHeight()); // 0

        LogUtil.e("MyViewGroup getMinimumWidth()==" + getMinimumWidth()); // 0
        LogUtil.e("MyViewGroup getMinimumHeight()==" + getMinimumHeight()); // 0

        LogUtil.e("MyViewGroup getLeft()==" + getLeft()); // 0
        LogUtil.e("MyViewGroup getTop()==" + getTop()); // 0
        LogUtil.e("MyViewGroup getRight()==" + getRight()); // 0
        LogUtil.e("MyViewGroup getBottom()==" + getBottom()); // 0
    }

    /*
        <application.baseview.layout.MyViewGroup
        android:id="@+id/myViewGroup"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_marginTop="10dp"
        android:background="#FF3CB9B5" />


        density=2.75
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.e("________________________________________________________");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec); // EXACTLY
        int width = MeasureSpec.getSize(widthMeasureSpec); // 825 = 300*2.75

        int heightMode = MeasureSpec.getMode(heightMeasureSpec); // EXACTLY
        int height = MeasureSpec.getSize(heightMeasureSpec); // 825

        LogUtil.e("MyViewGroup onMeasure widthMode==" + MeasureSpecUtil.getModeName(widthMode));
        LogUtil.e("MyViewGroup onMeasure width==" + width);
        LogUtil.e("MyViewGroup onMeasure heightMode==" + MeasureSpecUtil.getModeName(heightMode));
        LogUtil.e("MyViewGroup onMeasure height==" + height);
        setMeasuredDimension(200, 200);


        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if (layoutParams != null) {
            LogUtil.e("MyViewGroup onMeasure layoutParams.width==" + layoutParams.width); // 825
            LogUtil.e("MyViewGroup onMeasure layoutParams.height==" + layoutParams.height); // 825
            LogUtil.e("MyViewGroup onMeasure layoutParams.leftMargin==" + layoutParams.leftMargin); //
            LogUtil.e("MyViewGroup onMeasure layoutParams.topMargin==" + layoutParams.topMargin); //
            LogUtil.e("MyViewGroup onMeasure layoutParams.rightMargin==" + layoutParams.rightMargin); //
            LogUtil.e("MyViewGroup onMeasure layoutParams.bottomMargin==" + layoutParams.bottomMargin); //
        }

        LogUtil.e("MyViewGroup onMeasure getMeasuredWidth()==" + getMeasuredWidth()); // 200
        LogUtil.e("MyViewGroup onMeasure getMeasuredHeight()==" + getMeasuredHeight()); // 200

        LogUtil.e("MyViewGroup onMeasure getWidth()==" + getWidth()); // 0
        LogUtil.e("MyViewGroup onMeasure getHeight()==" + getHeight()); // 0

        LogUtil.e("MyViewGroup onMeasure getMinimumWidth()==" + getMinimumWidth()); // 0
        LogUtil.e("MyViewGroup onMeasure getMinimumHeight()==" + getMinimumHeight()); // 0

        LogUtil.e("MyViewGroup onMeasure getLeft()==" + getLeft()); // 0
        LogUtil.e("MyViewGroup onMeasure getTop()==" + getTop()); // 0
        LogUtil.e("MyViewGroup onMeasure getRight()==" + getRight()); // 0
        LogUtil.e("MyViewGroup onMeasure getBottom()==" + getBottom()); // 0
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtil.e("MyViewGroup onLayout getMeasuredWidth()==" + getMeasuredWidth()); // 200
        LogUtil.e("MyViewGroup onLayout getMeasuredHeight()==" + getMeasuredHeight()); // 200

        LogUtil.e("MyViewGroup onLayout getWidth()==" + getWidth()); // 200
        LogUtil.e("MyViewGroup onLayout getHeight()==" + getHeight()); // 200

        LogUtil.e("MyViewGroup onLayout getMinimumWidth()==" + getMinimumWidth()); // 0
        LogUtil.e("MyViewGroup onLayout getMinimumHeight()==" + getMinimumHeight()); // 0

        LogUtil.e("MyViewGroup onLayout getLeft()==" + getLeft());
        LogUtil.e("MyViewGroup onLayout getTop()==" + getTop());
        LogUtil.e("MyViewGroup onLayout getRight()==" + getRight());
        LogUtil.e("MyViewGroup onLayout getBottom()==" + getBottom());
        LogUtil.e("MyViewGroup onLayout changed=" + changed + "    l=" + l + "   t=" + t + "   r=" + r + "   b=" + b);
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.e("MyViewGroup onFinishInflate");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        LogUtil.e("MyViewGroup dispatchDraw");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtil.e("MyViewGroup onSizeChanged     w=" + w + "   h=" + h + "   oldw=" + oldw + "   oldh=" + oldh);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.e("MyViewGroup onAttachedToWindow");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        LogUtil.e("MyViewGroup onWindowFocusChanged changed=" + hasWindowFocus);
    }

}
