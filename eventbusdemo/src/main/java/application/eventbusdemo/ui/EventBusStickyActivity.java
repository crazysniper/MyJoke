package application.eventbusdemo.ui;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import application.eventbusdemo.R;
import application.eventbusdemo.R2;
import application.eventbusdemo.bean.EventBean;
import application.eventbusdemo.util.EventBusUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = EventBusUtil.EventBusStickyActivity)
public class EventBusStickyActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_bus_sticky;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void register() {
    }

    @OnClick({R2.id.register})
    public void onClick(View view) {
        if (view.getId() == R.id.register) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void main(EventBean eventBean) {
        LogUtil.e("main=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void background(EventBean eventBean) {
        LogUtil.e("background=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, sticky = true)
    public void mainOrdered(EventBean eventBean) {
        LogUtil.e("mainOrdered=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void posting(EventBean eventBean) {
        LogUtil.e("posting=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    public void async(EventBean eventBean) {
        LogUtil.e("async=" + eventBean);
    }

}
