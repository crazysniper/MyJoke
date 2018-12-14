package application.eventdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import application.eventdemo.R;
import application.eventdemo.util.EventConstant;

@Route(path = EventConstant.EventMainActivity)
public class EventMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);
        ARouter.getInstance().inject(this);

        initView();
    }

    private void initView() {
        event = (Button) findViewById(R.id.event);

        event.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.event) {
            startActivity(new Intent(EventMainActivity.this, EventInfoActivity.class));
        }
    }
}
