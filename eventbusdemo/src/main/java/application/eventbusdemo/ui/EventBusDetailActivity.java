package application.eventbusdemo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.eventbusdemo.R;
import application.eventbusdemo.util.EventBusUtil;
import butterknife.ButterKnife;

@Route(path = EventBusUtil.EventBusDetailActivity)
public class EventBusDetailActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_bus_detail;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }
}
