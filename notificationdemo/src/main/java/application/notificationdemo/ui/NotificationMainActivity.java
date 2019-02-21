package application.notificationdemo.ui;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.notificationdemo.R;
import application.notificationdemo.R2;
import application.notificationdemo.util.NotificationUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = NotificationUtil.NotificationMainActivity)
public class NotificationMainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_notification_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.open})
    public void onClick(View view) {
        if (view.getId() == R.id.open) {

        }
    }

    public void openNotification() {

    }

}
