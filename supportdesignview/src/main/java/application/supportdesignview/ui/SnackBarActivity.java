package application.supportdesignview.ui;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import application.supportdesignview.R;
import application.supportdesignview.R2;
import application.supportdesignview.util.SupportDesignConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = SupportDesignConstant.SnackBarActivity)
public class SnackBarActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R2.id.btn1)
    Button btn1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_snack_bar;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.btn1})
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            Snackbar snackbar = Snackbar.make(btn1, "你猜", Snackbar.LENGTH_INDEFINITE).setAction("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(SnackBarActivity.this, "Snackbar确认了");
                }
            }).setActionTextColor(Color.RED);

            snackbar.getView().setBackgroundColor(Color.BLUE);

            snackbar.show();
        }
    }
}
