package application.fourmodule.ui;

import android.view.View;
import android.widget.Button;

import com.myjoke.baselibray.base.BaseActivity;

import application.fourmodule.R;


public class BroadcastMainActivity extends BaseActivity implements View.OnClickListener {

    private Button service, broadcast, contentProvider;

    @Override
    public int getLayoutId() {
        return R.layout.activity_broadcast_main;
    }

    @Override
    public void initView() {
        service = findView(R.id.service);
        broadcast = findView(R.id.broadcast);
        contentProvider = findView(R.id.contentProvider);

        service.setOnClickListener(this);
        broadcast.setOnClickListener(this);
        contentProvider.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.service) {

        } else if (v.getId() == R.id.broadcast) {

        } else if (v.getId() == R.id.contentProvider) {

        }
    }
}
