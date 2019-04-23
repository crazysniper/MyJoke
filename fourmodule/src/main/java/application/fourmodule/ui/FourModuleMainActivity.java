package application.fourmodule.ui;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.fourmodule.R2;
import application.fourmodule.R;
import application.fourmodule.util.FourModuleConstant;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = FourModuleConstant.FourModuleMainActivity)
public class FourModuleMainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_four_module_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.contact,R2.id.service})
    public void onClikc(View view) {
        if (view.getId() == R.id.contact) {
            ARouter.getInstance().build(FourModuleConstant.ContactActivity).navigation();
        } else if (view.getId() == R.id.service) {
            ARouter.getInstance().build(FourModuleConstant.ServiceMainActivity).navigation();
        }
    }

}
