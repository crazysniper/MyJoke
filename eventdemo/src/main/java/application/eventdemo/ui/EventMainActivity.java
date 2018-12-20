package application.eventdemo.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.eventdemo.R;
import application.eventdemo.util.EventConstant;

@Route(path = EventConstant.EventMainActivity)
public class EventMainActivity extends BaseActivity implements View.OnClickListener {

    private Button event;

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        event = (Button) findViewById(R.id.event);

        event.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.event) {
            startActivity(new Intent(EventMainActivity.this, EventInfoActivity.class));
        }
    }
}
