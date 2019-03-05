package application.supportdesignview.ui;

import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.supportdesignview.R;
import application.supportdesignview.R2;
import application.supportdesignview.util.SupportDesignConstant;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = SupportDesignConstant.SupportDesignActivity)
public class SupportDesignActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.activity_support_design;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.constraintLayout, R2.id.asyncTaskLayout, R2.id.doubelClick, R2.id.cardView, R2.id.snackBar})
    public void onClick(View v) {
        if (v.getId() == R.id.constraintLayout) {
            ARouter.getInstance().build(SupportDesignConstant.ConstraintLayoutActivity).navigation();
        } else if (v.getId() == R.id.asyncTaskLayout) {
            ARouter.getInstance().build(SupportDesignConstant.AsyncTaskActivity).navigation();
        } else if (v.getId() == R.id.doubelClick) {
            startActivity(new Intent(SupportDesignActivity.this, TestDoubleClickActivity.class));
        } else if (v.getId() == R.id.cardView) {
            ARouter.getInstance().build(SupportDesignConstant.CardViewActivity).navigation();
        } else if (v.getId() == R.id.snackBar) {
            ARouter.getInstance().build(SupportDesignConstant.SnackBarActivity).navigation();
        }
    }
}
