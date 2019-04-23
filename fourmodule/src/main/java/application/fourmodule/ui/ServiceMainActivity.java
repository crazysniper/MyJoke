package application.fourmodule.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.fourmodule.R;
import application.fourmodule.R2;
import application.fourmodule.ui.service.MyService;
import application.fourmodule.util.FourModuleConstant;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = FourModuleConstant.ServiceMainActivity)
public class ServiceMainActivity extends BaseActivity {

    Intent intent = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        intent = new Intent(this, MyService.class);
    }

    @OnClick({R2.id.startService, R2.id.stopService, R2.id.bindService, R2.id.unBindService})
    public void onClick(View view) {
        if (view.getId() == R.id.startService) {
            startService(intent);
        } else if (view.getId() == R.id.stopService) {
            stopService(intent);
        } else if (view.getId() == R.id.bindService) {
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        } else if (view.getId() == R.id.unBindService) {
            unbindService(connection);
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
