package application.eventdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import application.eventdemo.R;
import application.eventdemo.util.EventUtil;
import application.eventdemo.widget.LayoutX;
import application.eventdemo.widget.TextViewX;

public class EventInfoActivity extends AppCompatActivity {

    private LayoutX layout;
    private TextViewX view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

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
