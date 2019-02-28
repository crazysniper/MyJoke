package application.baseview.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myjoke.baselibray.util.LogUtil;

import application.baseview.R;

public class MyLinearView extends LinearLayout {

    private TextView leftTextView, rightTextView;
    private int leftTextSize = 10, rightTextSize = 10;

    private LeftRightClickListener leftRightClickListener;

    public MyLinearView(Context context) {
        this(context, null);
    }

    public MyLinearView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLinearView);

        int orientation = typedArray.getInt(R.styleable.MyLinearView_orientation, 0);
        setOrientation(orientation);

        int bgColor = typedArray.getColor(R.styleable.MyLinearView_bg, Color.WHITE);
        setBackgroundColor(bgColor);

        boolean leftVisible = typedArray.getBoolean(R.styleable.MyLinearView_leftVisible, false);
        leftTextSize = typedArray.getDimensionPixelSize(R.styleable.MyLinearView_leftTextSize, leftTextSize);
        String leftText = typedArray.getString(R.styleable.MyLinearView_leftText);
        int leftTextColor = typedArray.getColor(R.styleable.MyLinearView_leftTextColor, Color.BLACK);

        boolean rightVisible = typedArray.getBoolean(R.styleable.MyLinearView_rightVisible, false);
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.MyLinearView_rightTextSize, rightTextSize);
        String rightText = typedArray.getString(R.styleable.MyLinearView_rightText);
        int rightTextColor = typedArray.getColor(R.styleable.MyLinearView_rightTextColor, Color.BLACK);

        typedArray.recycle();


        LogUtil.e("字体大小=" + leftTextSize);

        if (leftVisible) {
            leftTextView = new TextView(context);
            leftTextView.setText(leftText);
            leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);// TextView.setTextSize默认单位是sp
            leftTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            leftTextView.setTextColor(leftTextColor);

            LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            leftTextView.setLayoutParams(leftLayoutParams);

            addView(leftTextView);

            leftTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (leftRightClickListener != null) {
                        leftRightClickListener.leftOnClick(v);
                    }
                }
            });
        }


        if (rightVisible) {
            rightTextView = new TextView(context);
            rightTextView.setText(rightText);
            rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);// TextView.setTextSize默认单位是sp
            rightTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            rightTextView.setTextColor(rightTextColor);

            addView(rightTextView);

            LinearLayout.LayoutParams rightLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
            rightLayoutParams.weight = 1;
            rightTextView.setLayoutParams(rightLayoutParams);

            rightTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (leftRightClickListener != null) {
                        leftRightClickListener.rightOnClick(v);
                    }
                }
            });
        }
    }

    public void setLeftText(String leftText) {
        if (leftTextView != null) {
            leftTextView.setText(leftText);
        }
    }

    public void setRightText(String rightText) {
        if (rightTextView != null) {
            rightTextView.setText(rightText);
        }
    }

    public void removeLeftRightClickListener() {
        this.leftRightClickListener = null;
    }

    public void setLeftRightClickListener(LeftRightClickListener leftRightClickListener) {
        this.leftRightClickListener = leftRightClickListener;
    }

    public interface LeftRightClickListener {
        public void leftOnClick(View v);

        public void rightOnClick(View v);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        LogUtil.e("MyLinearView onWindowFocusChanged hasWindowFocus=" +
                hasWindowFocus + "      width=" + getWidth() + "   height=" + getHeight());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.e("MyLinearView     onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.e("MyLinearView     onDetachedFromWindow");
    }
}

