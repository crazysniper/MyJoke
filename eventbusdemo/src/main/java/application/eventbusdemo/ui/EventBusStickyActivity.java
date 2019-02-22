package application.eventbusdemo.ui;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ToastUtil;

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

    @OnClick({R2.id.register, R2.id.unregister, R2.id.removeSticky, R2.id.getStickyEvent})
    public void onClick(View view) {
        if (view.getId() == R.id.register) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
                ToastUtil.getInstance().showToast("绑定");
            }
        } else if (view.getId() == R.id.unregister) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
                ToastUtil.getInstance().showToast("解绑");
            }
        } else if (view.getId() == R.id.removeSticky) {
            EventBus.getDefault().removeAllStickyEvents();
        } else if (view.getId() == R.id.getStickyEvent) {
            EventBean eventBean = EventBus.getDefault().getStickyEvent(EventBean.class);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void main(EventBean eventBean) {
        LogUtil.e("main=" + eventBean);
        boolean result = EventBus.getDefault().removeStickyEvent(eventBean);
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
