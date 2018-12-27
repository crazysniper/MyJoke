
package application.recyclerviewdemo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.util.RecyclerViewConstant;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = RecyclerViewConstant.GridLayoutRecyclerViewActivity)
public class GridLayoutRecyclerViewActivity extends BaseActivity {

    Unbinder unbinder = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_grid_layout_recycler_view;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }
}
