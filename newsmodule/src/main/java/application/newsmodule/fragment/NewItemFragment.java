package application.newsmodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.myjoke.baselibray.base.BaseLazyFragment;
import com.myjoke.baselibray.util.LogUtil;

import application.newsmodule.R;
import application.newsmodule.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gao on 2019/1/22.
 */

public class NewItemFragment extends BaseLazyFragment {
    @BindView(R2.id.tv)
    TextView tv;
    private String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("name");
        }
        LogUtil.e("NewItemFragment  onCreate    name=" + name);
    }

    @Override
    protected int getLayoutId() {
        LogUtil.e("NewItemFragment  getLayoutId    name=" + name);
        return R.layout.item_fragment_news;
    }

    @Override
    protected void initView() {
        LogUtil.e("NewItemFragment  initView    name=" + name);
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        LogUtil.e("NewItemFragment  initData    name=" + name);
        tv.setText(name);
    }

    @Override
    public void onStart() {
        LogUtil.e("NewItemFragment  onStart    name=" + name);
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtil.e("NewItemFragment  onResume    name=" + name);
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtil.e("NewItemFragment  onPause    name=" + name);
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtil.e("NewItemFragment  onStop    name=" + name);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        LogUtil.e("NewItemFragment  onDestroy    name=" + name);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        LogUtil.e("NewItemFragment  onDestroyView    name=" + name);
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        LogUtil.e("NewItemFragment  onDetach    name=" + name);
        super.onDetach();
    }
}
