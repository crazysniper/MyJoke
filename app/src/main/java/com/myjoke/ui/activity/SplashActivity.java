package com.myjoke.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.SpUtil;
import com.myjoke.util.ConstantPath;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.time)
    TextView time;

    private MyHandler handler = null;
    Unbinder unbinder = null;

    private int countTime = 1;
    private boolean isLogined;

    private boolean isQuickIn;

    @Override
    public int getLayoutId() {
        setTheme(R.style.AppTheme);//恢复原有的样式。在setContentView之前设置。
        requestWindowFeature(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        time = findView(R.id.time);
    }

    @Override
    public void initData() {
        isLogined = SpUtil.get("isLogined", false);

        handler = new MyHandler();
        handler.sendEmptyMessage(1);
    }

    @OnClick(R.id.time)
    public void onClick(View view) {
//        handler.removeMessages(1); // 可以
        isQuickIn = true;
        handler.removeCallbacksAndMessages(null); // 可以
        if (isLogined) {
            ARouter.getInstance().build(ConstantPath.MainActivity).navigation();
        } else {
            ARouter.getInstance().build(ConstantPath.LoginActivity).navigation();
        }
        SplashActivity.this.finish();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    time.setText(String.format(getResources().getString(R.string.splash_duration), countTime));
                    if (countTime > 0) {
                        countTime--;
                        handler.sendEmptyMessageDelayed(1, 1000);
                    } else if (!isQuickIn) {
                        if (isLogined) {
                            ARouter.getInstance().build(ConstantPath.MainActivity).navigation();
                        } else {
                            ARouter.getInstance().build(ConstantPath.LoginActivity).navigation();
                        }
                        SplashActivity.this.finish();
                    }
                    break;
                case 2:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
        unbinder.unbind();
        handler = null;
    }
}
