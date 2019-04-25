package application.recyclerviewdemo.ui;

import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.R2;
import application.recyclerviewdemo.adapter.ListViewAdapter;
import application.recyclerviewdemo.util.DataUtil;
import application.recyclerviewdemo.util.RecyclerViewConstant;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RecyclerViewConstant.ListViewActivity)
public class ListViewActivity extends BaseActivity {

    @BindView(R2.id.listView)
    ListView listView;


    ListViewAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        adapter = new ListViewAdapter(this, DataUtil.getDataList(), R.layout.item_layout);
        listView.setAdapter(adapter);
    }

}
