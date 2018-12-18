package application.materialdemo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.materialdemo.R;
import application.materialdemo.util.MaterialConstant;

@Route(path = MaterialConstant.MaterialMainActivity)
public class CardViewActivity extends BaseActivity {

   @Override
    public int getLayoutId() {
        return R.layout.activity_card_view;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }
}
