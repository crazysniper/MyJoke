package application.recyclerviewdemo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ScreenUtil;
import com.myjoke.baselibray.util.ToastUtil;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.R2;
import application.recyclerviewdemo.adapter.LinearLayoutAdapter;
import application.recyclerviewdemo.divider.LinearLayoutItemDecoration;
import application.recyclerviewdemo.util.DataUtil;
import application.recyclerviewdemo.util.RecyclerViewConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = RecyclerViewConstant.LinearLayoutRecyclerViewActivity)
public class LinearLayoutRecyclerViewActivity extends BaseActivity implements LinearLayoutAdapter.LinearLayoutClickListener, LinearLayoutAdapter.LinearLayoutLongClickListener {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    LinearLayoutAdapter adapter;

    Unbinder unbinder = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_linear_layout_recycler_view;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new LinearLayoutAdapter(this, DataUtil.getDataList());
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new LinearLayoutItemDecoration());

        adapter.setLinearLayoutClickListener(this);
        adapter.setLinearLayoutLongClickListener(this);

        RecyclerView.Adapter adapter2 = recyclerView.getAdapter();
        LogUtil.e("LinearLayoutRecyclerViewActivity adapter==adapter2:" + Boolean.toString(adapter == adapter2));

        LogUtil.e("LinearLayoutRecyclerViewActivity adapter.getItemCount()=" + adapter.getItemCount());
        LogUtil.e("LinearLayoutRecyclerViewActivity ScreenUtil.getScreenHeight(this)="+ ScreenUtil.getScreenHeight(this));


        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtil.e("LinearLayoutRecyclerViewActivity  postDelayed recyclerView.getChildCount()=" + recyclerView.getChildCount());
                LogUtil.e("LinearLayoutRecyclerViewActivity  recyclerView.getBottom()=" + recyclerView.getBottom());
            }
        }, 1000);

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                LogUtil.e("LinearLayoutRecyclerViewActivity  recyclerView.getHeight()=" + recyclerView.getHeight());
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void onClick(View view, int position) {
        ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "点击=" + position);
    }

    @Override
    public void onLongClick(View view, int position) {
        ToastUtil.getInstance().showToast(LinearLayoutRecyclerViewActivity.this, "长按=" + position);
    }

}
