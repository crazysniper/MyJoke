package application.inputlibrary.ui;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.inputlibrary.R;
import application.inputlibrary.util.InputUtil;
import butterknife.ButterKnife;


@Route(path = InputUtil.InputDemoActivity)
public class InputDemoActivity extends BaseActivity {

    @Autowired(name = "mode")//
    public int mode;

    @Override
    public int getLayoutId() {
        LogUtil.e("MODE==" + mode);
        ARouter.getInstance().inject(this);
        getWindow().setSoftInputMode(mode);
        return R.layout.input_demo;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }
}
