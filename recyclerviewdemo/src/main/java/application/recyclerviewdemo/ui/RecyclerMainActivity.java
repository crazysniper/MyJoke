package application.recyclerviewdemo.ui;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.R2;
import application.recyclerviewdemo.util.RecyclerViewConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@Route(path = RecyclerViewConstant.RecyclerMainActivity)
public class RecyclerMainActivity extends BaseActivity {

    @BindView(R2.id.activity_recycler_main)
    LinearLayout relativeLayout;

    Unbinder unbinder = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_main;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        LogUtil.e("RecyclerViewDemo Module packageName==" + getPackageName());
//        findViewById(R.id.recycler).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("1111111", "333333");
//                Toast.makeText(RecyclerMainActivity.this, "RecycerView22", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @OnClick({R2.id.listRecycler, R2.id.gridRecycler,R2.id.staggeredRecycler,R2.id.listView})
    public void click(View view) {
        if (R.id.listRecycler == view.getId()) {
            Log.e("1111111", "2222222222222222222");
            ARouter.getInstance().build(RecyclerViewConstant.LinearLayoutRecyclerViewActivity).navigation();
        } else if (R.id.gridRecycler == view.getId()) {
            Log.e("1111111", "2222222222222222222");
            ARouter.getInstance().build(RecyclerViewConstant.GridLayoutRecyclerViewActivity).navigation();
        } else if (R.id.staggeredRecycler == view.getId()) {
            Log.e("1111111", "2222222222222222222");
            ARouter.getInstance().build(RecyclerViewConstant.StaggeredLayoutRecyclerViewActivity).navigation();
        }else if (R.id.listView == view.getId()) {
            ARouter.getInstance().build(RecyclerViewConstant.ListViewActivity).navigation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }
}
