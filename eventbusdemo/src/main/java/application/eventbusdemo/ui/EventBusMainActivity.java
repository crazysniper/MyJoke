package application.eventbusdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import application.baseview.layout.MyLinearView;
import application.eventbusdemo.R;
import application.eventbusdemo.R2;
import application.eventbusdemo.util.EventBusUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = EventBusUtil.EventBusMainActivity)
public class EventBusMainActivity extends BaseActivity {

    @BindView(R2.id.eventBus)
    Button eventBus;

    @BindView(R2.id.main)
    MyLinearView main;
    @BindView(R2.id.background)
    MyLinearView background;
    @BindView(R2.id.mainOrdered)
    MyLinearView mainOrdered;
    @BindView(R2.id.posting)
    MyLinearView posting;
    @BindView(R2.id.async)
    MyLinearView async;

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_bus_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.eventBus})
    public void onClick(View view) {
        if (view.getId() == R.id.eventBus) {
            ARouter.getInstance().build(EventBusUtil.EventBusDetailActivity).navigation();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void main(String message) {
        main.setRightText(message);
        LogUtil.e("main=" + message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 110)
    public void main2(String message) {
        main.setRightText(message);
        LogUtil.e("main2=" + message);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 90)
    public void background(String message) {
        LogUtil.e("background=" + message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, priority = 80)
    public void mainOrdered(String message) {
        LogUtil.e("mainOrdered=" + message);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 70)
    public void posting(String message) {
        LogUtil.e("posting=" + message);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 120)
    public void posting2(String message) {
        LogUtil.e("posting2=" + message);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 60)
    public void async(String message) {
        LogUtil.e("async=" + message);
    }
}
