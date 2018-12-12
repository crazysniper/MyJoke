package application.supportdesignview.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.supportdesignview.R;
import application.supportdesignview.util.SupportDesignConstant;


@Route(path = SupportDesignConstant.SupportDesignActivity)
public class SupportDesignActivity extends BaseActivity implements View.OnClickListener {

    private Button constraintLayout, asyncTaskLayout, doubelClick;

    @Override
    public int getLayoutId() {
        return R.layout.activity_support_design;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        constraintLayout = findView(R.id.constraintLayout);
        asyncTaskLayout = findView(R.id.asyncTaskLayout);
        doubelClick = findView(R.id.doubelClick);

        constraintLayout.setOnClickListener(this);
        asyncTaskLayout.setOnClickListener(this);
        doubelClick.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.constraintLayout) {
            ARouter.getInstance().build(SupportDesignConstant.ConstraintLayoutActivity).navigation();
        } else if (v.getId() == R.id.asyncTaskLayout) {
            ARouter.getInstance().build(SupportDesignConstant.AsyncTaskActivity).navigation();
        } else if (v.getId() == R.id.doubelClick) {
            startActivity(new Intent(SupportDesignActivity.this, TestDoubleClickActivity.class));
        }
    }
}
