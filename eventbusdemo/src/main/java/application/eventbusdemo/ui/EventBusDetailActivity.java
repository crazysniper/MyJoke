package application.eventbusdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

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

    @OnClick({R2.id.postString, R2.id.postSticky, R2.id.toNext})
    public void onClick(View view) {
        if (view.getId() == R.id.postString) {
            EventBus.getDefault().post(new String("String类型的msg"));
        } else if (view.getId() == R.id.postString) {
            EventBus.getDefault().postSticky(new EventBean("EventBean类型的msg"));
        } else if (view.getId() == R.id.toNext) {
            ARouter.getInstance().build(EventBusUtil.EventBusStickyActivity).navigation();
        }
    }
}
