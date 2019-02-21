package application.baseview.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        boolean leftVisible = typedArray.getBoolean(R.styleable.MyLinearView_leftVisible, false);
        leftTextSize = typedArray.getDimensionPixelSize(R.styleable.MyLinearView_leftTextSize, leftTextSize);
        String leftText = typedArray.getString(R.styleable.MyLinearView_leftText);
        int leftTextColor = typedArray.getColor(R.styleable.MyLinearView_leftTextColor, Color.WHITE);

        boolean rightVisible = typedArray.getBoolean(R.styleable.MyLinearView_rightVisible, false);
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.MyLinearView_rightTextSize, rightTextSize);
        String rightText = typedArray.getString(R.styleable.MyLinearView_rightText);
        int rightTextColor = typedArray.getColor(R.styleable.MyLinearView_rightTextColor, Color.WHITE);

        typedArray.recycle();


        if (leftVisible) {
            leftTextView = new TextView(context);
            leftTextView.setText(leftText);
            leftTextView.setTextSize(leftTextSize);
            leftTextView.setTextColor(leftTextColor);
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
            rightTextView.setTextSize(rightTextSize);
            rightTextView.setTextColor(rightTextColor);
            addView(rightTextView);

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

}

