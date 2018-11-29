package com.myjoke.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.util.ConstantPath;

public class SplashActivity extends BaseActivity {

    private MyHandler handler = null;

    private TextView time;
    private int countTime = 3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        time = findView(R.id.time);

    }

    @Override
    public void initData() {
        time.setText("" + countTime);

        handler = new MyHandler();
        handler.sendEmptyMessageDelayed(1, 1000);

        new Handler().post(new Runnable() {
            @Override
            public void run() {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    countTime--;
                    time.setText("" + countTime);
                    if (countTime > 0 ) {
                        handler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        ARouter.getInstance().build(ConstantPath.MainActivity).navigation();
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
        handler = null;
    }
}
