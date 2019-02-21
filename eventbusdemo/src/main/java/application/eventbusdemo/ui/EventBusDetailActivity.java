package application.eventbusdemo.ui;

import android.view.View;
import android.widget.Button;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = EventBusUtil.EventBusDetailActivity)
public class EventBusDetailActivity extends BaseActivity {

    @BindView(R2.id.postString)
    Button postString;

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

    @Override
    protected void register() {
    }

    @OnClick({R2.id.register, R2.id.unregister, R2.id.postString, R2.id.postSticky, R2.id.toNext})
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
        } else if (view.getId() == R.id.postString) {
            EventBus.getDefault().post(new String("String类型的msg"));
        } else if (view.getId() == R.id.postSticky) {
            ToastUtil.getInstance().showToast("发送黏性");
            EventBus.getDefault().postSticky(new EventBean("EventBean类型的msg"));
        } else if (view.getId() == R.id.toNext) {
            ARouter.getInstance().build(EventBusUtil.EventBusStickyActivity).navigation();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void main(EventBean eventBean) {
        LogUtil.e("sticky main=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void background(EventBean eventBean) {
        LogUtil.e("sticky background=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, sticky = true)
    public void mainOrdered(EventBean eventBean) {
        LogUtil.e("sticky mainOrdered=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void posting(EventBean eventBean) {
        LogUtil.e("sticky posting=" + eventBean);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    public void async(EventBean eventBean) {
        LogUtil.e("sticky async=" + eventBean);
    }

}
