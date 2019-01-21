package application.baseview.layout;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.myjoke.baselibray.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gao on 2019/1/10.
 */

public class FlexLayout extends ViewGroup {

    public FlexLayout(Context context) {
        this(context, null);
    }

    public FlexLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return super.generateLayoutParams(attrs);
        return new MarginLayoutParams(getContext(), attrs);
    }


    private List<List<View>> lineViews = new ArrayList<>();
    private List<Integer> lineHeights = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //由于onMeasure会执行多次,避免重复的计算控件个数和高度,这里需要进行清空操作
        lineViews.clear();
        lineHeights.clear();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int viewGroupWidth = 0;
        int viewGroupHeight = 0;


        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            viewGroupWidth = widthSize;
            viewGroupHeight = heightSize;
        } else {
            int childViewCount = getChildCount();

            //当前行所占的宽高
            int currentLineWidth = 0;
            int currentLineHeight = 0;

            //用来存储每一行上的子View
            List<View> lineView = new ArrayList<View>();

            for (int index = 0; index < childViewCount; index++) {
                View childView = getChildAt(index);
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);

                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

                int childViewWidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                int childViewHeight = childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

                LogUtil.e("子View index==" + index + "  宽度=" + childViewWidth + "   高度=" + childViewHeight + "     widthSize=" + widthSize);

                LogUtil.e("currentLineWidth=" + currentLineWidth + "  currentLineHeight=" + currentLineHeight);
                LogUtil.e("viewGroupWidth=" + viewGroupWidth + "  viewGroupHeight=" + viewGroupHeight);

                if (currentLineWidth + childViewWidth > widthSize) { // 超过一行了
                    viewGroupWidth = Math.max(currentLineWidth, childViewWidth); // 获取此时ViewGroup的宽度。
                    viewGroupHeight += currentLineHeight; //  获取此时ViewGroup的高度。

                    lineHeights.add(currentLineHeight); // 添加行高
                    lineViews.add(lineView);

                    lineView = new ArrayList<View>();
                    lineView.add(childView);

                    currentLineWidth = childViewWidth;
                } else {  //当前行宽+子View+左右外边距<=ViewGroup的宽度,不换行
                    currentLineWidth += childViewWidth;
                    currentLineHeight = Math.max(currentLineHeight, childViewHeight);

                    lineView.add(childView); //添加行对象里的子View
                }

                if (index == childViewCount - 1) {
                    //最后一个子View的时候
                    //添加行对象
                    lineViews.add(lineView);
                    viewGroupWidth = Math.max(childViewWidth, viewGroupWidth);
                    viewGroupHeight += childViewHeight;
                    //添加行高
                    lineHeights.add(currentLineHeight);
                }
            }
        }
        setMeasuredDimension(viewGroupWidth, viewGroupHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lines = lineViews.size();
        int left = 0;
        int top = 0;

        LogUtil.e("总行数==" + lines);

        for (int lineIndex = 0; lineIndex < lines; lineIndex++) {
            List<View> viewList = lineViews.get(lineIndex);

            int lineHeight = lineHeights.get(lineIndex);  //每行行高
            int lineViewSize = viewList.size();

            LogUtil.e("第" + lineIndex + "行，有子View数量=" + lineViewSize + "  行高=" + lineHeight);

            for (int index = 0; index < lineViewSize; index++) {
                View childView = viewList.get(index);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

                int vl = left + layoutParams.leftMargin;
                int vt = top + layoutParams.topMargin;
                int vr = vl + childView.getMeasuredWidth();
                int vb = vt + childView.getMeasuredHeight();

                childView.layout(vl, vt, vr, vb);
                left += layoutParams.leftMargin + childView.getMeasuredWidth() + layoutParams.rightMargin;
            }

            left = 0;
            top += lineHeight;
        }
    }
}
