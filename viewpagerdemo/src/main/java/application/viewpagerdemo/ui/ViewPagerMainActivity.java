package application.viewpagerdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.viewpagerdemo.R;
import application.viewpagerdemo.util.ViewPagerConstant;

@Route(path = ViewPagerConstant.ViewPagerMainActivity)
public class ViewPagerMainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        btn1 = findView(R.id.btn1);
        btn2 = findView(R.id.btn2);
        btn3 = findView(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            ARouter.getInstance().build(ViewPagerConstant.ViewPager_View_Activity).navigation();
        } else if (v.getId() == R.id.btn2) {
            ARouter.getInstance().build(ViewPagerConstant.ViewPager_Fragment_Activity).navigation();
        } else if (v.getId() == R.id.btn3) {
            ARouter.getInstance().build(ViewPagerConstant.FragmentsActivity).navigation();
        }
    }
}
