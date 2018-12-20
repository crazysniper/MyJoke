package application.eventdemo.ui;

import android.view.MotionEvent;
import android.view.View;

import com.myjoke.baselibray.base.BaseActivity;

import application.eventdemo.R;
import application.eventdemo.util.EventUtil;
import application.eventdemo.widget.LayoutX;
import application.eventdemo.widget.TextViewX;

public class EventInfoActivity extends BaseActivity {

    private LayoutX layout;
    private TextViewX view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_info;
    }

    @Override
    public void initView() {
        layout = (LayoutX) findViewById(R.id.layout);
        view = (TextViewX) findViewById(R.id.view);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventUtil.showEvent(null, "ViewGroup   onClick");
            }
        });

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EventUtil.showEvent(event, "ViewGroup   onTouch");
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventUtil.showEvent(null, "View   onClick");
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EventUtil.showEvent(event, "View   onTouch");
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventUtil.showEvent(ev, "Activity   dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EventUtil.showEvent(event, "Activity   onTouchEvent");
        return super.onTouchEvent(event);
    }
}
