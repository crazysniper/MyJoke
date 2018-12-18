package application.materialdemo.ui;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import application.materialdemo.R;
import application.materialdemo.util.MaterialConstant;

@Route(path = MaterialConstant.MaterialMainActivity)
public class SnackBarActivity extends BaseActivity implements View.OnClickListener {

    private Button btn1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_snack_bar;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        btn1 = findView(R.id.btn1);

        btn1.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            Snackbar.make(btn1, "你猜", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(SnackBarActivity.this, "Snackbar确认了");
                }
            }).setActionTextColor(Color.RED)
                    .show();
        }
    }
}
