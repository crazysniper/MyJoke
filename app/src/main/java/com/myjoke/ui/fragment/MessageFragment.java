package com.myjoke.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myjoke.R;
import com.myjoke.baselibray.base.BaseFragment;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ToastUtil;
import com.myjoke.events.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MessageFragment extends BaseFragment {

    @BindView(R.id.tv1)
    TextView tv1;

    public MessageFragment() {
    }


    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        LogUtil.e("MessageFragment  initView=" + EventBus.getDefault().isRegistered(this));
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, view);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn1})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ToastUtil.getInstance().showToast("点击了消息模块里面的按钮");
                EventBus.getDefault().post(new FragmentEvent("message", "事件从消息模块来:" + System.currentTimeMillis()));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateView(FragmentEvent fragmentEvent) {
        LogUtil.e("MessageFragment updateView " + fragmentEvent);
        if (fragmentEvent != null && "activity".equals(fragmentEvent.getType())) {
            LogUtil.e("MessageFragment msg=" + fragmentEvent.getMsg());
            tv1.setText(fragmentEvent.getMsg());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("MessageFragment  onDestroy=" + EventBus.getDefault().isRegistered(this));
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
