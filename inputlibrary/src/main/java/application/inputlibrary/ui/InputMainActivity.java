package application.inputlibrary.ui;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

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
//        Postcard postcard = ARouter.getInstance().build(InputUtil.InputDemoActivity);
        Intent intent = new Intent(this, InputDemoActivity.class);
        if (view.getId() == R.id.stateUnspecified) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        } else if (view.getId() == R.id.stateUnchanged) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED);
        } else if (view.getId() == R.id.stateHidden) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        } else if (view.getId() == R.id.stateAlwaysHidden) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } else if (view.getId() == R.id.stateVisible) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        } else if (view.getId() == R.id.stateAlwaysVisible) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        } else if (view.getId() == R.id.adjustUnspecified) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
        } else if (view.getId() == R.id.adjustResize) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        } else if (view.getId() == R.id.adjustPan) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        } else if (view.getId() == R.id.adjustNoting) {
            intent.putExtra("mode", WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
        startActivity(intent);
    }
}
