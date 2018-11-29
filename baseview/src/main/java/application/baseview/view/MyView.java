package application.baseview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.MeasureSpecUtil;

/**
 * Created by Gao on 2018/11/28.
 */

public class MyView extends View {
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.e("________________________________________________________");

        int attrsCount = attrs.getAttributeCount();

        String classAttribute = attrs.getClassAttribute();
        LogUtil.e("MyView classAttribute==" + classAttribute);

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
            LogUtil.e("MyView width==" + width);
            LogUtil.e("MyView height==" + height);
            LogUtil.e("MyView layoutParams.leftMargin==" + layoutParams.leftMargin);
            LogUtil.e("MyView layoutParams.topMargin==" + layoutParams.topMargin);
            LogUtil.e("MyView layoutParams.rightMargin==" + layoutParams.rightMargin);
            LogUtil.e("MyView layoutParams.bottomMargin==" + layoutParams.bottomMargin);
        }

        int measureWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        LogUtil.e("MyView measureWidth==" + measureWidth); // 0
        LogUtil.e("MyView measuredHeight==" + measuredHeight); // 0

        LogUtil.e("MyView getWidth()==" + getWidth()); // 0
        LogUtil.e("MyView getHeight()==" + getHeight()); // 0

        LogUtil.e("MyView getMinimumWidth()==" + getMinimumWidth()); // 0
        LogUtil.e("MyView getMinimumHeight()==" + getMinimumHeight()); // 0

        LogUtil.e("MyView getLeft()==" + getLeft()); // 0
        LogUtil.e("MyView getTop()==" + getTop()); // 0
        LogUtil.e("MyView getRight()==" + getRight()); // 0
        LogUtil.e("MyView getBottom()==" + getBottom()); // 0
    }


    /*
       <application.baseview.view.MyView
        android:id="@+id/myView"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:background="#d9ac64" />


        density=2.75
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.e("________________________________________________________");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec); // EXACTLY
        int width = MeasureSpec.getSize(widthMeasureSpec); // 550 = 200*2.75

        int heightMode = MeasureSpec.getMode(heightMeasureSpec); // EXACTLY
        int height = MeasureSpec.getSize(heightMeasureSpec); // 550

        LogUtil.e("MyView onMeasure widthMode==" + MeasureSpecUtil.getModeName(widthMode));
        LogUtil.e("MyView onMeasure width==" + width);
        LogUtil.e("MyView onMeasure heightMode==" + MeasureSpecUtil.getModeName(heightMode));
        LogUtil.e("MyView onMeasure height==" + height);

        setMeasuredDimension(100, 100);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if (layoutParams != null) {
            LogUtil.e("MyView onMeasure layoutParams.width==" + layoutParams.width); // 550
            LogUtil.e("MyView onMeasure layoutParams.height==" + layoutParams.height); // 550
            LogUtil.e("MyView onMeasure layoutParams.leftMargin==" + layoutParams.leftMargin); // 550
            LogUtil.e("MyView onMeasure layoutParams.topMargin==" + layoutParams.topMargin); // 550
            LogUtil.e("MyView onMeasure layoutParams.rightMargin==" + layoutParams.rightMargin); // 550
            LogUtil.e("MyView onMeasure layoutParams.bottomMargin==" + layoutParams.bottomMargin); // 550

        }
        LogUtil.e("MyView onMeasure getMeasuredWidth()==" + getMeasuredWidth()); // 100
        LogUtil.e("MyView onMeasure getMeasuredHeight()==" + getMeasuredHeight()); // 100

        LogUtil.e("MyView onMeasure getWidth()==" + getWidth()); // 0
        LogUtil.e("MyView onMeasure getHeight()==" + getHeight()); // 0

        LogUtil.e("MyView onMeasure getMinimumWidth()==" + getMinimumWidth()); // 0
        LogUtil.e("MyView onMeasure getMinimumHeight()==" + getMinimumHeight()); // 0

        LogUtil.e("MyView onMeasure getLeft()==" + getLeft()); // 0
        LogUtil.e("MyView onMeasure getTop()==" + getTop()); // 0
        LogUtil.e("MyView onMeasure getRight()==" + getRight()); // 0
        LogUtil.e("MyView onMeasure getBottom()==" + getBottom()); // 0
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.e("MyView onLayout changed=" + changed + "    left=" + left + "   top=" + top + "   right=" + right + "   bottom=" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.e("MyView onDraw");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.e("MyView onFinishInflate");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        LogUtil.e("MyView dispatchDraw");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtil.e("MyView onSizeChanged     w=" + w + "   h=" + h + "   oldw=" + oldw + "   oldh=" + oldh);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.e("MyView onAttachedToWindow");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        LogUtil.e("MyView onWindowFocusChanged changed=" + hasWindowFocus);
    }


}
