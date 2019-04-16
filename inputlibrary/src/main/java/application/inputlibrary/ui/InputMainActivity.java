package application.inputlibrary.ui;

import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.inputlibrary.R;
import application.inputlibrary.R2;
import application.inputlibrary.util.InputUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = InputUtil.InputMainActivity)
public class InputMainActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_input_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.stateUnspecified, R2.id.stateUnchanged, R2.id.stateHidden, R2.id.stateAlwaysHidden,
            R2.id.stateVisible, R2.id.stateAlwaysVisible,
            R2.id.adjustUnspecified, R2.id.adjustResize, R2.id.adjustPan, R2.id.adjustNoting})
    public void onClick(View view) {
        Postcard postcard = ARouter.getInstance().build(InputUtil.InputDemoActivity);
        if (view.getId() == R.id.stateUnspecified) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        } else if (view.getId() == R.id.stateUnchanged) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED);
        } else if (view.getId() == R.id.stateHidden) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        } else if (view.getId() == R.id.stateAlwaysHidden) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } else if (view.getId() == R.id.stateVisible) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        } else if (view.getId() == R.id.stateAlwaysVisible) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        } else if (view.getId() == R.id.adjustUnspecified) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
        } else if (view.getId() == R.id.adjustResize) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        } else if (view.getId() == R.id.adjustPan) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        } else if (view.getId() == R.id.adjustNoting) {
            postcard.withInt("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
        postcard.navigation();
    }
}
