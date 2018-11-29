package application.recyclerviewdemo.ui;

import com.myjoke.baselibray.base.BaseActivity;

import application.recyclerviewdemo.R;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StaggeredLayoutRecyclerViewActivity extends BaseActivity {

    Unbinder unbinder = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_staggered_layout_recycler_view;
    }

    @Override
    public void initView() {

        unbinder = ButterKnife.bind(this);
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
