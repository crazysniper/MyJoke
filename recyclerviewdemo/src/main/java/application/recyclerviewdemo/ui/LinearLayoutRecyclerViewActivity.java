package application.recyclerviewdemo.ui;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.R2;
import application.recyclerviewdemo.adapter.LinearLayoutAdapter;
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

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            // 可以实现类似padding的效果
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                super.getItemOffsets(outRect, itemPosition, parent);
            }

            // 可以绘制在内容的上面，覆盖内容。
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            // 实现类似绘制背景的效果，内容在上面。
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });

        adapter.setLinearLayoutClickListener(this);
        adapter.setLinearLayoutLongClickListener(this);
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
