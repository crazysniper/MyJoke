package application.dialogdemo.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import application.dialogdemo.R;

/**
 * Created by Gao on 2018/12/10.
 */

public class MyPopupWindow extends PopupWindow {
    WindowManager.LayoutParams layoutParams;

    public MyPopupWindow(Context context) {
        this(context, null);
    }

    public MyPopupWindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPopupWindow(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.popup, null);
        setContentView(view);

//        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6fb6b5b5")));

        setAnimationStyle(R.style.anim_popup_dir);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setTouchable(true);
        setFocusable(true);
        setOutsideTouchable(true);

        Window window = ((Activity) context).getWindow();
        layoutParams = window.getAttributes();
        layoutParams.alpha = 0.6f;
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        ((Activity) context).getWindow().setAttributes(layoutParams);
//        setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                layoutParams.alpha = 1f;
                ((Activity) context).getWindow().setAttributes(layoutParams);
            }
        });
    }
}
